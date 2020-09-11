public class ChatMessage extends Message {
    /**
     * Sent by a client, broadcast by the server to all other clients
     */
    private final Color color;
    private final String m;

    public ChatMessage(String m, Color color) {
        this.m = m;
        this.color = color;
    }

    public String getMessageBody() {
        return this.color + this.m + Color.RESET;
    }
    public String getPlainMessage() { return this.m; }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return String.format("Message: " + m);
    }
}
