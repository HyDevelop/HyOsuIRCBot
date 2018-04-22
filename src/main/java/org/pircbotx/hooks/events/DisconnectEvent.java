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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.pircbotx.hooks.Event;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericSnapshotEvent;
import org.pircbotx.snapshot.UserChannelDaoSnapshot;

/**
 * This event is dispatched when we get disconnected. It is meant for the bot to
 * carry out actions upon disconnection. This may happen if the PircBotX quits
 * from the server, or if the connection is unexpectedly lost.
 * <p>
 * Disconnection from the IRC server is detected immediately if either we or the
 * server close the connection normally. If the connection to the server is
 * lost, but neither we nor the server have explicitly closed the connection,
 * then it may take a few minutes to detect (this is commonly referred to as a
 * "ping timeout").
 *
 * @author Leon Blakey
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DisconnectEvent extends Event implements GenericSnapshotEvent {
	@Getter(onMethod = @_(
			@Override))
	protected final UserChannelDaoSnapshot userChannelDaoSnapshot;
	/**
	 * Exception encountered during disconnection, if any
	 */
	protected final Exception disconnectException;

	public DisconnectEvent(PircBotX bot, UserChannelDaoSnapshot userChannelDaoSnapshot, Exception disconnectException) {
		super(bot);
		this.userChannelDaoSnapshot = userChannelDaoSnapshot;
		this.disconnectException = disconnectException;
	}

	/**
	 * @see #getUserChannelDaoSnapshot()
	 * @see GenericSnapshotEvent
	 * @deprecated Use {@link #getUserChannelDaoSnapshot() } from
	 * {@link GenericSnapshotEvent}
	 */
	@Deprecated
	public UserChannelDaoSnapshot getDaoSnapshot() {
		return userChannelDaoSnapshot;
	}

	/**
	 * Does NOT respond to the server! This will throw an
	 * {@link UnsupportedOperationException} since we can't respond to a server
	 * we just disconnected from.
	 *
	 * @param response The response to send
	 */
	@Override
	@Deprecated
	public void respond(String response) {
		throw new UnsupportedOperationException("Attepting to respond to a disconnected server");
	}
}
