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
	private String userName;

	public WriteThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
		this.pluginManager = PluginManager.getInstance();
		this.userName = "anonymous";

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			client.viewsOutput("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {
		for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
			plugin.beforeClientCreation(client, this);
		}

		// Client creation?
		color = Color.RESET;
		
		for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
			plugin.afterClientCreation(client, this);
		}

		String text;
		Message m;
		do {
			text = client.viewsWaitForInput("[" + this.userName + "]: ");
			
			String usernameToSend = this.userName;
					
			try {
				m = new Message(usernameToSend, text, color);
				for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
					text = plugin.changeMessage(text, m);
				}
				
				m = new Message(usernameToSend, text, color);
				oos.writeObject(m);
			} catch (IOException ex) {
				client.viewsOutput("Error sending message to server: " + ex.getMessage());
				ex.printStackTrace();
			}

		} while (!text.equals("bye"));

		try {
			socket.close();
		} catch (IOException ex) {

			client.viewsOutput("Error writing to server: " + ex.getMessage());
		}
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public ObjectOutputStream getOos(){
		return this.oos;
	}

	public void setUsername( String userName ){
		this.userName = userName;
	}
}
