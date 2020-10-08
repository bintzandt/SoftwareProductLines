import java.io.Console;

public class Client {
	private final Console console;

	public Client(String hostname, int port) {
		this.console = System.console();
	}

	public void viewOutput(String message) {
		System.out.println("\n" + message);
	}

	public String viewWaitForInput(String question) {
		return this.console.readLine("\n" + question);
	}
}