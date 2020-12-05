public class WeatherStation {
	public WeatherAttribute getTemperatur10cm() {
		if (this.temperatuur10cm instanceof Float) {
			
			return new WeatherAttribute("Temperatuur", this.celciusToKelvin(this.temperatuur10cm).toString(), "°K");
		} else {
			return new WeatherAttribute("Temperatuur", "n/a", "°K");
		}
		
	}
	
	private Float celciusToKelvin( Float tempInCelcius ) {
		return (float) (tempInCelcius + 273.15);
	}
} 