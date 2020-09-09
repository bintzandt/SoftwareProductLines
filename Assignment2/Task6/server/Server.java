import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	private int port;
	private Set<String> userNames = new HashSet<>();
	private Set<UserThread> userThreads = new HashSet<>();

	public Server( int port ){
		this.port = port;
	}

	public void execute(){
		try (ServerSocket serverSocket = new ServerSocket( this.port ) ){
			System.out.println("Server is listening on port " + this.port );

			// Keep listening for new clients.
			while( true ){
				Socket socket = serverSocket.accept();
				System.out.println("New user connected!");

				UserThread newUser = new UserThread(socket, this);
				userThreads.add( newUser );
				newUser.start();
			}
		} catch( IOException ex ){
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
		server.execute();
	}

	void broadcast( String message, UserThread excludeUser ){
		for (UserThread user : userThreads){
			if (user != excludeUser){
				user.sendMessage(message);
			}
		}
	}

	void addUserName( String userName ){
		userNames.add(userName);
	}

	void removeUser( String userName, UserThread user){
		boolean removed = userNames.remove(userName);
		if (removed){
			userThreads.remove(user);
			System.out.println("The user " + userName + " has quitted");
		}
	}

	Set<String> getUserNames() {
		return this.userNames;
	}

	boolean hasUsers(){
		return ! this.userNames.isEmpty();
	}

}