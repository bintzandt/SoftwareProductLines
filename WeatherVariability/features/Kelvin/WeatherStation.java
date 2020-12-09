import org.w3c.dom.Element;

public class WeatherStation {
	public void addTemperatuur10cm(Element weerstation_element) {
		original(weerstation_element);

		temperatuur10cm.addShownValue(
			new ValueConverter() {
				@Override
				String getShownValue(String apiValue) {
					return celciusToKelvin(Float.parseFloat(apiValue)).toString();
				}
			}, "°K"
		);
	}
	
	private Float celciusToKelvin( Float tempInCelcius ) {
		return (float) (tempInCelcius + 273.15);
	}
} 
