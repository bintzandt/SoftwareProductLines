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
	
	// #if ChatLog
//@	private Logger logger;
	// #endif
	
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
			view.output("Connected to the chat server");

			new ReadThread(socket, this).start();
			view.output("Reader started");
			new WriteThread(socket, this).start();
			view.output("Writer started");

		} catch (UnknownHostException ex) {
			view.output("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			view.output("I/O Error: " + ex.getMessage());
		}

	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	// #if ChatLog
//@	public Logger getLogger() {
//@		return logger;
//@	}
	// #endif


	public static void main(String[] args) {
		if (args.length < 2) return;

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		Client client = new Client(hostname, port);
		client.execute();
	}
}
