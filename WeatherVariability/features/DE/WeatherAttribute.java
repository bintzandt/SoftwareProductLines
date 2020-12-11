import java.util.HashMap;

public class WeatherAttribute {
	
	public WeatherAttribute(String description, String apiValue) {
		this.dict.put("Temperatuur", "Temperatur");
		this.dict.put("Windrichting", "Windrichtung");
		this.dict.put("Windsnelheid", "Windgeschwindigkeit");
		this.dict.put("Luchtdruk", "Luftdruck");
		this.dict.put("Zonintensiteit", "Sonnenintensität");
		this.dict.put("Luchtvochtigheid", "Feuchtigkeit");
		
		this.dict.put("Z", "S");
		this.dict.put("ZO", "SO");
		this.dict.put("ZW", "SW");
		this.dict.put("OZO", "OSO");
		this.dict.put("ZZO", "SSO");
		this.dict.put("ZZW", "SSW");
		this.dict.put("WZW", "WSW");
		
		this.dict.put("n.v.t.", "n/d");
	}

}