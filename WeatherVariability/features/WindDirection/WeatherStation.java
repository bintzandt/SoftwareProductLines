import org.w3c.dom.Element;

public class WeatherStation {
	public void addWindrichting(Element weerstation_element) {
		original(weerstation_element);

		windrichting.addShownValue(
			new ValueConverter() {
				@Override
				String getShownValue(String apiValue) {
					return apiValue;
				}
			}, null
		);
	}
} 