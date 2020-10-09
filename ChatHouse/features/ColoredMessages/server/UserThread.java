public class UserThread extends Thread {
	
	private void clientStarted(){
		Color color;
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
		writeThread.setColor(color);
		client.viewOutput("You chose " + color.name() + ", all messages you send will be displayed in this color!");
	}
	
}