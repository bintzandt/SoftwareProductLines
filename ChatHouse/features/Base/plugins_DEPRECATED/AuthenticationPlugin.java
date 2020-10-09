import java.io.IOException;
		
public class AuthenticationPlugin extends Plugin {
	private final String serverPassword = "SUPERSECRET";

	public AuthenticationPlugin() {
		this.clientPlugin = new ClientAuthenticationPlugin();
		this.serverPlugin = new ServerAuthenticationPlugin();
	}

	class ClientAuthenticationPlugin extends ClientPlugin {
		@Override
		public void afterClientCreation(Client client, WriteThread writeThread) {
			String userName = client.viewWaitForInput("Enter your name: ");
			client.setUserName(userName);
			writeThread.setUsername(userName);
			try {
				String secret = client.viewWaitForInput("Enter the secret password: ");

				LoginMessage loginMessage = new LoginMessage(userName, secret);
				writeThread.getOos().writeObject(loginMessage);

				client.viewOutput("Joining the chat server as " + userName);
			} catch (IOException ex) {
				client.viewOutput("Error sending login message to server: " + ex.getMessage());
				ex.printStackTrace();
				return; // Disconnect?
			}
		}
	}

	class ServerAuthenticationPlugin extends ServerPlugin {
		@Override
		public void onClientStarted(UserThread newUser) {
			try {
				LoginMessage userNameMessage = (LoginMessage) newUser.ois.readObject();
				if (userNameMessage instanceof LoginMessage) {
					LoginMessage lm = (LoginMessage) userNameMessage;
					String userName = lm.getUsername();
					String password = lm.getPassword();

					newUser.setUserName(userName);

					if (password.equals(serverPassword)) {
						newUser.setAuthenticated(true);
					} else {
						System.out.println("User " + userName + " attempted login with incorrect password");
						newUser.sendMessage(new Message("Server",
								"Incorrect password! Please reconnect, connection closed.", Color.RED));
						newUser.getSocket().close();
						return;
					}
				}
			} catch (Exception ex) {
				System.out.println("Error in UserThread: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
