import java.io.IOException;

public class WriteThread extends Thread {
	private Client client;
	
	private void afterClientCreation() {
		original();
		this.client.setBlock(true);
		
		String userName = this.client.viewWaitForInput("Enter your name: ");
		this.client.setUserName(userName);

		try {
			String secret = this.client.viewWaitForInput("Enter the secret password: ");

			LoginMessage loginMessage = new LoginMessage(userName, secret);
			this.getOos().writeObject(loginMessage);

			this.client.viewOutput("Joining the chat server as " + userName);
			
			this.client.setBlock(false);
		} catch (IOException ex) {
			this.client.viewOutput("Error sending login message to server: " + ex.getMessage());
			ex.printStackTrace();
			return; // Disconnect?
		}
	}
}