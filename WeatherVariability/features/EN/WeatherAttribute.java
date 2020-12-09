import java.util.HashMap;

public class WeatherAttribute {
	
	public void InitDict() {
		this.dict = new HashMap<String, String>();
		this.dict.put("Temperatuur", "Temperature");
		this.dict.put("Windrichting", "Wind direction");
		this.dict.put("Windsnelheid", "Wind speed");
		this.dict.put("Luchtdruk", "Air pressure");
		this.dict.put("Zonintensiteit", "Sun intensity");
	}

}