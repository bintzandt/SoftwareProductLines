import org.w3c.dom.Element;

public class WeatherStation {
	public void addLuchtvochtigheid(Element weerstation_element) {
		original(weerstation_element);

		luchtvochtigheid.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return ((Integer) Integer.parseInt(apiValue)).toString();
				}
			}, "%"
		);
	}
} 