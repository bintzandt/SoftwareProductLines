public class AuthenticationPlugin extends  Plugin {
	private final String serverPassword = "SUPERSECRET";

	public void execute(UserThread userThread) {
		try {
			LoginMessage userNameMessage = (LoginMessage) userThread.ois.readObject();
			if (userNameMessage instanceof LoginMessage) {
				LoginMessage lm = (LoginMessage) userNameMessage;
				String userName = lm.getUsername();
				String password = lm.getPassword();

				userThread.setUserName(userName);

				if (password.equals(serverPassword)) {
					userThread.setAuthenticated(true);
				} else {
					System.out.println("User " + userName + " attempted login with incorrect password");
					userThread.sendMessage(new Message("Server",
							"Incorrect password! Please reconnect, connection closed.", Color.RED));
					userThread.getSocket().close();
					return;
				}
			}
		} catch (Exception ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
