import java.io.Serializable;

public class Message implements Serializable {
	protected final Color color;
	
	public Message(String username, char[] m, Color color) {
		this.color = color;
	}
	
	public final Color getColor() {
		return this.color;
	}
	
	public final String getPlainMessage() {
		return "[" + username + "]: " + this.color +  new String(this.m) + Color.RESET;
	}
}