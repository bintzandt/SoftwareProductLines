import java.util.HashMap;

public class WeatherAttribute {
	
	public WeatherAttribute(String description, String apiValue) {
		this.dict.put("Temperatuur", "Temperature");
		this.dict.put("Windrichting", "Wind direction");
		this.dict.put("Windsnelheid", "Wind speed");
		this.dict.put("Luchtdruk", "Air pressure");
		this.dict.put("Zonintensiteit", "Sun intensity");
		this.dict.put("Luchtvochtigheid", "Humidity");
	}

}