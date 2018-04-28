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
package org.pircbotx.hooks.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.types.GenericChannelModeRecipientEvent;

import javax.annotation.Nullable;

/**
 * Called when a user (possibly us) gets owner status granted in a channel. Note
 * that this isn't supported on all servers or may be used for something else
 * <p>
 * This is a type of mode change and therefor is also dispatched in a
 * {@link ModeEvent}
 *
 * @author Leon Blakey
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OwnerEvent extends Event implements GenericChannelModeRecipientEvent {
	/**
	 * The channel in which the mode change took place.
	 */
	@Getter(onMethod = @_(
			@Override))
	protected final Channel channel;
	/**
	 * The user hostmask that performed the mode change.
	 */
	@Getter(onMethod = @_(
			@Override))
	protected final UserHostmask userHostmask;
	/**
	 * The user that performed the mode change.
	 */
	@Getter(onMethod = @_(
			@Override,
			@Nullable))
	protected final User user;
	/**
	 * The user hostmask that received the owner status.
	 */
	@Getter(onMethod = @_(
			@Override))
	protected final UserHostmask recipientHostmask;
	/**
	 * The user that received the owner status.
	 */
	@Getter(onMethod = @_(
			@Override,
			@Nullable))
	protected final User recipient;
	/**
	 * If the owner status was given or removed
	 */
	protected final boolean isOwner;

	public OwnerEvent(PircBotX bot, @NonNull Channel channel, @NonNull UserHostmask userHostmask, User user,
			@NonNull UserHostmask recipientHostmask, User recipient, boolean isOwner) {
		super(bot);
		this.channel = channel;
		this.userHostmask = userHostmask;
		this.user = user;
		this.recipientHostmask = recipientHostmask;
		this.recipient = recipient;
		this.isOwner = isOwner;
	}

	/**
	 * Gets the status of the mode change
	 *
	 * @return True if founder status was given, false if removed
	 * @deprecated Use isOwner, this method being called isFounder is a bug.
	 * Will be removed in future versions
	 */
	@Deprecated
	public boolean isFounder() {
		return isOwner;
	}

	/**
	 * Respond by send a message in the channel to the user that set the mode
	 * (<b>Warning:</b> not to the user that got owner status!) in
	 * <code>user: message</code> format
	 *
	 * @param response The response to send
	 */
	@Override
	public void respond(String response) {
		getChannel().send().message(getUser(), response);
	}
}
