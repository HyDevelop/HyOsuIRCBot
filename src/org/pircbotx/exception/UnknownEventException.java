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
package org.pircbotx.exception;

import static com.google.common.base.Preconditions.*;

import org.pircbotx.hooks.Event;

/**
 * An unknown event error.
 *
 * @author Leon Blakey
 */
public class UnknownEventException extends RuntimeException {
	protected static final long serialVersionUID = 1L;
	
	public UnknownEventException(Event event, Throwable cause) {
		super("Unknown Event " + event.getClass().toString(), cause);
		checkNotNull(event, "Event cannot be null");
	}

	public UnknownEventException(Event event) {
		this(event, null);
	}
}
