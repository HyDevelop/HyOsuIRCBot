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
package org.pircbotx.dcc;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pircbotx.*;
import org.pircbotx.exception.DccException;
import org.pircbotx.hooks.events.IncomingChatRequestEvent;
import org.pircbotx.hooks.events.IncomingFileTransferEvent;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Handler of all DCC requests
 * <p>
 * @author Leon Blakey
 */
@RequiredArgsConstructor
@Slf4j
public class DccHandler implements Closeable {
	protected static final Random TOKEN_RANDOM = new SecureRandom();
	protected static final int TOKEN_RANDOM_MAX = 20000;
	@NonNull
	protected final PircBotX bot;
	protected final Map<PendingRecieveFileTransfer, CountDownLatch> pendingReceiveTransfers = new HashMap<PendingRecieveFileTransfer, CountDownLatch>();
	protected final List<PendingSendFileTransfer> pendingSendTransfers = new ArrayList<PendingSendFileTransfer>();
	protected final Map<PendingSendFileTransferPassive, CountDownLatch> pendingSendPassiveTransfers = new HashMap<PendingSendFileTransferPassive, CountDownLatch>();
	protected final Map<PendingSendChatPassive, CountDownLatch> pendingSendPassiveChat = new HashMap<PendingSendChatPassive, CountDownLatch>();
	protected boolean shuttingDown = false;

