import java.util.List; 
import java.util.LinkedList; import java.util.HashMap; 

public   class  WeatherAttribute {
	
	public HashMap<String, String> dict;

	

	public String description;

	
	public String apiValue;

	
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
		
		this.dict.put("Z", "S");
		this.dict.put("ZO", "SO");
		this.dict.put("ZW", "SW");
		this.dict.put("OZO", "OSO");
		this.dict.put("ZZO", "SSO");
		this.dict.put("ZZW", "SSW");
		this.dict.put("WZW", "WSW");
		
		this.dict.put("n/a", "n/d");
	}

	
	
	public String getDescription() {
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

	
	
	public void applyTranslation() {
		if (this.dict instanceof HashMap) {
			if (this.dict.get(this.description) instanceof String) {
				this.description = this.dict.get(this.description);
			}
			
			for (WeatherAttributeShownValue shownValue : shownValues) {
				if (this.dict.get(shownValue.value) instanceof String) {
					shownValue.value = this.dict.get(shownValue.value);
				}
			}
		}
	}


}
