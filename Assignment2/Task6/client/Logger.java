import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	private String filepath;
	private PrintWriter writer;
	private boolean available = false;

	public Logger(String filename) {
		this.filepath = System.getProperty("user.home") + "/" + filename;
		try {
			writer = new PrintWriter(new FileWriter(filepath, true));
			System.out.println("Logging to " + filepath);
			available = true;
		} catch (IOException e) {
			System.out.println("Logging to " + filepath + " not available!");
		}
	}

	private String getDateTime() {
		return java.time.LocalDateTime.now().toString();
	}

	public void writeln(String string) {
		if (!available) return;
		writer.println(getDateTime() + " - " + string);
	}

	public void close() {
		if (!available) return;
		writer.close();
	}
}
