public class ChatEncryptedMessage extends Message {
    /**
     * Sent by a client, broadcast by the server to all other clients
     */
    private final Color color;
    private final String username;
    private final String m_crypt;

    public ChatEncryptedMessage(String username, String m, Color color) {
        this.username = username;
        // TODO: apply "encryption" to m
        this.m_crypt = m;
        this.color = color;
    }

    public ChatMessage decrypt() {
        // TODO: apply "decryption" to m
        return new ChatMessage(username, m_crypt, color);
    }

    @Override
    public String toString() {
        return String.format("Encrypted message!");
    }
}
