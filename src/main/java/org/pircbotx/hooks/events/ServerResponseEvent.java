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

import com.google.common.collect.ImmutableList;
import javax.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.pircbotx.hooks.Event;
import org.pircbotx.PircBotX;
import org.pircbotx.ReplyConstants;

/**
 * This is called when we receive a numeric response from the IRC server.
 * <p>
 * Numerics in the range from 001 to 099 are used for client-server connections
 * only and should never travel between servers. Replies generated in response
 * to commands are found in the range from 200 to 399. Error replies are found
 * in the range from 400 to 599.
 * <p>
 * For example, we can use this event to discover the topic of a channel when we
 * join it. If we join the channel #test which has a topic of &quot;I am King of
 * Test&quot; then the response will be &quot;
 * <code>PircBotX #test :I Am King of Test</code>&quot; with a code of 332 to
 * signify that this is a topic. (This is just an example - note that listening
 * for {@link TopicEvent} is an easier way of finding the topic for a channel).
 * Check the IRC RFC for the full list of other command response codes.
 * <p>
 * PircBotX uses the class ReplyConstants, which contains constants that you may
 * find useful here.
 *
 * @see ReplyConstants
 * @author Leon Blakey
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerResponseEvent extends Event {
	/**
	 * The three-digit numerical code for the response.
	 */
	protected final int code;
	/**
	 * The raw line of the response
	 */
	protected final String rawLine;
	/**
	 * Parsed raw line.
	 */
	protected final ImmutableList<String> parsedResponse;

	public ServerResponseEvent(PircBotX bot, int code, @NonNull String rawLine, @NonNull ImmutableList<String> parsedResponse) {
		super(bot);
		this.code = code;
		this.rawLine = rawLine;
		this.parsedResponse = parsedResponse;
	}

	/**
	 * Respond with a <i>raw line</i> to the server
	 *
	 * @param response The response to send
	 */
	@Override
	public void respond(String response) {
		getBot().sendRaw().rawLine(response);
	}
}
