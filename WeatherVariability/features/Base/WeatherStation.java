import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Element;

public class WeatherStation {
	private final String regio;
	
	private final String stationnaam;
	private final Float lat;
	private final Float lon;
	private final Date date;
	private final Integer luchtvochtigheid;
	private final Float temperatuurGC;
	private final Float windsnelheidMS;
	private final Integer windsnelheidBF;
	private final Integer windrichtingGR;
	private final String windrichting;
	private final Float luchtdruk;
	private final Integer zichtmeters;
	private final Float windstotenMS;
	private final Float regenMMPU;
	private final Integer zonintensiteitWM2;
	private final Float temperatuur10cm;
	
	
	
	public WeatherStation(Element weerstation_element, String regio) {
		this.regio = regio;
		
		// Get all attribters
		 this.stationnaam = weerstation_element.getElementsByTagName("stationnaam").item(0).getTextContent();
		 this.lat = getFloatElement(weerstation_element, "lat");
		 this.lon = getFloatElement(weerstation_element, "lon");
		 this.date = getDateElement(weerstation_element, "datum");
		 this.luchtvochtigheid = getIntegerElement(weerstation_element, "luchtvochtigheid");
		 this.temperatuurGC = getFloatElement(weerstation_element, "temperatuurGC");
		 this.windsnelheidMS = getFloatElement(weerstation_element, "windsnelheidMS");
		 this.windsnelheidBF = getIntegerElement(weerstation_element, "windsnelheidBF");
		 this.windrichtingGR = getIntegerElement(weerstation_element, "windrichtingGR");
		 this.windrichting = weerstation_element.getElementsByTagName("windrichting").item(0).getTextContent();
		 this.luchtdruk = getFloatElement(weerstation_element, "luchtdruk");
		 this.zichtmeters = getIntegerElement(weerstation_element, "zichtmeters");
		 this.windstotenMS = getFloatElement(weerstation_element, "windstotenMS");
		 this.regenMMPU = getFloatElement(weerstation_element, "regenMMPU");
		 this.zonintensiteitWM2 = getIntegerElement(weerstation_element, "zonintensiteitWM2");
		 this.temperatuur10cm = getFloatElement(weerstation_element, "temperatuur10cm");
	
	}
	
	@Override
    public String toString() {
		return "regio: " + this.regio +
				", stationnaam: " + this.stationnaam +
				", latitude: " + this.lat +
				", longitude: " + this.lon +
				", date: " + this.date +
				", luchtvochtigheid: " + this.luchtvochtigheid +
				", temperatuur (GC): " + this.temperatuurGC +
				", windsnelheid (m/s): " + this.windsnelheidMS +
				", windsnelheid (beaufort): " + this.windsnelheidBF +
				", windrichting (graden): " + this.windrichtingGR +
				", windrichting: " + this.windrichting +
				", luchtdruk: " + this.luchtdruk +
				", zichtmeters: " + this.zichtmeters +
				", windstoten (m/s): " + this.windstotenMS +
				", regen (mmpu): " + this.regenMMPU +
				", zonintensiteit (W/M2): " + this.zonintensiteitWM2 +
				", temperatuur 10cm: " + this.temperatuur10cm;
				
				
	}
	
	private Float getFloatElement(Element e, String tag) {
		try {
			return Float.parseFloat(e.getElementsByTagName(tag).item(0).getTextContent());
		}
		catch(Exception exception) {
			return null;
		}
	}
	
	private Integer getIntegerElement(Element e, String tag) {
		try {
			return Integer.parseInt(e.getElementsByTagName(tag).item(0).getTextContent());
		}
		catch(Exception exception) {
			return null;
		}
	}
	
	private Date getDateElement(Element e, String tag) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").parse("11/26/2020 09:50:00");
		} catch (ParseException exception) {
			return null;
		}
	}
	
	public String getRegio() {
		return this.regio;
	}
}
