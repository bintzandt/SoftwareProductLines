import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 */
public class Client {
	private String hostname;
	private int port;
	
	private boolean block = false;
	
	private Logger logger;
	
	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;

		this.logger = new Logger("spl_client.log");
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);
			viewOutput("Connected to the chat server");

			new ReadThread(socket, this).start();
			viewOutput("Reader started");
			new WriteThread(socket, this).start();
			viewOutput("Writer started");

		} catch (UnknownHostException ex) {
			viewOutput("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			viewOutput("I/O Error: " + ex.getMessage());
		}

	}

	public String getUserName() {
		return "anonymous";
	}

	public Logger getLogger() {
		return logger;
	}


	public static void main(String[] args) {
		if (args.length < 2) return;

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		Client client = new Client(hostname, port);
		client.execute();
	}
	
	public void setBlock(boolean b) {
		this.block = b;
	}
	
	public boolean getBlock() {
		return this.block;
	}
}
