import java.net.*;
import java.io.*;

/**
 * To run the client in a command prompt use: javac Client.java & java Client localhost 8080
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 * @author www.codejava.net
 */
public class Client {
	private String hostname;
	private int port;
	private String userName;
	private final String password = "SUPERSECRET";

	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);
			System.out.println("Connected to the chat server");

			new ReadThread(socket).start();
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

	String getUserName() {
		return this.userName;
	}

	Boolean verifyPassword( String password ) {
		if ( this.password.equals( password ) ){
			System.out.println("Password correct, go ahead!");
			return true;
		}

		System.out.println("\nPassword incorrect, try again!");
		return false;
	}


	public static void main(String[] args) {
		if (args.length < 2) return;

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		Client client = new Client(hostname, port);
		client.execute();
	}
}