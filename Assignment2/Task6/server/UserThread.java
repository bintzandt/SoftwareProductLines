import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class UserThread extends Thread {
	private final String serverPassword = "SUPERSECRET";

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

			boolean authenticated = false;
			String userName = "";
			do {
				Message userNameMessage = (Message) ois.readObject();
				if (userNameMessage instanceof LoginMessage) {
					LoginMessage lm = (LoginMessage) userNameMessage;
					userName = lm.getUsername();
					String password = lm.getPassword();

					if (password.equals(serverPassword)) {
						authenticated = true;
					} else {
						System.out.println("User " + userName + " attempted login with incorrect password");
						sendMessage(new ChatMessage("Server", "Incorrect password! Please reconnect, connection closed.", Color.RED));
						socket.close();
						return;
					}
				}
			} while (!authenticated);

			printUsers(userName);
			server.addUserName(userName);

			Message serverMessage = new ChatMessage("Server", "New user connected: " + userName, Color.YELLOW);
			server.broadcast(serverMessage, this);

			String clientMessage = "";
			Message m;
			boolean saidBye = false;
			do {
				m = (Message) ois.readObject();
				if (m instanceof ChatMessage) {
					clientMessage = ((ChatMessage) m).getPlainMessage();
				}

				if (m instanceof ChatEncryptedMessage) {
					// Shhhhh, it's end-to-end encrypted I promise
					clientMessage = ((ChatEncryptedMessage) m).getDecryptedMessage().getPlainMessage();
				}


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

			serverMessage = new ChatMessage("Server", userName + " has quitted.", Color.YELLOW);
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
				this.oos.writeObject(new ChatMessage(
						"Server",
						"Welcome " + userName + ", currently connected users are: " + server.getUserNames(),
						Color.YELLOW
				));
			} else {
				this.oos.writeObject(new ChatMessage(
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
}