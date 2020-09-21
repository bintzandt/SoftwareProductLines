public class ChatMessage extends Message {
	private static final long serialVersionUID = 1L;

	/**
	 * Sent by a client, broadcast by the server to all other clients
	 */

	public ChatMessage(String username, char[] m, Color color) {
		super(username, m, color);
	}

	public ChatMessage(String username, String m, Color color) {
		super(username, m, color);
	}

	public ChatEncryptedMessage encrypt() {
		return new ChatEncryptedMessage(username, m, color);
	}
}
