import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
			Config.view.output("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void handleCommand(String text) {
		String[] words = text.split("\\s+");

		if (words[0].equals("/config")) {
			if (words.length < 3) {
				Config.view.output(
						"Current configuration:\n"
						+ "colors = " + Config.COLORED_MESSAGES + "\n"
						+ "send_username = " + Config.SEND_MY_USERNAME + "\n"
						+ "chatlog = " + Config.USER_CHATLOG + "\n"
						+ "encrypt_rot13 = " + Config.ENCRYPT_ROT13 + "\n"
						+ "encrypt_revert = " + Config.ENCRYPT_REVERT
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
					Config.view.output("ERROR: Config " + words[1] + " not recognized! Try /config");
					recognized = false;
				}
				if (recognized) {
					Config.view.output("Config " + words[1] + " set to " + value);
					if (encryption_option && !value) {
						Config.view.output("WARNING! Disabling our industry-grade encryption (ROT13, revert) is");
						Config.view.output("not recommended! This will weaken the privacy of your conversations.");
					}
				}
			}
		} else if (words[0].equals("/color")) {
			if (words.length < 2) {
				Config.view.output("Please input a new color");
			} else {
				try {
					color = Color.valueOf(words[1].toUpperCase());
					Config.view.output("Your color has been changed to " + color.name());
				} catch (IllegalArgumentException ex) {
					Config.view.output("Invalid color, please choose one of the following colors: " + Color.getColorOptions());
				}
			}
		}
	}

	public void run() {
		String userName = Config.view.waitForInput("Enter your name: ");
		client.setUserName(userName);

		do {
			try {
				String colorString = Config.view.waitForInput("Enter you text color: ").toUpperCase();
				color = Color.valueOf(colorString);
			} catch (IllegalArgumentException ex) {
				Config.view.output("Invalid color, please choose one of the following colors: " + Color.getColorOptions());
				continue;
			}
			break;
		} while (true);
		Config.view.output("You chose " + color.name() + ", all messages you send will be displayed in this color!");

		try {
			String secret = Config.view.waitForInput("Enter the secret password: ");

			LoginMessage loginMessage = new LoginMessage(userName, secret);
			oos.writeObject(loginMessage);

			Config.view.output("Joining the chat server as " + userName);
		} catch (IOException ex) {
			Config.view.output("Error sending login message to server: " + ex.getMessage());
			ex.printStackTrace();
			return; // Disconnect?
		}

		String text;
		Message m;
		do {
			text = Config.view.waitForInput("[" + userName + "]: ");
			if (text.startsWith("/")) {
				handleCommand(text);
			} else {
				String usernameToSend = Config.SEND_MY_USERNAME ? userName : "anonymous";
				try {
					m = new ChatEncryptedMessage(usernameToSend, text, color);
					oos.writeObject(m);
				} catch (IOException ex) {
					Config.view.output("Error sending message to server: " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		} while (!text.equals("bye"));

		try {
			socket.close();
		} catch (IOException ex) {

			Config.view.output("Error writing to server: " + ex.getMessage());
		}
	}
}