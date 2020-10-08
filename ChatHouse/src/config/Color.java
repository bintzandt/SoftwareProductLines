import java.util.ArrayList; 
import java.util.List; 

public enum  Color {
	RESET("\u001B[0m") , 
	BLACK("\u001B[30m") , 
	RED("\u001B[31m") , 
	GREEN("\u001B[32m") , 
	YELLOW("\u001B[33m") , 
	BLUE("\u001B[34m") , 
	PURPLE("\u001B[35m") , 
	CYAN("\u001B[36m") , 
	WHITE("\u001B[37m"); 

	private String colorCode; 

	Color(String colorCode) {
		this.colorCode = colorCode;
	} 

	public String getColorCode() {
		return this.colorCode;
	} 

	public static String getColorOptions() {
		List<String> colorOptions = new ArrayList<String>();
		for (Color c : Color.values()) {
			colorOptions.add(c.name().toLowerCase());
		}
		return colorOptions.toString();
	} 

	@Override
	public String toString() {
		return this.colorCode;
	}}
