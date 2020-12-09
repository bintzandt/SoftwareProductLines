import java.util.List; 
import java.util.LinkedList; import java.util.HashMap; 

public   class  WeatherAttribute {
	
	public HashMap<String, String> dict;

	

	public String description;

	
	public final String apiValue;

	
	public List<WeatherAttributeShownValue> shownValues;

	
	
	public WeatherAttribute  (String description, String apiValue) {
		this.description = description;
		this.apiValue = apiValue;
		this.shownValues = new LinkedList<WeatherAttributeShownValue>();
		this.dict = new HashMap<String, String>();
	
		this.dict.put("Temperatuur", "Temperatur");
		this.dict.put("Windrichting", "Windrichtung");
		this.dict.put("Windsnelheid", "Windgeschwindigkeit");
		this.dict.put("Luchtdruk", "Luftdruck");
		this.dict.put("Zonintensiteit", "Sonnenintensität");
		this.dict.put("Luchtvochtigheid", "Feuchtigkeit");
	}

	
	
	public String getDescription() {
		this.applyTranslation();
		return this.description;
	}

	
	
	public String getApiValue() {
		return this.apiValue;
	}

	
	
	public List<WeatherAttributeShownValue> getShownValues() {
		return shownValues;
	}

	
	
	public void addShownValue(ValueConverter valueconverter, String unit) {
		shownValues.add(new WeatherAttributeShownValue(valueconverter.safeGetShownValue(apiValue), unit));
	}

	
	
	public void InitDict() {
		this.dict = new HashMap<String, String>();
	}

	
	
	private void applyTranslation() {
		if (this.dict instanceof HashMap && this.dict.get(this.description) instanceof String) {
			System.out.println("Fired");
			this.description = this.dict.get(this.description);
		}
	}


}
