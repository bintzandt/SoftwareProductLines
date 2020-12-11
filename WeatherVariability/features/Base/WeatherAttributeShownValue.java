
public class WeatherAttributeShownValue {
	public String value;
	public final String unit;
	
	public WeatherAttributeShownValue(String value, String unit) {
		this.value = value;
		this.unit = unit;
	}
	
	public WeatherAttributeShownValue(String unit) {
		this.value = "n/a";
		this.unit = unit;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getUnit() {
		return this.unit;
	}
}