	public boolean processDcc(UserHostmask userHostmask, final User user, String request) throws IOException {
		List<String> requestParts = tokenizeDccRequest(request);
		String type = requestParts.get(1);
		if (type.equals("SEND")) {
			//Someone is trying to send a file to us
			//Example: DCC SEND <filename>  <ip> <port> <file size> <transferToken> (note File size is optional)
			String rawFilename = requestParts.get(2);
			final String safeFilename = (rawFilename.startsWith("\"") && rawFilename.endsWith("\""))
					? rawFilename.substring(1, rawFilename.length() - 1) : rawFilename;
			InetAddress address = parseRawAddress(requestParts.get(3));
			int port = Integer.parseInt(requestParts.get(4));
			long size = Long.parseLong(Utils.tryGetIndex(requestParts, 5, "-1"));
			String transferToken = Utils.tryGetIndex(requestParts, 6, null);

			if (transferToken != null)
				//Check if this is an acknowledgement of a passive dcc file request
				synchronized (pendingSendPassiveTransfers) {
					Iterator<Map.Entry<PendingSendFileTransferPassive, CountDownLatch>> pendingItr = pendingSendPassiveTransfers.entrySet().iterator();
					while (pendingItr.hasNext()) {
						Map.Entry<PendingSendFileTransferPassive, CountDownLatch> curEntry = pendingItr.next();
						PendingSendFileTransferPassive transfer = curEntry.getKey();
						if (transfer.getUser() == user && transfer.getFilename().equals(rawFilename)
								&& transfer.getTransferToken().equals(transferToken)) {
							transfer.setReceiverAddress(address);
							transfer.setReceiverPort(port);
							log.debug("Passive send file transfer of file {} to user {} accepted at address {} and port {}",
									transfer.getFilename(), transfer.getUser().getNick(), address, port);
							curEntry.getValue().countDown();
							pendingItr.remove();
							return true;
						}
					}
				}

			//Nope, this is a new transfer
			if (port == 0 || transferToken != null)
				//User is trying to use reverse DCC
				bot.getConfiguration().getListenerManager().onEvent(new IncomingFileTransferEvent(bot, userHostmask, user, rawFilename, safeFilename, address, port, size, transferToken, true));
			else
				bot.getConfiguration().getListenerManager().onEvent(new IncomingFileTransferEvent(bot, userHostmask, user, rawFilename, safeFilename, address, port, size, transferToken, false));
		} else if (type.equals("RESUME")) {
			//Someone is trying to resume sending a file to us
			//Example: DCC RESUME <filename> 0 <position> <token>
			//Reply with: DCC ACCEPT <filename> 0 <position> <token>
			String filename = requestParts.get(2);
			int port = Integer.parseInt(requestParts.get(3));
			long position = Long.parseLong(requestParts.get(4));

			if (port == 0) {
				//Passive transfer
				String transferToken = requestParts.get(5);
				synchronized (pendingSendPassiveTransfers) {
					Iterator<Map.Entry<PendingSendFileTransferPassive, CountDownLatch>> pendingItr = pendingSendPassiveTransfers.entrySet().iterator();
					while (pendingItr.hasNext()) {
						Map.Entry<PendingSendFileTransferPassive, CountDownLatch> curEntry = pendingItr.next();
						PendingSendFileTransferPassive transfer = curEntry.getKey();
						if (transfer.getUser() == user && transfer.getFilename().equals(filename)
								&& transfer.getTransferToken().equals(transferToken)) {
							transfer.setStartPosition(position);
							log.debug("Passive send file transfer of file {} to user {} set to position {}",
									transfer.getFilename(), transfer.getUser().getNick(), position);
							return true;
						}
					}
				}
			} else
				synchronized (pendingSendTransfers) {
					Iterator<PendingSendFileTransfer> pendingItr = pendingSendTransfers.iterator();
					while (pendingItr.hasNext()) {
						PendingSendFileTransfer transfer = pendingItr.next();
						if (transfer.getUser() == user && transfer.getFilename().equals(filename)
								&& transfer.getPort() == port) {
							transfer.setPosition(position);
							log.debug("Send file transfer of file {} to user {} set to position {}",
									transfer.getFilename(), transfer.getUser().getNick(), position);
							return true;
						}
					}
				}

			//Haven't returned yet, received an unknown transfer
			throw new DccException(DccException.Reason.UnknownFileTransferResume, user, "Transfer line: " + request);
		} else if (type.equals("ACCEPT")) {
			//Someone is acknowledging a transfer resume
			//Example (normal):  DCC ACCEPT <filename> <port> <position>
			//Example (passive): DCC ACCEPT <filename> 0 <position> <token>
			//TODO how well does this handle non passive?
			String filename = requestParts.get(2);
			int port;
			long position = Long.parseLong(requestParts.get(4));
			String transferToken;
			if (requestParts.size() == 5) {
				//Standard request
				port = Integer.parseInt(requestParts.get(3));
				transferToken = null;
			} else {
				//Passive request
				port = 0;
				transferToken = requestParts.get(5);
			}

			synchronized (pendingReceiveTransfers) {
				Iterator<Map.Entry<PendingRecieveFileTransfer, CountDownLatch>> pendingItr = pendingReceiveTransfers.entrySet().iterator();
				while (pendingItr.hasNext()) {
					Map.Entry<PendingRecieveFileTransfer, CountDownLatch> curEntry = pendingItr.next();
					IncomingFileTransferEvent transferEvent = curEntry.getKey().getEvent();
					if (transferEvent.getUser() == user && transferEvent.getRawFilename().equals(filename)
							&& transferEvent.getPort() == port && transferEvent.getToken().equals(transferToken)) {
						curEntry.getKey().setPosition(position);
						log.debug("Receive file transfer of file {} to user {} set to position {}",
								transferEvent.getRawFilename(), transferEvent.getUser().getNick(), position);
						curEntry.getValue().countDown();
						pendingItr.remove();
						return true;
					}
				}
			}
		} else if (type.equals("CHAT")) {
			//Someone is trying to chat with us
			//Example: DCC CHAT <protocol> <ip> <port> (protocol should be chat)
			InetAddress address = parseRawAddress(requestParts.get(3));
			int port = Integer.parseInt(requestParts.get(4));
			String chatToken = Utils.tryGetIndex(requestParts, 5, null);

			//Check if this is an acknowledgement of a passive chat request
			if (chatToken != null)
				synchronized (pendingSendPassiveChat) {
					Iterator<Map.Entry<PendingSendChatPassive, CountDownLatch>> pendingItr = pendingSendPassiveChat.entrySet().iterator();
					while (pendingItr.hasNext()) {
						Map.Entry<PendingSendChatPassive, CountDownLatch> curEntry = pendingItr.next();
						PendingSendChatPassive pendingChat = curEntry.getKey();
						log.trace("Current pending chat: {}", pendingChat);
						if (pendingChat.getUser() == user && pendingChat.getChatToken().equals(chatToken)) {
							log.debug("Passive chat request to user {} accepted", user);
							pendingChat.setReceiverAddress(address);
							pendingChat.setReceiverPort(port);
							curEntry.getValue().countDown();
							pendingItr.remove();
							return true;
						}
					}
				}

			//Nope, this is a new chat
			if (port == 0 && chatToken != null)
				bot.getConfiguration().getListenerManager().onEvent(new IncomingChatRequestEvent(bot, userHostmask, user, address, port, chatToken, true));
			else
				bot.getConfiguration().getListenerManager().onEvent(new IncomingChatRequestEvent(bot, userHostmask, user, address, port, chatToken, false));
		} else
			return false;
		return true;
	}

