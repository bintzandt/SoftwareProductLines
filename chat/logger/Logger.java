import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Logger {
	private String filepath;
	private PrintWriter writer;
	private boolean available = false;
	private boolean enabled;

	public Logger(String filename, boolean enabled) {
		this.filepath = System.getProperty("user.home") + "/" + filename;
		try {
			writer = new PrintWriter(new FileWriter(filepath, true));
			System.out.println("Logging to " + filepath);
			available = true;
		} catch (IOException e) {
			System.out.println("Logging to " + filepath + " not available!");
		}
		this.enabled = enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private String getDateTime() {
		return java.time.LocalDateTime.now().toString();
	}

	public void writeln(String string) {
		if (!available) return;
		if (!enabled) return;
		writer.println(getDateTime() + " - " + string);
		writer.flush();
	}

	public void close() {
		if (!available) return;
		writer.close();
	}
}
