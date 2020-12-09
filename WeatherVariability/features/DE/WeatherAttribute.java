import java.util.HashMap;

public class WeatherAttribute {
	
	public WeatherAttribute(String description, String value, String unit) {
		this.dict.put("Temperatuur", "Temperatur");
		this.dict.put("Windrichting", "Windrichtung");
		this.dict.put("Windsnelheid", "Windgeschwindigkeit");
		this.dict.put("Luchtdruk", "Luftdruck");
		this.dict.put("Zonintensiteit", "Sonnenintensität");
		this.dict.put("Luchtvochtigheid", "Feuchtigkeit");
	}

}