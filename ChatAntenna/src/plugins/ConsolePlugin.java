import java.io.Console;

public class ConsolePlugin extends Plugin {
	public ConsolePlugin() {
		this.clientPlugin = new ClientConsolePlugin();
		this.serverPlugin = new ServerConsolePlugin();
	}
	
	public class ClientConsolePlugin extends ClientUIPlugin {
		private final Console console;

		public ClientConsolePlugin() {
			this.console = System.console();
		}

		public void viewOutput(String message) {
			System.out.println("\n" + message);
		}

		public String viewWaitForInput(String question) {
			return this.console.readLine("\n" + question);
		}
	}
	
	public class ServerConsolePlugin extends ServerPlugin {}
}
