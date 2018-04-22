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
package org.pircbotx.hooks.types;

import org.pircbotx.User;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.events.OpEvent;

/**
 * Any user status change in a channel. Eg {@link OpEvent}
 * <p/>
 * @author Leon Blakey
 */
public interface GenericUserModeEvent extends GenericUserEvent {
	/**
	 * The recipient user hostmask that this event applies to.
	 *
	 * @return The hostmask of the user
	 */
	public UserHostmask getRecipientHostmask();

	/**
	 * The recipient user that this event applies to.
	 *
	 * @return The user or null if the hostmask didn't match a user at creation
	 * time
	 */
	public User getRecipient();
}
