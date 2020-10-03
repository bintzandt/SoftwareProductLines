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
	
	private PluginManager pluginManager;

	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		this.pluginManager = PluginManager.getInstance();
		
		// Check whether there is one and only one UI plugin
		int uiPlugins = 0;
		for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
			if (plugin instanceof ClientUIPlugin)
				uiPlugins++;
		}
		if (uiPlugins != 1) {
			System.out.println("There should be one UI plugin, " + uiPlugins + " found! Aborting.");
			System.exit(1);
		}

		this.logger = new Logger("spl_client.log");
	}
	
	public String viewsWaitForInput( String question ) {
		for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
			if (plugin instanceof ClientUIPlugin) {
				String input = ((ClientUIPlugin) plugin).viewWaitForInput(question);
				
				if (input != null)
					return input;
			}
		}
		
		// We shouldn't be here, we checked for one UI plugin!
		assert(false);
		return "";
	}
	
	public void viewsOutput(String message) {
		for (ClientPlugin plugin : this.pluginManager.getClientPlugins()) {
			if (plugin instanceof ClientUIPlugin) {
				((ClientUIPlugin) plugin).viewOutput(message);
			}
		}
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);
			viewsOutput("Connected to the chat server");

			new ReadThread(socket, this).start();
			viewsOutput("Reader started");
			new WriteThread(socket, this).start();
			viewsOutput("Writer started");

		} catch (UnknownHostException ex) {
			viewsOutput("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			viewsOutput("I/O Error: " + ex.getMessage());
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
