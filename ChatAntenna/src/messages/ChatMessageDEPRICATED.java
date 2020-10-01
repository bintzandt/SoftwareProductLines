public class ChatMessageDEPRICATED extends Message {
	private static final long serialVersionUID = 1L;

	/**
	 * Sent by a client, broadcast by the server to all other clients
	 */

	public ChatMessageDEPRICATED(String username, char[] m, Color color) {
		super(username, m, color);
	}

	public ChatMessageDEPRICATED(String username, String m, Color color) {
		super(username, m, color);
	}

	public ChatMessageDEPRICATED encrypt() {
		return new ChatMessageDEPRICATED(username, m, color);
	}
}
