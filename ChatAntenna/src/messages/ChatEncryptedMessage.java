public class ChatEncryptedMessage extends Message {
	private static final long serialVersionUID = 1L;

	private boolean is_encrypted_rot13;
	private boolean is_encrypted_revert;
	
	/**
	 * Sent by a client, broadcast by the server to all other clients
	 */

	public ChatEncryptedMessage(String username, char[] m, Color color) {
		super(username, m, color);

		// encrypt messages
		if (Config.ENCRYPT_ROT13) {
			this.m = this.applyROT13(this.m);
			is_encrypted_rot13 = true;
		}
		if (Config.ENCRYPT_REVERT) {
			this.m = this.applyRevert(this.m);
			is_encrypted_revert = true;
		}
	}

	public ChatEncryptedMessage(String username, String m, Color color) {
		this(username, m.toCharArray(), color);
	}

	public char[] applyROT13(char[] m) {
		char[] m_encrypted = m.clone();
		for (int i = 0; i < m_encrypted.length; i++) {
			char c = m_encrypted[i];
			if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
				m_encrypted[i] = (char) (c + 13);
			} else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
				m_encrypted[i] = (char) (c - 13);
			}
		}
		return m_encrypted;
	}

	public char[] applyRevert(char[] m) {
		return new StringBuilder(new String(m)).reverse().toString().toCharArray();
	}

	public ChatMessage getDecryptedMessage() {
		char[] m_decrypted = this.m.clone();
		if (is_encrypted_revert)
			m_decrypted = this.applyRevert(m_decrypted);
		if (is_encrypted_rot13)
			m_decrypted = this.applyROT13(m_decrypted);

		return new ChatMessage(username, m_decrypted, color);
	}
}
