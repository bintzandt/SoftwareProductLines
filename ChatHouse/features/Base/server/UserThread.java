import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserThread extends Thread {
	private Socket socket;
	private Server server;
	
	public ObjectInputStream ois;
	public ObjectOutputStream oos;

	public String userName;
	public boolean authenticated;

	public UserThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;

		this.userName = "anonymous";
		this.authenticated = false;
	}

	public void run() {
		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
			this.oos = new ObjectOutputStream(socket.getOutputStream());

			this.clientStarted();

			printUsers(userName);
			server.addUserName(userName);
			
			Message serverMessage = new Message("Server", "New user connected: " + userName, Color.YELLOW);
			server.broadcast(serverMessage, this);
			
			String clientMessage = "";
			Message m;
			boolean saidBye = false;
			
			
			do {
				m = (Message) ois.readObject();
				clientMessage = m.getPlainMessage();
				

				// Shhhhh, it's end-to-end encrypted I promise
//					clientMessage = ((ChatEncryptedMessage) m).getDecryptedMessage().getPlainMessage();

				// Broadcast the original message
				server.broadcast(m, this);

				// Log the message server-side as well
				server.log(clientMessage);


				if (clientMessage.equals("bye")) {
					saidBye = true;
				}


			} while (!saidBye);

			server.removeUser(userName, this);
			socket.close();

			serverMessage = new Message("Server", userName + " has quitted.", Color.YELLOW);
			server.broadcast(serverMessage, this);

		} catch (Exception ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Sends all currently connected users to the new user
	 */
	void printUsers(String userName) {
		try {
			if (server.hasUsers()) {
				this.oos.writeObject(new Message(
						"Server",
						"Welcome " + userName + ", currently connected users are: " + server.getUserNames(),
						Color.YELLOW
				));
			} else {
				this.oos.writeObject(new Message(
						"Server",
						"Welcome " + userName + ", no other users are currently connected",
						Color.YELLOW
				));
			}
		} catch (IOException ex) {
			System.out.println("Error sending connected users to new user: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	/**
	 * Sends a message to the client.
	 */
	void sendMessage(Message message) {
		try {
			this.oos.writeObject(message);
		} catch (IOException ex) {
			System.out.println("Error sending message to user: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void setUserName( String userName ){
		this.userName = userName;
	}

	public void setAuthenticated( boolean authenticated ){
		this.authenticated = authenticated;
	}

	public Socket getSocket(){
		return this.socket;
	}

	private void clientStarted(){
		
	}
}