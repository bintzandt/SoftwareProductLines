import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class Server {
	private int port;
	private Set<String> userNames = new HashSet<>();
	private Observer observer;
	
	private Logger logger;

	public Map<String, Plugin> plugins = new HashMap<String, Plugin>();

	public Server(int port) {
		this.port = port;
		this.observer = new Observer();
		logger = new Logger("spl_server.log");
	}

	public void execute() {
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			System.out.println("Server is listening on port " + this.port);

			// Keep listening for new clients.
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New user connected!");
				
				UserThread newUser = new UserThread(socket, this);

				// Add the new thread to the observer.
				this.observer.addObserver( newUser );

				newUser.start();
			}
		} catch (IOException ex) {
			System.out.println("Error in the server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntax: java Server <port-number>");
			System.exit(0);
		}

		int port = Integer.parseInt(args[0]);

		Server server = new Server(port);

		server.plugins.put("authentication", new AuthenticationPlugin() );

		server.execute();
	}

	void broadcast(Message message, UserThread excludeUser) {
		this.observer.notifyObservers( message, excludeUser );
	}

	void log(String message) {
		logger.writeln(message);
	}

	void addUserName(String userName) {
		userNames.add(userName);
	}

	void removeUser(String userName, UserThread user) {
		boolean removed = userNames.remove(userName);
		if (removed) {
			this.observer.removeObserver( user );
			System.out.println("The user " + userName + " has quitted");
		}
	}

	Set<String> getUserNames() {
		return this.userNames;
	}

	boolean hasUsers() {
		return !this.userNames.isEmpty();
	}

}