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
import org.pircbotx.hooks.CoreHooks;
import org.pircbotx.hooks.Event;
import org.pircbotx.PircBotX;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.types.GenericChannelUserEvent;

/**
 * This event is dispatched whenever we receive a FINGER request.
 * <p>
 * {@link CoreHooks} automatically responds correctly. Unless {@link CoreHooks}
 * is removed from the
 * {@link org.pircbotx.Configuration#getListenerManager() bot's ListenerManager},
 * Listeners of this event should <b>not</b> send a response as the user will
 * get two responses
 *
 * @author Leon Blakey
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FingerEvent extends Event implements GenericChannelUserEvent {
	/**
	 * The user hostmask that sent the FINGER request.
	 */
	@Getter(onMethod = @_(
			@Override))
	protected final UserHostmask userHostmask;
	/**
	 * The user that sent the FINGER request.
	 */
	@Getter(onMethod = @_({
		@Override,
		@Nullable}))
	protected final User user;
	/**
	 * The target channel of the FINGER request.
	 */
	protected final Channel channel;

	public FingerEvent(PircBotX bot, @NonNull UserHostmask userHostmask, User user, @Nullable Channel channel) {
		super(bot);
		this.userHostmask = userHostmask;
		this.user = user;
		this.channel = channel;
	}

	/**
	 * Respond with a CTCP response to the user
	 *
	 * @param response The response to send
	 */
	@Override
	public void respond(String response) {
		getUserHostmask().send().ctcpResponse(response);
	}
}
