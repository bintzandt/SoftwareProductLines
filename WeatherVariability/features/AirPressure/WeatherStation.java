import org.w3c.dom.Element;

public class WeatherStation {
	public void addLuchtdruk(Element weerstation_element) {
		original(weerstation_element);

		luchtdruk.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return ((Float) Float.parseFloat(apiValue)).toString();
				}
			}, "hPa"
		);
	}
} 