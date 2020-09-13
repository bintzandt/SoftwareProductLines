public class ChatEncryptedMessage extends Message {
	private final Color color;
    private final String username;
    private char[] m;

	/**
     * Sent by a client, broadcast by the server to all other clients
     */

    public ChatEncryptedMessage(String username, char[] m, Color color) {
    	this.username = username;
    	this.m = m;
    	this.color = color;
        
        // encrypt messages
        this.m = this.applyROT13(this.m);
    	this.m= this.applyRevert(this.m);
    }
    
    public ChatEncryptedMessage(String username, String m, Color color) {
		this.username = username;
		this.m = m.toCharArray();
		this.color = color;
		
        // encrypt messages
        this.m = this.applyROT13(this.m);
    	this.m= this.applyRevert(this.m);
	}

	public char[] applyROT13(char[] m) {
    	char[] m_encrypted = m.clone();
        for (int i = 0; i < m_encrypted.length; i++) {
            char c = m_encrypted[i];
            if (c >= 'a' && c <= 'm' ||  c >= 'A' && c <= 'M'){
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
    	m_decrypted = this.applyRevert(m_decrypted);
    	m_decrypted = this.applyROT13(m_decrypted);
        
        return new ChatMessage(username, m_decrypted, color);
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
