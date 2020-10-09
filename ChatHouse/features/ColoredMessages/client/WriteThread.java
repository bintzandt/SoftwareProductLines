public class WriteThread extends Thread {
	private Client client;
	
	private void afterClientCreation(){
		this.client.setBlock(true);
		do {
			try {
				String colorString = client.viewWaitForInput("Enter your text color: ").toUpperCase();
				color = Color.valueOf(colorString);
			} catch (IllegalArgumentException ex) {
				client.viewOutput("Invalid color, please choose one of the following colors: " + Color.getColorOptions());
				continue;
			}
			break;
		} while (true);
		this.setColor(color);
		client.viewOutput("You chose " + color.name() + ", all messages you send will be displayed in this color!");
		this.client.setBlock(false);
	}

}