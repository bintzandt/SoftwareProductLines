import org.w3c.dom.Element;

public class WeatherStation {
	
	private final String stationnaam;
	private final Float temperatuur10cm;
	private final Float luchtdruk;
	
	public WeatherStation(Element weerstation_element) {
		// Get all attribters
		 this.stationnaam = weerstation_element.getElementsByTagName("stationnaam").item(0).getTextContent();
		 this.temperatuur10cm = getFloatElement(weerstation_element, "temperatuur10cm");
		 this.luchtdruk = getFloatElement(weerstation_element, "luchtdruk");
	}
	
	@Override
    public String toString() {
		return "stationnaam: " + this.stationnaam +
				", temperatuur: " + this.temperatuur10cm +
				", luchtdruk: " + this.luchtdruk;
				
	}
	
	public Float getFloatElement(Element e, String tag) {
		try {
			return Float.parseFloat(e.getElementsByTagName(tag).item(0).getTextContent());
		}
		catch(Exception exception) {
			return null;
		}
	}
	
	public String getWeatherStationName() {
		return this.stationnaam;
	}
	
}
