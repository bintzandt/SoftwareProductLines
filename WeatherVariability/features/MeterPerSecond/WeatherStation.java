import org.w3c.dom.Element;

public class WeatherStation {
	public void addWindsnelheid(Element weerstation_element) {
		original(weerstation_element);
		
		windsnelheidMS.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return ((Float) Float.parseFloat(apiValue)).toString();
				}
			}, "m/s"
		);
	}
}