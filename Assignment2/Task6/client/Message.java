import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Color color;
	private final String m;
	
	public Message(String m, Color color) {
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
