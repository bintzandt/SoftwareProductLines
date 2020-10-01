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
	private PluginManager pluginManager;

	public WriteThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
		this.pluginManager = PluginManager.getInstance();

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			client.view.output("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {

		Console console = System.console();

		String userName = client.view.waitForInput("Enter your name: ");
		client.setUserName(userName);
		color = Color.RESET;

		try {
			String secret = client.view.waitForInput("Enter the secret password: ");

			LoginMessage loginMessage = new LoginMessage(userName, secret);
			oos.writeObject(loginMessage);

			client.view.output("Joining the chat server as " + userName);
		} catch (IOException ex) {
			client.view.output("Error sending login message to server: " + ex.getMessage());
			ex.printStackTrace();
			return; // Disconnect?
		}
		
		for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
			plugin.afterClientCreation(client, this);
		}

		String text;
		Message m;
		do {
			text = client.view.waitForInput("[" + userName + "]: ");
			
			String usernameToSend = userName;
					
			try {
				m = new Message(usernameToSend, text, color);
				for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
					text = plugin.changeMessage(text, m);
				}
				
				m = new Message(usernameToSend, text, color);
				oos.writeObject(m);
			} catch (IOException ex) {
				client.view.output("Error sending message to server: " + ex.getMessage());
				ex.printStackTrace();
			}

		} while (!text.equals("bye"));

		try {
			socket.close();
		} catch (IOException ex) {

			client.view.output("Error writing to server: " + ex.getMessage());
		}
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
