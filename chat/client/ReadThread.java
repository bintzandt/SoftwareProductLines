package chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import chat.messages.*;
import chat.config.*;

/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author www.codejava.net
 */
public class ReadThread extends Thread {
	private final Socket socket;
	private final Client client;
	private ObjectInputStream ois;

	public ReadThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
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

				if (m instanceof ChatMessage) {
					ChatMessage cm = (ChatMessage) m;

					System.out.println("\n" + cm.getMessageBody());
					client.getLogger().writeln(cm.getPlainMessage());
				} else if (m instanceof ChatEncryptedMessage) {
					ChatMessage cm = ((ChatEncryptedMessage) m).getDecryptedMessage();

					System.out.println("\n" + cm.getMessageBody());
					client.getLogger().writeln(cm.getPlainMessage());
				}

				// prints the username after displaying the server's message
				if (client.getUserName() != null) {
					System.out.print("[" + client.getUserName() + "]: ");
				}

			} catch (Exception ex) {
				System.out.println("Error reading from server: " + ex.getMessage());
				ex.printStackTrace();
				break;
			}
		}

		client.getLogger().close();
	}
}