import org.fusesource.jansi.AnsiConsole;

import java.io.*;
import java.net.*;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author www.codejava.net
 */
public class ReadThread extends Thread {
	private BufferedReader reader;
	private Socket socket;
	private Client client;

	public ReadThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;

		try {
			InputStream input = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
		} catch (IOException ex) {
			System.out.println("Error getting input stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {
		AnsiConsole.systemInstall();
		while (true) {
			try {
				String response = reader.readLine();

				//System.out.println( ansi().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );
				System.out.println("\n" + ansi().fg(RED).a(response).reset());

				// prints the username after displaying the server's message
				if (client.getUserName() != null) {
					System.out.print("[" + client.getUserName() + "]: ");
				}
			} catch (IOException ex) {
				System.out.println("Error reading from server: " + ex.getMessage());
				ex.printStackTrace();
				break;
			}
		}
	}
}