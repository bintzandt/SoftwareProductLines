import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

/**
 * A datapoint from the Buienradar API.
 * value is unparsed data from the API, while a ShownValue is made to be human-readable.
 */
public class WeatherAttribute {
	public HashMap<String, String> dict;

	public String description;
	public String apiValue;
	public List<WeatherAttributeShownValue> shownValues;
	
	public WeatherAttribute(String description, String apiValue) {
		this.description = description;
		this.apiValue = apiValue;
		this.shownValues = new LinkedList<WeatherAttributeShownValue>();
		this.dict = new HashMap<String, String>();
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
