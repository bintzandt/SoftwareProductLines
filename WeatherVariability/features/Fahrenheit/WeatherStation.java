public class WeatherStation {
	public WeatherAttribute getTemperatur10cm() {
		if (this.temperatuur10cm instanceof Float) {
			
			return new WeatherAttribute("Temperatuur", this.celciusToFahrenheit(this.temperatuur10cm).toString(), "°F");
		} else {
			return new WeatherAttribute("Temperatuur", "n/a", "°F");
		}
		
	}
	
	private Float celciusToFahrenheit( Float tempInCelcius ) {
		return (float) (tempInCelcius * 1.8 + 32);
	}
} 