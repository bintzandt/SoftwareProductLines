import java.io.Console;

class ConsoleView implements ViewInterface {
	private final Console console;

	public ConsoleView(){
		this.console = System.console();
	}

	public void output( String message ){
		System.out.println( "\n" + message );
	}

	public String waitForInput( String question ){
		return this.console.readLine( "\n" + question );
	}
}