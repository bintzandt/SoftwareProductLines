import java.util.HashMap;

public class WeatherAttribute {
	
	public WeatherAttribute(String description, String apiValue) {
		this.dict.put("Temperatuur", "Temperature");
		this.dict.put("Windrichting", "Wind direction");
		this.dict.put("Windsnelheid", "Wind speed");
		this.dict.put("Luchtdruk", "Air pressure");
		this.dict.put("Zonintensiteit", "Sun intensity");
		this.dict.put("Luchtvochtigheid", "Humidity");
		
		this.dict.put("Z", "S");
		this.dict.put("ZO", "SE");
		this.dict.put("ZW", "SW");
		this.dict.put("O", "E");
		this.dict.put("NO", "NE");
		this.dict.put("NNO", "NNE");
		this.dict.put("ONO", "ENE");
		this.dict.put("OZO", "ESE");
		this.dict.put("ZZO", "SSE");
		this.dict.put("ZZW", "SSW");
		this.dict.put("WZW", "WSW");
		
		this.dict.put("n.v.t.", "n/a");
	}

}