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
	private String userName;
	private Logger logger;
	
	public ViewInterface view;

	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		
		// #if GUI
//@		view = new GUIView();
		// #else
		view = new ConsoleView();
		// #endif

		// #if ChatLog
//@		this.logger = new Logger("spl_client.log");
		// #endif
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);
			System.out.println("Connected to the chat server");

			new ReadThread(socket, this).start();
			System.out.println("Reader started");
			System.out.flush();
			new WriteThread(socket, this).start();
			System.out.println("Writer started");

		} catch (UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O Error: " + ex.getMessage());
		}

	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
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
}
