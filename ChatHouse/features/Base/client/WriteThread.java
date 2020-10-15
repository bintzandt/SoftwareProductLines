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
			client.viewOutput("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {
		color = Color.RESET;
		
		this.afterClientCreation();
		
		String text;
		Message m;
		do {
			text = client.viewWaitForInput("[" + this.client.getUserName() + "]: ");
			
			String usernameToSend = this.client.getUserName();
					
			try {
				m = new Message(usernameToSend, text, color);
				m.encrypt();
				
				oos.writeObject(m);
			} catch (IOException ex) {
				client.viewOutput("Error sending message to server: " + ex.getMessage());
				ex.printStackTrace();
			}

		} while (!text.equals("bye"));

		try {
			socket.close();
		} catch (IOException ex) {

			client.viewOutput("Error writing to server: " + ex.getMessage());
		}
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public ObjectOutputStream getOos(){
		return this.oos;
	}
	
	private void afterClientCreation() {}
}
