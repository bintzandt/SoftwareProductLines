import org.w3c.dom.Element;

public class WeatherStation {
	public void addWindsnelheid(Element weerstation_element) {
		original(weerstation_element);
		
		windsnelheidMS.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(apiValue);
				}
			}, "m/s"
		);
	}
}