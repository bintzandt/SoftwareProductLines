public class ChatMessage extends Message {
	private static final long serialVersionUID = 1L;
	private final Color color;
	private final String username;
	private final char[] m;

	/**
	 * Sent by a client, broadcast by the server to all other clients
	 */

	public ChatMessage(String username, char[] m, Color color) {
		this.username = username;
		this.m = m;
		this.color = color;
	}

	public ChatMessage(String username, String m, Color color) {
		this.username = username;
		this.m = m.toCharArray();
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public ChatEncryptedMessage encrypt() {
		return new ChatEncryptedMessage(username, m, color);
	}

	public String getMessageBody() {
		return this.color + getPlainMessage() + Color.RESET;
	}

	public String getPlainMessage() {
		return "[" + username + "]: " + new String(this.m);
	}

	@Override
	public String toString() {
		return String.format("Message: " + new String(m));
	}
}
