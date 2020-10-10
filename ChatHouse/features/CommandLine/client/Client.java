import java.io.Console;

public class Client {
	private final Console console;

	public Client(String hostname, int port) {
		this.console = System.console();
	}

	public void viewOutput(String message) {
		System.out.println("\r" + message + "\n");
	}
	
	public void viewOutput(String message, String username) {
		System.out.println("\r" + message + "\n");
		System.out.print("[" + username + "]: ");
	}

	public String viewWaitForInput(String question) {
		return this.console.readLine("\n" + question);
	}
}