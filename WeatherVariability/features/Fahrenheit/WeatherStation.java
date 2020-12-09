import org.w3c.dom.Element;

public class WeatherStation {
	public void addTemperatuur10cm(Element weerstation_element) {
		original(weerstation_element);

		temperatuur10cm.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(celciusToFahrenheit(Float.parseFloat(apiValue)));
				}
			}, "°F"
		);
	}
	
	private Float celciusToFahrenheit( Float tempInCelcius ) {
		return (float) (tempInCelcius * 1.8 + 32);
	}
} 