	/**
	 * Accept chat request, blocking until the connection is active
	 * <p>
	 * @param event The chat request event
	 * @return An active {@link ReceiveChat}
	 * @throws IOException If an error occurred during connection
	 */
	public ReceiveChat acceptChatRequest(IncomingChatRequestEvent event) throws IOException {
		checkNotNull(event, "Event cannot be null");
		if (event.isPassive()) {
			ServerSocket serverSocket = createServerSocket(event.getUser());
			InetAddress publicAddress = getRealDccPublicAddress(serverSocket);
			bot.sendDCC().chatPassiveAccept(event.getUser().getNick(), 
					publicAddress, serverSocket.getLocalPort(), event.getToken());
			
			log.debug("Sent DCC recieve chat accept to user {} ({}ms timeout) to passive connect on public address {}, local address {}", 
					event.getUser().getNick(), 
					bot.getConfiguration().getDccAcceptTimeout(),
					publicAddress, 
					serverSocket.getLocalSocketAddress());
			Socket userSocket = serverSocket.accept();

			//User is connected, begin transfer
			serverSocket.close();
			return bot.getConfiguration().getBotFactory().createReceiveChat(bot, event.getUser(), userSocket);
		} else {
			InetAddress localAddress = getRealDccLocalAddress(event.getAddress());
			log.debug("Accepting DCC recieve chat from user {} at address {} port {} from local address {}", 
					event.getUser().getNick(), 
					event.getAddress(),
					event.getPort(),
					localAddress);
			return bot.getConfiguration().getBotFactory().createReceiveChat(bot, event.getUser(), 
					new Socket(event.getAddress(), event.getPort(), localAddress, 0));
		}
	}

	/**
	 * Accept file transfer at position 0, blocking until the connection is
	 * active
	 * <p>
	 * @param event The file request event
	 * @param destination The destination file
	 * @return An active {@link ReceiveFileTransfer}
	 * @throws IOException If an error occurred during connection
	 */
	public ReceiveFileTransfer acceptFileTransfer(IncomingFileTransferEvent event, File destination) throws IOException {
		checkNotNull(event, "Event cannot be null");
		checkNotNull(destination, "Destination file cannot be null");
		return acceptFileTransfer(event, destination, 0);
	}

	/**
	 * Accept file transfer resuming at specified position, blocking until the
	 * connection is active
	 * <p>
	 * @param event The file request event
	 * @param destination The destination file
	 * @param startPosition The position to start the transfer at
	 * @return An active {@link ReceiveFileTransfer}
	 * @throws IOException If an error occurred during connection
	 * @throws InterruptedException If this is interrupted while waiting for a
	 * connection
	 * @throws DccException If a timeout is reached or the bot is shutting down
	 */
	public ReceiveFileTransfer acceptFileTransferResume(IncomingFileTransferEvent event, File destination, long startPosition) throws IOException, InterruptedException, DccException {
		checkNotNull(event, "Event cannot be null");
		checkNotNull(destination, "Destination file cannot be null");
		checkArgument(startPosition >= 0, "Start position %s must be positive", startPosition);

		//Add to pending map so we can be notified when the user has accepted
		CountDownLatch countdown = new CountDownLatch(1);
		PendingRecieveFileTransfer pendingTransfer = new PendingRecieveFileTransfer(event);
		synchronized (pendingReceiveTransfers) {
			pendingReceiveTransfers.put(pendingTransfer, countdown);
		}

		//Tell user were going to resume transfering
		if (event.isPassive())
			bot.sendDCC().filePassiveResumeRequest(event.getUser().getNick(), event.getRawFilename(), startPosition, event.getToken());
		else
			bot.sendDCC().fileResumeRequest(event.getUser().getNick(), event.getRawFilename(), event.getPort(), startPosition);

		//Wait for response
		if (!countdown.await(bot.getConfiguration().getDccResumeAcceptTimeout(), TimeUnit.MILLISECONDS))
			throw new DccException(DccException.Reason.FileTransferResumeTimeout, event.getUser(), "Event: " + event);
		if (shuttingDown)
			throw new DccException(DccException.Reason.FileTransferResumeCancelled, event.getUser(), "Transfer " + event + " canceled due to bot shutting down");

		//User has accepted resume, begin transfer
		if (pendingTransfer.getPosition() != startPosition)
			log.warn("User is resuming transfer at position {} instead of requested position {} for transfer {}. Defaulting to users position",
					pendingTransfer.getPosition(), startPosition, event);
		return acceptFileTransfer(event, destination, pendingTransfer.getPosition());
	}

