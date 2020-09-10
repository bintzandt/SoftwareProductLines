import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author www.codejava.net
 */
public class ReadThread extends Thread {
	private final Socket socket;
	private ObjectInputStream ois;

	public ReadThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Error creating object input stream " + ex.getMessage());
			ex.printStackTrace();
		}
		
		while (true) {
			try {
				Message m = (Message) ois.readObject();
	            System.out.println(m.getMessageBody());
			} catch (Exception ex) {
				System.out.println("Error reading from server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}