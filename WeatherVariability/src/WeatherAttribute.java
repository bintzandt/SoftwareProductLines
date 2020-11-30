
public  class  WeatherAttribute {
	

	public final String description;

	
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
		return this.description;
	}

	
	
	public String getValue() {
		return this.value;
	}

	
	
	public String getUnit() {
		return this.unit;
	}


}
