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
		String secret = "";

		do {
			secret = console.readLine("\nEnter the secret password: ");
		} while ( ! client.verifyPassword( secret ) );

		String userName = console.readLine("\nEnter your name: ");
		client.setUserName(userName);

		String color = console.readLine("\nEnter you text color: ");
		
		try {
			Message userNameMessage = new Message(userName, color);
			oos.writeObject(userNameMessage);
			System.out.println("Succesfully joined chat server as " + userName);
		} catch (IOException ex) {
			System.out.println("Error sending username message to server: " + ex.getMessage());
			ex.printStackTrace();
		}

		String text;
		Message m;
		do {
			text = console.readLine("[" + userName + "]: ");
			try {
				m = new Message(text);
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