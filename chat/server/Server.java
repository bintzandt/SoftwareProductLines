package chat.server;

import java.io.*;
import java.net.*;
import java.util.*;

// Import classes from other packages.
import chat.logger.Logger;
import chat.config.*;
import chat.messages.Message;

public class Server {
	private int port;
	private Set<String> userNames = new HashSet<>();
	private Observer observer;
	
	private Logger logger;

	public Server(int port) {
		this.port = port;
		logger = new Logger("spl_server.log", Config.SERVER_CHATLOG);
		this.observer = new Observer();
	}

	public void execute() {
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			System.out.println("Server is listening on port " + this.port);

			// Keep listening for new clients.
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New user connected!");
				
				UserThread newUser = new UserThread(socket, this);

				// Add the new thread to the observer.
				this.observer.addObserver( newUser );

				newUser.start();
			}
		} catch (IOException ex) {
			System.out.println("Error in the server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntax: java Server <port-number>");
			System.exit(0);
		}

		int port = Integer.parseInt(args[0]);

		Server server = new Server(port);
		server.execute();
	}

	void broadcast(Message message, UserThread excludeUser) {
		this.observer.notifyObservers( message, excludeUser );
	}

	void log(String message) {
		logger.writeln(message);
	}

	void addUserName(String userName) {
		userNames.add(userName);
	}

	void removeUser(String userName, UserThread user) {
		boolean removed = userNames.remove(userName);
		if (removed) {
			this.observer.removeObserver( user );
			System.out.println("The user " + userName + " has quitted");
		}
	}

	Set<String> getUserNames() {
		return this.userNames;
	}

	boolean hasUsers() {
		return !this.userNames.isEmpty();
	}

}