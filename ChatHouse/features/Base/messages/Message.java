import java.io.Serializable;

public class Message implements Serializable {
	protected static final long serialVersionUID = 1L;
	
	protected final String username;
	protected char[] m;
	protected boolean isEncrypted;
	
	public Message(String username, char[] m, Color color) {
		this.username = username;
		this.m = m;
		this.isEncrypted = false;
	}
	
	public Message(String username, String m, Color color) {
		this(username, m.toCharArray(), color);
	}
	
	public final String getMessageBody() {
		return getPlainMessage();
	}

	public final String getPlainMessage() {
		return "[" + username + "]: " + new String(this.m);
	}
	
	public void encrypt() {}
	
	public void decrypt() {}

	@Override
	public final String toString() {
		return String.format("Message: " + new String(m));
	}
}
