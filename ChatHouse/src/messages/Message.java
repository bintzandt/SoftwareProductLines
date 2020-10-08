import java.io.Serializable; 

public  class  Message  implements Serializable {
	
	protected static final long serialVersionUID = 1L;

	
	
	protected final String username;

	
	protected char[] m;

	
	protected final Color color;

	
	protected boolean isEncrypted;

	
	
	public Message(String username, char[] m, Color color) {
		this.username = username;
		this.m = m;
		this.color = color;
		this.isEncrypted = false;
	}

	
	
	public Message(String username, String m, Color color) {
		this(username, m.toCharArray(), color);
	}

	
	
	public final Color getColor() {
		return this.color;
	}

	
	
	public final String getMessageBody() {
		return getPlainMessage();
	}

	

	public final String getPlainMessage() {
		return "[" + username + "]: " + this.color +  new String(this.m) + Color.RESET;
	}

	
	
	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	

	@Override
	public final String toString() {
		return String.format("Message: " + new String(m));
	}


}