	protected ReceiveFileTransfer acceptFileTransfer(IncomingFileTransferEvent event, File destination, long startPosition) throws IOException {
		checkNotNull(event, "Event cannot be null");
		checkNotNull(destination, "Destination file cannot be null");
		checkArgument(startPosition >= 0, "Start position %s must be positive", startPosition);

		if (event.isPassive()) {
			ServerSocket serverSocket = createServerSocket(event.getUser());
			bot.sendDCC().filePassiveAccept(event.getUser().getNick(), event.getRawFilename(), getRealDccPublicAddress(serverSocket), serverSocket.getLocalPort(), event.getFilesize(), event.getToken());
			Socket userSocket = serverSocket.accept();

			//User is connected, begin transfer
			serverSocket.close();
			return bot.getConfiguration().getBotFactory().createReceiveFileTransfer(bot, userSocket, event.getUser(), destination, startPosition, event.getFilesize());
		} else {
			Socket userSocket = new Socket(event.getAddress(), event.getPort(), getRealDccLocalAddress(event.getAddress()), 0);
			return bot.getConfiguration().getBotFactory().createReceiveFileTransfer(bot, userSocket, event.getUser(), destination, startPosition, event.getFilesize());
		}
	}

	/**
	 * Send a chat request using {@link Configuration#isDccPassiveRequest()}
	 * <p>
	 * @param receiver The user to chat with
	 * @return An active {@link SendChat}
	 * @throws IOException If an error occurred during connection
	 * @throws InterruptedException If passive connection was interrupted
	 * @throws DccException If a timeout is reached or the bot is shutting down
	 */
	public SendChat sendChat(User receiver) throws IOException, InterruptedException {
		return sendChat(receiver, bot.getConfiguration().isDccPassiveRequest());
	}

	/**
	 * Send a chat request using passive parameter
	 * <p>
	 * @param receiver The user to chat with
	 * @param passive Whether to connect passively
	 * @return An active {@link SendChat}
	 * @throws IOException If an error occurred during connection
	 * @throws InterruptedException If passive connection was interrupted
	 * @throws DccException If a timeout is reached or the bot is shutting down
	 */
	public SendChat sendChat(User receiver, boolean passive) throws IOException, InterruptedException {
		checkNotNull(receiver, "Receiver user cannot be null");
		int dccAcceptTimeout = bot.getConfiguration().getDccAcceptTimeout();
		if (passive) {
			String chatToken = Integer.toString(TOKEN_RANDOM.nextInt(TOKEN_RANDOM_MAX));
			PendingSendChatPassive pendingChat = new PendingSendChatPassive(receiver, chatToken);
			CountDownLatch countdown = new CountDownLatch(1);
			synchronized (pendingSendPassiveChat) {
				pendingSendPassiveChat.put(pendingChat, countdown);
			}
			InetAddress publicAddress = getRealDccPublicAddress();
			bot.sendDCC().chatPassiveRequest(receiver.getNick(), publicAddress, chatToken);

			//Wait for the user to acknowledge
			log.debug("Sent DCC send chat request to user {} ({}ms timeout) for passive connect info using public address {}", 
					receiver.getNick(), 
					bot.getConfiguration().getDccAcceptTimeout(),
					publicAddress);
			if (!countdown.await(dccAcceptTimeout, TimeUnit.MILLISECONDS))
				throw new DccException(DccException.Reason.ChatTimeout, receiver, "");
			if (shuttingDown)
				throw new DccException(DccException.Reason.ChatCancelled, receiver, "");
			Socket chatSocket = new Socket(pendingChat.getReceiverAddress(), pendingChat.getReceiverPort());
			return bot.getConfiguration().getBotFactory().createSendChat(bot, receiver, chatSocket);
		} else {
			//Get the user to connect to us
			ServerSocket serverSocket = createServerSocket(receiver);
			InetAddress publicAddress = getRealDccPublicAddress(serverSocket);
			bot.sendDCC().chatRequest(receiver.getNick(), publicAddress, serverSocket.getLocalPort());

			//Wait for user to connect
			log.debug("Sent DCC send chat request to user {} ({}ms timeout) to connect on public address {}:{}, local address {}", 
					receiver.getNick(), 
					bot.getConfiguration().getDccAcceptTimeout(),
					publicAddress, 
					serverSocket.getLocalPort(),
					serverSocket.getLocalSocketAddress());
			Socket userSocket = serverSocket.accept();
			log.debug("Recieved connection");
			serverSocket.close();
			return bot.getConfiguration().getBotFactory().createSendChat(bot, receiver, userSocket);
		}
	}

