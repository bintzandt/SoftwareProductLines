import java.util.List; 
import java.util.LinkedList; 

/**
 * A datapoint from the Buienradar API.
 * value is unparsed data from the API, while a ShownValue is made to be human-readable.
 */
public  class  WeatherAttribute {
	

	public final String description;

	
	public final String apiValue;

	
	public List<WeatherAttributeShownValue> shownValues;

	
	
	public WeatherAttribute(String description, String apiValue) {
		this.description = description;
		this.apiValue = apiValue;
		this.shownValues = new LinkedList<WeatherAttributeShownValue>();
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


}
