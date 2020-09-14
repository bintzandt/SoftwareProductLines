import java.io.Console;
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
			System.out.println("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
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

			Message loginMessage = new LoginMessage(userName, secret);
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
			try {
				m = new ChatEncryptedMessage(userName, text, color);
				oos.writeObject(m);
			} catch (IOException ex) {
				System.out.println("Error sending message to server: " + ex.getMessage());
				ex.printStackTrace();
			}

		} while (!text.equals("bye"));

		try {
			socket.close();
		} catch (IOException ex) {

			System.out.println("Error writing to server: " + ex.getMessage());
		}
	}
}