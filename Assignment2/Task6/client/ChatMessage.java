public class ChatMessage extends Message {
    /**
     * Sent by a client, broadcast by the server to all other clients
     */
    private final Color color;
    private final String username;
    private final String m;

    public ChatMessage(String username, String m, Color color) {
        this.username = username;
        this.m = m;
        this.color = color;
    }

    public String getMessageBody() {
        return this.color + getPlainMessage() + Color.RESET;
    }
    public String getPlainMessage() {
        return "[" + username + "]: " + this.m;
    }

    public Color getColor() {
        return this.color;
    }

    public ChatEncryptedMessage encrypt() {
        return new ChatEncryptedMessage(username, m, color);
    }

    @Override
    public String toString() {
        return String.format("Message: " + m);
    }
}
