import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String color;
	private final String m;
	
	public Message(String m) {
		this.m = m;
		this.color = "Black";
	}
	
	public Message(String m, String color) {
		this.m = m;
		this.color = color;
	}
	
	public String getMessageBody() {
		return this.m;
	}
	
	public String getColor() {
		return this.color;
	}
	
	@Override
    public String toString() { 
        return String.format("Message: " + m); 
    }
	
}
