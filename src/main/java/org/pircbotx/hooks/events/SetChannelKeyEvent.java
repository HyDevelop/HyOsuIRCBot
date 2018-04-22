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

import javax.annotation.Nullable;
import org.pircbotx.Channel;
import org.pircbotx.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.pircbotx.hooks.Event;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.types.GenericChannelModeEvent;

/**
 * Called when a channel key is set. When the channel key has been set, other
 * users may only join that channel if they know the key. Channel keys are
 * sometimes referred to as passwords.
 * <p>
 * This is a type of mode change and therefor is also dispatched in a
 * {@link ModeEvent}
 *
 * @author Leon Blakey
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetChannelKeyEvent extends Event implements GenericChannelModeEvent {
	@Getter(onMethod = @_(
			@Override))
	protected final Channel channel;
	@Getter(onMethod = @_(
			@Override))
	protected final UserHostmask userHostmask;
	@Getter(onMethod = @_(
			@Override))
	protected final User user;
	/**
	 * The new key for the channel.
	 */
	protected final String key;

	public SetChannelKeyEvent(PircBotX bot, @NonNull Channel channel, @NonNull UserHostmask userHostmask, User user, String key) {
		super(bot);
		this.channel = channel;
		this.userHostmask = userHostmask;
		this.user = user;
		this.key = key;
	}

	/**
	 * Respond by send a message in the channel to the user that set the mode in
	 * <code>user: message</code> format
	 *
	 * @param response The response to send
	 */
	@Override
	public void respond(String response) {
		getChannel().send().message(getUser(), response);
	}
}
