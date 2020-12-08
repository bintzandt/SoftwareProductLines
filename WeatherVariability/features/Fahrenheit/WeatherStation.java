import org.w3c.dom.Element;

public class WeatherStation {
	public void addTemperatuur10cm(Element weerstation_element) {
		original(weerstation_element);

		temperatuur10cm.addShownValue(
			new ValueConverter() {
				@Override
				String getShownValue(String apiValue) {
					return celciusToFahrenheit(Float.parseFloat(apiValue)).toString();
				}
			}, "°F"
		);
	}
	
	private Float celciusToFahrenheit( Float tempInCelcius ) {
		return (float) (tempInCelcius * 1.8 + 32);
	}
} 