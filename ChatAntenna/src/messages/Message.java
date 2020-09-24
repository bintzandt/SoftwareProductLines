import java.io.Serializable;

public abstract class Message implements Serializable {
	protected static final long serialVersionUID = 1L;
	
	protected final String username;
	protected char[] m;
	protected final Color color;
	
	public Message(String username, char[] m, Color color) {
		this.username = username;
		this.m = m;
		this.color = color;
	}
	
	public Message(String username, String m, Color color) {
		this(username, m.toCharArray(), color);
	}
	
	public final Color getColor() {
		return this.color;
	}
	
	public final String getMessageBody() {
		// #if ColoredMessages
//@		return this.color + getPlainMessage() + Color.RESET;
		// #else
		return getPlainMessage();
		// #endif
		
		
	}

	public final String getPlainMessage() {
		return "[" + username + "]: " + new String(this.m);
	}

	@Override
	public final String toString() {
		return String.format("Message: " + new String(m));
	}
}
