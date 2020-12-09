import org.w3c.dom.Element;

public class WeatherStation {
	public void addZonintensiteit(Element weerstation_element) {
		original(weerstation_element);

		zonintensiteitWM2.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return ((Integer) Integer.parseInt(apiValue)).toString();
				}
			}, "W/m²"
		);
	}
} 