	/**
	 * Send file using {@link Configuration#isDccPassiveRequest() }
	 * <p>
	 * @param file The file to send
	 * @param receiver The user to send the file to
	 * @return An active {@link SendFileTransfer}
	 * @throws IOException If an error occurred during connecting
	 * @throws DccException If a timeout is reached or the bot is shutting down
	 * @throws InterruptedException If passive connection was interrupted
	 */
	public SendFileTransfer sendFile(File file, User receiver) throws IOException, DccException, InterruptedException {
		return sendFile(file, receiver, bot.getConfiguration().isDccPassiveRequest());
	}

	/**
	 * Send file using {@link Configuration#isDccPassiveRequest() }
	 * <p>
	 * @param file The file to send
	 * @param receiver The user to send the file to
	 * @param passive Whether to connect passively
	 * @return An active {@link SendFileTransfer}
	 * @throws IOException If an error occurred during connecting
	 * @throws DccException If a timeout is reached or the bot is shutting down
	 * @throws InterruptedException If passive connection was interrupted
	 */
	public SendFileTransfer sendFile(File file, User receiver, boolean passive) throws IOException, DccException, InterruptedException {
		checkNotNull(file, "Source file cannot be null");
		checkNotNull(receiver, "Receiver cannot be null");
		checkArgument(file.exists(), "File must exist");

		//Make the filename safe to send
		String safeFilename = file.getName();
		if (safeFilename.contains(" "))
			if (bot.getConfiguration().isDccFilenameQuotes())
				safeFilename = "\"" + safeFilename + "\"";
			else
				safeFilename = safeFilename.replace(" ", "_");

		if (passive) {
			String transferToken = Integer.toString(TOKEN_RANDOM.nextInt(TOKEN_RANDOM_MAX));
			CountDownLatch countdown = new CountDownLatch(1);
			PendingSendFileTransferPassive pendingPassiveTransfer = new PendingSendFileTransferPassive(receiver, safeFilename, transferToken);
			synchronized (pendingSendTransfers) {
				pendingSendPassiveTransfers.put(pendingPassiveTransfer, countdown);
			}
			InetAddress publicAddress = getRealDccPublicAddress();
			bot.sendDCC().filePassiveRequest(receiver.getNick(), safeFilename, publicAddress, file.length(), transferToken);

			//Wait for user to acknowledge
			log.debug("Sent DCC send file request to user {} ({}ms timeout) for passive connect info using public address {} for file {}", 
					receiver.getNick(), 
					bot.getConfiguration().getDccAcceptTimeout(),
					publicAddress, 
					file.getAbsolutePath());
			if (!countdown.await(bot.getConfiguration().getDccAcceptTimeout(), TimeUnit.MILLISECONDS))
				throw new DccException(DccException.Reason.FileTransferTimeout, receiver, "File: " + file.getAbsolutePath());
			if (shuttingDown)
				throw new DccException(DccException.Reason.FileTransferCancelled, receiver, "Transfer of file " + file.getAbsolutePath()
						+ " canceled due to bot shutdown");
			Socket transferSocket = new Socket(pendingPassiveTransfer.getReceiverAddress(), pendingPassiveTransfer.getReceiverPort());
			return bot.getConfiguration().getBotFactory().createSendFileTransfer(bot, transferSocket, receiver, file, pendingPassiveTransfer.getStartPosition());
		} else {
			//Try to get the user to connect to us
			final ServerSocket serverSocket = createServerSocket(receiver);
			PendingSendFileTransfer pendingSendFileTransfer = new PendingSendFileTransfer(receiver, safeFilename, serverSocket.getLocalPort());
			synchronized (pendingSendTransfers) {
				pendingSendTransfers.add(pendingSendFileTransfer);
			}
			InetAddress publicAddress = getRealDccPublicAddress(serverSocket);
			bot.sendDCC().fileRequest(receiver.getNick(), safeFilename, publicAddress, serverSocket.getLocalPort(), file.length());

			//Wait for the user to connect
			log.debug("Sent DCC send file request to user {} ({}ms timeout) to connect on public address {}, local address {}, port {} for file {}", 
					receiver.getNick(), 
					bot.getConfiguration().getDccAcceptTimeout(),
					publicAddress, 
					serverSocket.getLocalSocketAddress(), 
					serverSocket.getLocalPort(),
					file.getAbsolutePath());
			Socket userSocket = serverSocket.accept();
			serverSocket.close();
			return bot.getConfiguration().getBotFactory().createSendFileTransfer(bot, userSocket, receiver, file, pendingSendFileTransfer.getPosition());
		}
	}

