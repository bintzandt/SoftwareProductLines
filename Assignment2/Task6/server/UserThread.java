import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class UserThread extends Thread {
	private Socket socket;
	private Server server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public UserThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
	}

	public void run() {
		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
			this.oos = new ObjectOutputStream(socket.getOutputStream());

			Message userNameMessage = (Message) ois.readObject();
			String userName = userNameMessage.getMessageBody();
			
			printUsers(userName);
			server.addUserName(userNameMessage.getMessageBody());
			
			Message serverMessage = new Message("New user connected: " + userName);
			server.broadcast(serverMessage, this);

			String clientMessage;
			Message m;
			do {				
				m = (Message) ois.readObject();
				clientMessage = m.getMessageBody();
				
				
				serverMessage = new Message("[" + userName + "]: " + clientMessage);
				server.broadcast(serverMessage, this);

			} while (!clientMessage.equals("bye"));

			server.removeUser(userName, this);
			socket.close();

			serverMessage = new Message(userName + " has quitted.");
			server.broadcast(serverMessage, this);

		} catch (Exception ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Sends all currently connected users to the new user
	 */
	void printUsers(String userName){
		try {
			if (server.hasUsers()){
				this.oos.writeObject(new Message(
					"Welcome " + userName + ", currently connected users are: " + server.getUserNames()
				));
			} else {
				this.oos.writeObject(new Message(
					"Welcome " + userName + ", no other users are currently connected"
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
}