/**
 * Copyright (C) 2010-2014 Leon Blakey <lord.quackstar at gmail.com>
 *
 * This file is part of PircBotX.
 *
 * PircBotX is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * PircBotX is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * PircBotX. If not, see <http://www.gnu.org/licenses/>.
 */
package org.pircbotx;

import static com.google.common.base.Preconditions.*;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Maps;
import java.io.Closeable;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.apache.commons.lang3.StringUtils;
import org.pircbotx.exception.DaoException;
import org.pircbotx.hooks.events.UserListEvent;
import org.pircbotx.snapshot.ChannelSnapshot;
import org.pircbotx.snapshot.UserChannelDaoSnapshot;
import org.pircbotx.snapshot.UserChannelMapSnapshot;
import org.pircbotx.snapshot.UserSnapshot;

/**
 * Model that creates and tracks Users and Channel and maintains relationships.
 * This includes channel users, channel op/voice/etc users, private messages,
 * etc
 * <p>
 * All methods will throw a {@link NullPointerException} when any argument is
 * null
 *
 * @see User
 * @see Channel
 * @author Leon Blakey
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChannelDao<U extends User, C extends Channel> implements Closeable {
	protected final PircBotX bot;
	protected final Configuration.BotFactory botFactory;
	protected final Locale locale;
	protected final Object accessLock = new Object();
	protected final UserChannelMap<U, C> mainMap;
	protected final EnumMap<UserLevel, UserChannelMap<U, C>> levelsMap;
	protected final Map<String, U> userNickMap;
	protected final Map<String, C> channelNameMap;
	protected final Map<String, U> privateUsers;

	protected UserChannelDao(PircBotX bot, Configuration.BotFactory botFactory) {
		this.bot = bot;
		this.botFactory = botFactory;
		this.locale = bot.getConfiguration().getLocale();
		this.mainMap = new UserChannelMap<U, C>();
		this.userNickMap = Maps.newHashMap();
		this.channelNameMap = Maps.newHashMap();
		this.privateUsers = Maps.newHashMap();

		//Initialize levels map with a UserChannelMap for each level
		this.levelsMap = Maps.newEnumMap(UserLevel.class);
		for (UserLevel level : UserLevel.values())
			levelsMap.put(level, new UserChannelMap<U, C>());
	}

	/**
	 * Lookup user by nick, throwing a {@link DaoException} if not found
	 *
	 * @param nick The nick of the user
	 * @return Known active {@link User}
	 * @throws DaoException If user does not exist, exception will contain
	 * {@link DaoException.Reason#UnknownUser} and the
	 * nick that doesn't exist
	 */
	@Synchronized("accessLock")
	public U getUser(@NonNull String nick) throws DaoException {
		checkArgument(StringUtils.isNotBlank(nick), "Cannot get a blank user");
		U user = userNickMap.get(nick.toLowerCase(locale));
		if (user != null)
			return user;

		//Does not exist
		throw new DaoException(DaoException.Reason.UnknownUser, nick);
	}

	/**
	 * Lookup user by UserHostmask, throwing a {@link DaoException} if not found
	 *
	 * @param userHostmask The hostmask of the user
	 * @return Known active {@link User}
	 * @throws DaoException If user does not exist, exception will contain
	 * {@link DaoException.Reason#UnknownUserHostmask},
	 * hostmask, and wrapped exception with nick
	 */
	@Synchronized("accessLock")
	public U getUser(@NonNull UserHostmask userHostmask) {
		try {
			//Rarely we don't get the full hostmask
			//eg, the server setting your usermode when you connect to the server
			if (userHostmask.getNick() == null)
				return getUser(userHostmask.getHostmask());
			return getUser(userHostmask.getNick());
		} catch (Exception e) {
			//Does not exist, wrap with detail about hostmask
			throw new DaoException(DaoException.Reason.UnknownUserHostmask, userHostmask.toString(), e);
		}
	}

	/**
	 * Create a user from a hostmask, internally called when a valid, real user
	 * contacts us
	 *
	 * @param userHostmask The hostmask of the user
	 * @return Active {@link User} that was created
	 */
	@Synchronized("accessLock")
	@SuppressWarnings("unchecked")
	public U createUser(@NonNull UserHostmask userHostmask) {
		if (containsUser(userHostmask))
			throw new RuntimeException("Cannot create a user from hostmask that already exists: " + userHostmask);
		U user = (U) botFactory.createUser(userHostmask);
		userNickMap.put(userHostmask.getNick().toLowerCase(locale), user);
		return user;
	}

	/**
	 * @deprecated Renamed {@link #containsUser(String) } to match
	 * Java Collections API
	 * @see #containsUser(String)
	 */
	@Synchronized("accessLock")
	@Deprecated
	public boolean userExists(@NonNull String nick) {
		return containsUser(nick);
	}

	/**
	 * Check if user exists by nick
	 *
	 * @param nick Nick of user
	 * @return True if user exists
	 */
	@Synchronized("accessLock")
	public boolean containsUser(@NonNull String nick) {
		String nickLowercase = nick.toLowerCase(locale);
		return userNickMap.containsKey(nickLowercase) || privateUsers.containsKey(nickLowercase);
	}

	/**
	 * Check if user exists by hostmask
	 *
	 * @param hostmask Hostmask of user
	 * @return True if user exists
	 */
	@Synchronized("accessLock")
	public boolean containsUser(@NonNull UserHostmask hostmask) {
		return containsUser(hostmask.getNick());
	}

	/**
	 * Get all currently known users, except from just joined channels where the
	 * WHO response hasn't finished (listen for {@link UserListEvent} instead)
	 *
	 * @return An immutable set of the currently known users
	 * @see UserListEvent
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<U> getAllUsers() {
		return ImmutableSortedSet.copyOf(userNickMap.values());
	}

	@Synchronized("accessLock")
	protected void addUserToChannel(@NonNull U user, @NonNull C channel) {
		mainMap.addUserToChannel(user, channel);
	}

	@Synchronized("accessLock")
	protected void addUserToPrivate(@NonNull U user) {
		String nick = user.getNick().toLowerCase(locale);
		privateUsers.put(nick, user);
	}

	@Synchronized("accessLock")
	protected void addUserToLevel(@NonNull UserLevel level, @NonNull U user, @NonNull C channel) {
		levelsMap.get(level).addUserToChannel(user, channel);
	}

	@Synchronized("accessLock")
	protected void removeUserFromLevel(@NonNull UserLevel level, @NonNull U user, @NonNull C channel) {
		levelsMap.get(level).removeUserFromChannel(user, channel);
	}

	/**
	 * Gets all currently known users in a channel who do not hold a UserLevel
	 * (op/voice/etc). A {@link UserListEvent} for the channel must of been
	 * dispatched before this method will return complete results
	 *
	 * @param channel Known channel
	 * @return An immutable sorted set of Users
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<U> getNormalUsers(@NonNull C channel) {
		Set<U> remainingUsers = new HashSet<U>(mainMap.getUsers(channel));
		for (UserChannelMap<U, C> curLevelMap : levelsMap.values())
			remainingUsers.removeAll(curLevelMap.getUsers(channel));
		return ImmutableSortedSet.copyOf(remainingUsers);
	}

	/**
	 * Gets all currently known users in a channel that hold the specified
	 * UserLevel. A {@link UserListEvent} for the channel must of been
	 * dispatched before this method will return complete results
	 *
	 * @param channel Known channel
	 * @param level Level users must hold
	 * @return An immutable sorted set of Users
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<U> getUsers(@NonNull C channel, @NonNull UserLevel level) {
		return levelsMap.get(level).getUsers(channel);
	}

	/**
	 * Gets all currently known levels (op/voice/etc) a user holds in the
	 * channel. A {@link UserListEvent} for the channel must of been dispatched
	 * before this method will return complete results
	 *
	 * @param channel Known channel
	 * @param user Known user
	 * @return An immutable sorted set of UserLevels
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<UserLevel> getLevels(@NonNull C channel, @NonNull U user) {
		ImmutableSortedSet.Builder<UserLevel> builder = ImmutableSortedSet.naturalOrder();
		for (Map.Entry<UserLevel, UserChannelMap<U, C>> curEntry : levelsMap.entrySet())
			if (curEntry.getValue().containsEntry(user, channel))
				builder.add(curEntry.getKey());
		return builder.build();
	}

	/**
	 * Gets all currently known channels the user is a part of as a normal user.
	 * A {@link UserListEvent} for all channels must of been dispatched before
	 * this method will return complete results
	 *
	 * @param user Known user
	 * @return An immutable sorted set of Channels
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<C> getNormalUserChannels(@NonNull U user) {
		Set<C> remainingChannels = new HashSet<C>(mainMap.getChannels(user));
		for (UserChannelMap<U, C> curLevelMap : levelsMap.values())
			remainingChannels.removeAll(curLevelMap.getChannels(user));
		return ImmutableSortedSet.copyOf(remainingChannels);
	}

	/**
	 * Gets all currently known channels the user is a part of with the
	 * specified level. A {@link UserListEvent} for all channels must of been
	 * dispatched before this method will return complete results
	 *
	 * @param user Known user
	 * @return An immutable sorted set of Channels
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<C> getChannels(@NonNull U user, @NonNull UserLevel level) {
		return levelsMap.get(level).getChannels(user);
	}

	@Synchronized("accessLock")
	protected void removeUserFromChannel(@NonNull U user, @NonNull C channel) {
		mainMap.removeUserFromChannel(user, channel);
		for (UserChannelMap<U, C> curLevelMap : levelsMap.values())
			curLevelMap.removeUserFromChannel(user, channel);

		if (!privateUsers.values().contains(user) && !mainMap.containsUser(user))
			//Completely remove user
			userNickMap.remove(user.getNick().toLowerCase(locale));
	}

	@Synchronized("accessLock")
	protected void removeUser(@NonNull U user) {
		mainMap.removeUser(user);
		for (UserChannelMap<U, C> curLevelMap : levelsMap.values())
			curLevelMap.removeUser(user);

		//Remove remaining locations
		userNickMap.remove(user.getNick().toLowerCase(locale));
		privateUsers.remove(user.getNick().toLowerCase(locale));
	}

	@Synchronized("accessLock")
	protected boolean levelContainsUser(@NonNull UserLevel level, @NonNull C channel, @NonNull U user) {
		return levelsMap.get(level).containsEntry(user, channel);
	}

	@Synchronized("accessLock")
	protected void renameUser(@NonNull U user, @NonNull String newNick) {
		String oldNick = user.getNick();

		user.setNick(newNick);
		userNickMap.remove(oldNick.toLowerCase(locale));
		userNickMap.put(newNick.toLowerCase(locale), user);
	}

	/**
	 * Lookup channel by name, throwing a {@link DaoException} if not found
	 *
	 * @param name Name of channel (eg #pircbotx)
	 * @return A known channel
	 */
	@Synchronized("accessLock")
	public C getChannel(@NonNull String name) throws DaoException {
		checkArgument(StringUtils.isNotBlank(name), "Cannot get a blank channel");
		C chan = channelNameMap.get(name.toLowerCase(locale));
		if (chan != null)
			return chan;

		//This could potentially be a mode message, strip off prefixes till we get a channel
		String modePrefixes = bot.getConfiguration().getUserLevelPrefixes();
		if (modePrefixes.contains(Character.toString(name.charAt(0)))) {
			String nameTrimmed = name.toLowerCase(locale);
			do {
				nameTrimmed = nameTrimmed.substring(1);
				chan = channelNameMap.get(nameTrimmed);
				if (chan != null)
					return chan;
			} while (modePrefixes.contains(Character.toString(nameTrimmed.charAt(0))));
		}

		//Channel does not exist
		throw new DaoException(DaoException.Reason.UnknownChannel, name);
	}

	/**
	 * Creates a known channel, internally called when we join a channel
	 *
	 * @param name
	 */
	@Synchronized("accessLock")
	@SuppressWarnings("unchecked")
	public C createChannel(@NonNull String name) {
		C chan = (C) botFactory.createChannel(bot, name);
		channelNameMap.put(name.toLowerCase(locale), chan);
		return chan;
	}

	/**
	 * @deprecated Renamed {@link #containsChannel(String) } to match
	 * the Java Collections API
	 * @see #containsChannel(String)
	 */
	@Deprecated
	public boolean channelExists(@NonNull String name) {
		return containsChannel(name);
	}

	/**
	 * Check if we are currently in the given channel
	 *
	 * @param name Channel name (eg #pircbotx)
	 * @return True if we are still connected to the channel
	 */
	@Synchronized("accessLock")
	public boolean containsChannel(@NonNull String name) {
		if (channelNameMap.containsKey(name.toLowerCase(locale)))
			return true;

		//This could potentially be a mode message, strip off prefixes till we get a channel
		String modePrefixes = bot.getConfiguration().getUserLevelPrefixes();
		if (modePrefixes.contains(Character.toString(name.charAt(0)))) {
			String nameTrimmed = name.toLowerCase(locale);
			do {
				nameTrimmed = nameTrimmed.substring(1);
				if (channelNameMap.containsKey(nameTrimmed))
					return true;
			} while (modePrefixes.contains(Character.toString(nameTrimmed.charAt(0))));
		}

		//Nope, doesn't exist
		return false;
	}

	/**
	 * Get all currently known users in a channel
	 *
	 * @param channel Known channel
	 * @return An immutable set of users
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<U> getUsers(@NonNull C channel) {
		return mainMap.getUsers(channel);
	}

	/**
	 * Get all currently joined channels
	 *
	 * @return An immutable set of channels
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<C> getAllChannels() {
		return ImmutableSortedSet.copyOf(channelNameMap.values());
	}

	/**
	 * Get <i>channels we're joined to</i> that the user is joined to as well
	 *
	 * @param user A known user
	 * @return An immutable set of channels
	 */
	@Synchronized("accessLock")
	public ImmutableSortedSet<C> getChannels(@NonNull U user) {
		return mainMap.getChannels(user);
	}

	@Synchronized("accessLock")
	protected void removeChannel(@NonNull C channel) {
		mainMap.removeChannel(channel);
		for (UserChannelMap<U, C> curLevelMap : levelsMap.values())
			curLevelMap.removeChannel(channel);

		//Remove remaining locations
		channelNameMap.remove(channel.getName());
	}

	/**
	 * Gets the bots own user object.
	 *
	 * @return The user object representing this bot
	 */
	@Synchronized("accessLock")
	public User getUserBot() {
		return getUser(bot.getNick());
	}

	/**
	 * Clears all internal maps
	 */
	@Synchronized("accessLock")
	public void close() {
		mainMap.clear();
		for (UserChannelMap<U, C> curLevelMap : levelsMap.values())
			curLevelMap.clear();
		channelNameMap.clear();
		privateUsers.clear();
		userNickMap.clear();
	}

	/**
	 * Create an immutable snapshot (copy) of all of contained Users, Channels,
	 * and mappings, VERY EXPENSIVE.
	 *
	 * @return Copy of entire model
	 */
	@Synchronized("accessLock")
	public UserChannelDaoSnapshot createSnapshot() {
		//Create snapshots of all users and channels
		Map<U, UserSnapshot> userSnapshotMap = Maps.newHashMapWithExpectedSize(userNickMap.size());
		for (U curUser : userNickMap.values())
			userSnapshotMap.put(curUser, curUser.createSnapshot());
		Map<C, ChannelSnapshot> channelSnapshotMap = Maps.newHashMapWithExpectedSize(channelNameMap.size());
		for (C curChannel : channelNameMap.values())
			channelSnapshotMap.put(curChannel, curChannel.createSnapshot());

		//Make snapshots of the relationship maps using the above user and channel snapshots
		UserChannelMapSnapshot mainMapSnapshot = mainMap.createSnapshot(userSnapshotMap, channelSnapshotMap);
		EnumMap<UserLevel, UserChannelMap<UserSnapshot, ChannelSnapshot>> levelsMapSnapshot = Maps.newEnumMap(UserLevel.class);
		for (Map.Entry<UserLevel, UserChannelMap<U, C>> curLevel : levelsMap.entrySet())
			levelsMapSnapshot.put(curLevel.getKey(), curLevel.getValue().createSnapshot(userSnapshotMap, channelSnapshotMap));
		ImmutableBiMap.Builder<String, UserSnapshot> userNickMapSnapshotBuilder = ImmutableBiMap.builder();
		for (Map.Entry<String, U> curNickEntry : userNickMap.entrySet())
			userNickMapSnapshotBuilder.put(curNickEntry.getKey(), userSnapshotMap.get(curNickEntry.getValue()));
		ImmutableBiMap.Builder<String, ChannelSnapshot> channelNameMapSnapshotBuilder = ImmutableBiMap.builder();
		for (Map.Entry<String, C> curName : channelNameMap.entrySet())
			channelNameMapSnapshotBuilder.put(curName.getKey(), channelSnapshotMap.get(curName.getValue()));
		ImmutableBiMap.Builder<String, UserSnapshot> privateUserSnapshotBuilder = ImmutableBiMap.builder();
		for (Map.Entry<String, U> curNickEntry : privateUsers.entrySet())
			privateUserSnapshotBuilder.put(curNickEntry.getKey(), userSnapshotMap.get(curNickEntry.getValue()));

		//Finally can create the snapshot object
		UserChannelDaoSnapshot daoSnapshot = new UserChannelDaoSnapshot(bot,
				locale,
				mainMapSnapshot,
				levelsMapSnapshot,
				userNickMapSnapshotBuilder.build(),
				channelNameMapSnapshotBuilder.build(),
				privateUserSnapshotBuilder.build());

		//Tell UserSnapshots and ChannelSnapshots what the new backing dao is
		for (UserSnapshot curUserSnapshot : userSnapshotMap.values())
			curUserSnapshot.setDao(daoSnapshot);
		for (ChannelSnapshot curChannelSnapshot : channelSnapshotMap.values())
			curChannelSnapshot.setDao(daoSnapshot);

		//Finally
		return daoSnapshot;
	}
}