	/**
	 * Try to get a real InetAddress in this order:
	 * <ol>
	 * <li>{@link Configuration#getDccLocalAddress()}</li>
	 * <li>{@link Configuration#getLocalAddress()}</li>
	 * <li>{@link PircBotX#getLocalAddress()}</li>
	 * </ol>
	 */
	public InetAddress getRealDccLocalAddress(InetAddress destAddress) {
		//Issue #268: Workaround to give IPv6 users an IPv6 address, or return null to let the OS figure it out
		InetAddress address = bot.getConfiguration().getDccLocalAddress();
		address = (address != null && destAddress.getClass().equals(address.getClass())) ? address : bot.getConfiguration().getLocalAddress();
		address = (address != null && destAddress.getClass().equals(address.getClass())) ? address : bot.getLocalAddress();
		address = (address != null && destAddress.getClass().equals(address.getClass())) ? address : null;
		return address;
	}
	
	public InetAddress getRealDccLocalAddress() {
		InetAddress address = bot.getConfiguration().getDccLocalAddress();
		address = (address != null) ? address : bot.getConfiguration().getLocalAddress();
		address = (address != null) ? address : bot.getLocalAddress();
		return address;
	}
	
	/**
	 * Try to get a real InetAddress in this order:
	 * <ol>
	 * <li>{@link Configuration#getDccPublicAddress()}</li>
	 * <li>{@link #getRealDccLocalAddress() }</li>
	 * </ol>
	 */
	public InetAddress getRealDccPublicAddress() {
		InetAddress address = bot.getConfiguration().getDccPublicAddress();
		return (address != null) ? address : getRealDccLocalAddress();
	}
	
	/**
	 * Try to get a real InetAddress in this order:
	 * <ol>
	 * <li>{@link Configuration#getDccPublicAddress()}</li>
	 * <li>The given ServerSocket's address</li>
	 * </ol>
	 */
	public InetAddress getRealDccPublicAddress(ServerSocket ss) {
		InetAddress address = bot.getConfiguration().getDccPublicAddress();
		return (address != null) ? address : ss.getInetAddress();
	}

	protected ServerSocket createServerSocket(User user) throws IOException, DccException {
		InetAddress address = getRealDccLocalAddress();
		ImmutableList<Integer> dccPorts = bot.getConfiguration().getDccPorts();
		ServerSocket ss = null;
		if (dccPorts.isEmpty())
			// Use any free port.
			ss = new ServerSocket(0, 1, address);
		else {
			for (int currentPort : dccPorts)
				try {
					ss = new ServerSocket(currentPort, 1, address);
					// Found a port number we could use.
					break;
				} catch (Exception e) {
					// Do nothing; go round and try another port.
					log.debug("Failed to create server socket on port " + currentPort + ", trying next one", e);
				}
			if (ss == null)
				// No ports could be used.
				throw new DccException(DccException.Reason.DccPortsInUse, user, "Ports " + dccPorts + " are in use.");
		}
		ss.setSoTimeout(bot.getConfiguration().getDccAcceptTimeout());
		return ss;
	}

