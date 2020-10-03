public class ChatMessageDEPRECATED extends Message {
	private static final long serialVersionUID = 1L;

	/**
	 * Sent by a client, broadcast by the server to all other clients
	 */

	public ChatMessageDEPRECATED(String username, char[] m, Color color) {
		super(username, m, color);
	}

	public ChatMessageDEPRECATED(String username, String m, Color color) {
		super(username, m, color);
	}

	public ChatMessageDEPRECATED encrypt() {
		return new ChatMessageDEPRECATED(username, m, color);
	}
}
