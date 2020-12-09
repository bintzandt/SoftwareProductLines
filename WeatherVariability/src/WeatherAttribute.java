import java.util.HashMap; 

public   class  WeatherAttribute {
	
	
	public String getUnit  () {
		return this.unit;
	}

	
	public HashMap<String, String> dict;

	

	public String description;

	
	public final String value;

	
	public final String unit;

	
	
	public WeatherAttribute(String description, String value, String unit) {
		this.description = description;
		this.value = value.toString();
		this.unit = unit;
	}

	
	
	public WeatherAttribute(String description) {
		this.description = description;
		this.value = "n/a";
		this.unit = "";
	}

	
	
	public String getDescription() {
		this.applyTranslation();
		return this.description;
	}

	
	
	public String getValue() {
		return this.value;
	}

	
	
	private void InitDict() {
		this.dict = new HashMap<String, String>();
	}

	
	
	private void applyTranslation() {
		System.out.println("translating" + this.description + this.dict.keySet());
		if (this.dict instanceof HashMap && this.dict.get(this.description) instanceof String) {
			System.out.println("Fired");
			this.description = this.dict.get(this.description);
		}
	}


}