	protected static List<String> tokenizeDccRequest(String request) {
		int quotesIndexBegin = request.indexOf('"');
		if (quotesIndexBegin == -1)
			//Just use tokenizeLine
			return Utils.tokenizeLine(request);

		//This is a slightly modified version of Utils.tokenizeLine to parse
		//potential quotes in filenames
		int quotesIndexEnd = request.lastIndexOf('"');
		List<String> stringParts = new ArrayList<String>();
		int pos = 0, end;
		while ((end = request.indexOf(' ', pos)) >= 0) {
			if (pos >= quotesIndexBegin && end < quotesIndexEnd) {
				//We've entered the filename. Add and skip
				stringParts.add(request.substring(quotesIndexBegin, quotesIndexEnd + 1));
				pos = quotesIndexEnd + 2;
				continue;
			}
			stringParts.add(request.substring(pos, end));
			pos = end + 1;
			if (request.charAt(pos) == ':') {
				stringParts.add(request.substring(pos + 1));
				return stringParts;
			}
		}
		//No more spaces, add last part of line
		stringParts.add(request.substring(pos));
		return stringParts;
	}

	/**
	 * Shutdown any pending dcc transfers
	 */
	public void close() {
		//Shutdown open reverse dcc servers
		shuttingDown = true;
		int pendingCount = pendingReceiveTransfers.values().size() + pendingSendPassiveTransfers.values().size();
		if (pendingCount > 0) {
			log.info("Terminating {} DCC transfers waiting to be accepted", pendingCount);
			for (CountDownLatch curCountdown : pendingReceiveTransfers.values())
				curCountdown.countDown();
			for (CountDownLatch curCountdown : pendingSendPassiveTransfers.values())
				curCountdown.countDown();
		}
	}

	public static String addressToInteger(InetAddress address) {
		if (address instanceof Inet6Address)
			return address.getHostAddress();
		return new BigInteger(1, address.getAddress()).toString();
	}

	public static InetAddress parseRawAddress(String rawAddress) throws UnknownHostException {
		//Some IPv6 clients are sending the full IPv6 address instead of a bigint
		if (rawAddress.contains(":"))
			return Inet6Address.getByName(rawAddress);

		//Convert the rawInteger into something usable
		BigInteger bigIp = new BigInteger(rawAddress);
		byte[] addressBytes = bigIp.toByteArray();

		//If there aren't enough bytes, pad with 0 byte
		if (addressBytes.length == 5)
			//Has signum, strip it
			addressBytes = Arrays.copyOfRange(addressBytes, 1, 5);
		else if (addressBytes.length < 4) {
			byte[] newAddressBytes = new byte[4];
			newAddressBytes[3] = addressBytes[0];
			newAddressBytes[2] = (addressBytes.length > 1) ? addressBytes[1] : (byte) 0;
			newAddressBytes[1] = (addressBytes.length > 2) ? addressBytes[2] : (byte) 0;
			newAddressBytes[0] = (addressBytes.length > 3) ? addressBytes[3] : (byte) 0;
			addressBytes = newAddressBytes;
		} else if (addressBytes.length == 17)
			//Has signum, strip it
			addressBytes = Arrays.copyOfRange(addressBytes, 1, 17);
		try {
			return InetAddress.getByAddress(addressBytes);
		} catch (UnknownHostException ex) {
			throw new RuntimeException("Can't get InetAdrress version of int IP address " + rawAddress + " (bytes: " + Arrays.toString(addressBytes) + ")", ex);
		}
	}

	@Data
	protected static class PendingRecieveFileTransfer {
		protected final IncomingFileTransferEvent event;
		protected long position;
	}

	@Data
	protected static class PendingSendFileTransfer {
		protected final User user;
		protected final String filename;
		protected final int port;
		protected long position = 0;
	}

	@Data
	protected static class PendingSendFileTransferPassive {
		protected final User user;
		protected final String filename;
		protected final String transferToken;
		protected long startPosition = 0;
		protected InetAddress receiverAddress;
		protected int receiverPort;
	}

	@Data
	protected static class PendingSendChatPassive {
		protected final User user;
		protected final String chatToken;
		protected InetAddress receiverAddress;
		protected int receiverPort;
	}

	public static void main(String[] args) throws UnknownHostException {
		log.debug("IP: {}", parseRawAddress("134744072"));
	}
}
