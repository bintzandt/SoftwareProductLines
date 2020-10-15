public class UserThread extends Thread {
	public boolean authenticated = false;
	private final String serverPassword = "SUPERSECRET";
	
	private void clientStarted() {
		try {
			LoginMessage userNameMessage = (LoginMessage) this.ois.readObject();
			if (userNameMessage instanceof LoginMessage) {
				LoginMessage lm = (LoginMessage) userNameMessage;
				String userName = lm.getUsername();
				String password = lm.getPassword();

				this.setUserName(userName);

				if (password.equals(this.serverPassword)) {
					this.setAuthenticated(true);
				} else {
					System.out.println("User " + userName + " attempted login with incorrect password");
					this.sendMessage(new Message("Server",
							"Incorrect password! Please reconnect, connection closed.", Color.RED));
					this.getSocket().close();
					return;
				}
			}
		} catch (Exception ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void setUserName( String userName ){
		this.userName = userName;
	}
	
	public void setAuthenticated( boolean authenticated ){
		this.authenticated = authenticated;
	}
}