package chat.client;

import java.io.Console;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import chat.messages.*;
import chat.config.*;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author www.codejava.net
 */
public class WriteThread extends Thread {
	private Socket socket;
	private Client client;
	private ObjectOutputStream oos;
	private Color color;

	public WriteThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			System.out.println("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void handleCommand(String text) {
		String[] words = text.split("\\s+");

		if (words[0].equals("/config")) {
			if (words.length < 3) {
				System.out.print(
						"Current configuration:\n"
						+ "colors = " + Config.COLORED_MESSAGES + "\n"
						+ "send_username = " + Config.SEND_MY_USERNAME + "\n"
						+ "chatlog = " + Config.USER_CHATLOG + "\n"
						+ "encrypt_rot13 = " + Config.ENCRYPT_ROT13 + "\n"
						+ "encrypt_revert = " + Config.ENCRYPT_REVERT + "\n"
				);
			} else {
				boolean value = Boolean.parseBoolean(words[2]);
				boolean recognized = true;
				boolean encryption_option = false;

				if (words[1].equals("colors")) {
					Config.COLORED_MESSAGES = value;
				} else if (words[1].equals("send_username")) {
					Config.SEND_MY_USERNAME = value;
				} else if (words[1].equals("chatlog")) {
					Config.USER_CHATLOG = value;
					client.getLogger().setEnabled(value);
				} else if (words[1].equals("encrypt_rot13")) {
					Config.ENCRYPT_ROT13 = value;
					encryption_option = true;
				} else if (words[1].equals("encrypt_revert")) {
					Config.ENCRYPT_REVERT = value;
					encryption_option = true;
				} else {
					System.out.println("ERROR: Config " + words[1] + " not recognized! Try /config");
					recognized = false;
				}
				if (recognized) {
					System.out.println("Config " + words[1] + " set to " + value);
					if (encryption_option && !value) {
						System.out.println("WARNING! Disabling our industry-grade encryption (ROT13, revert) is");
						System.out.println("not recommended! This will weaken the privacy of your conversations.");
					}
				}
			}
		} else if (words[0].equals("/color")) {
			if (words.length < 2) {
				System.out.println("Please input a new color");
			} else {
				try {
					color = Color.valueOf(words[1].toUpperCase());
					System.out.println("Your color has been changed to " + color.name());
				} catch (IllegalArgumentException ex) {
					System.out.println("Invalid color, please choose one of the following colors: " + Color.getColorOptions());
				}
			}
		}
	}

	public void run() {

		Console console = System.console();

		String userName = console.readLine("\nEnter your name: ");
		client.setUserName(userName);

		do {
			try {
				String colorString = console.readLine("\nEnter you text color: ").toUpperCase();
				color = Color.valueOf(colorString);
			} catch (IllegalArgumentException ex) {
				System.out.println("Invalid color, please choose one of the following colors: " + Color.getColorOptions());
				continue;
			}
			break;
		} while (true);
		System.out.println("You chose " + color.name() + ", all messages you send will be displayed in this color!");

		try {
			String secret = console.readLine("\nEnter the secret password: ");

			LoginMessage loginMessage = new LoginMessage(userName, secret);
			oos.writeObject(loginMessage);

			System.out.println("Joining the chat server as " + userName);
		} catch (IOException ex) {
			System.out.println("Error sending login message to server: " + ex.getMessage());
			ex.printStackTrace();
			return; // Disconnect?
		}

		String text;
		Message m;
		do {
			text = console.readLine("[" + userName + "]: ");
			if (text.startsWith("/")) {
				handleCommand(text);
			} else {
				String usernameToSend = Config.SEND_MY_USERNAME ? userName : "anonymous";
				try {
					m = new ChatEncryptedMessage(usernameToSend, text, color);
					oos.writeObject(m);
				} catch (IOException ex) {
					System.out.println("Error sending message to server: " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		} while (!text.equals("bye"));

		try {
			socket.close();
		} catch (IOException ex) {

			System.out.println("Error writing to server: " + ex.getMessage());
		}
	}
}