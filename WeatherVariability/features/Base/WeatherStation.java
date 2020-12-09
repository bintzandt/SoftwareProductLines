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
	private WeatherAttribute windsnelheidMS;
	private final Integer windsnelheidBF;
	private final Integer windrichtingGR;
	private WeatherAttribute windrichting;
	private final Float luchtdruk;
	private final Integer zichtmeters;
	private final Float windstotenMS;
	private final Float regenMMPU;
	private final Integer zonintensiteitWM2;
	private WeatherAttribute temperatuur10cm;
	
	
	
	public WeatherStation(Element weerstation_element, String regio) {
		this.regio = regio;
		
		// Get all attribters
		this.stationnaam = getElement(weerstation_element, "stationnaam");
		this.lat = getFloatElement(weerstation_element, "lat");
		this.lon = getFloatElement(weerstation_element, "lon");
		this.date = getDateElement(weerstation_element, "datum");
		this.luchtvochtigheid = getIntegerElement(weerstation_element, "luchtvochtigheid");
		this.temperatuurGC = getFloatElement(weerstation_element, "temperatuurGC");
		this.addWindsnelheid(weerstation_element);
		this.windsnelheidBF = getIntegerElement(weerstation_element, "windsnelheidBF");
		this.windrichtingGR = getIntegerElement(weerstation_element, "windrichtingGR");
		this.addWindrichting(weerstation_element);
		this.luchtdruk = getFloatElement(weerstation_element, "luchtdruk");
		this.zichtmeters = getIntegerElement(weerstation_element, "zichtmeters");
		this.windstotenMS = getFloatElement(weerstation_element, "windstotenMS");
		this.regenMMPU = getFloatElement(weerstation_element, "regenMMPU");
		this.zonintensiteitWM2 = getIntegerElement(weerstation_element, "zonintensiteitWM2");
		this.addTemperatuur10cm(weerstation_element);
	
	}
	
	private String getElement(Element e, String tag) {
		return e.getElementsByTagName(tag).item(0).getTextContent();
	}
	
	private Float getFloatElement(Element e, String tag) {
		try {
			return Float.parseFloat(getElement(e, tag));
		}
		catch(Exception exception) {
			return null;
		}
	}
	
	private Integer getIntegerElement(Element e, String tag) {
		try {
			return Integer.parseInt(getElement(e, tag));
		}
		catch(Exception exception) {
			return null;
		}
	}
	
	private Date getDateElement(Element e, String tag) {
		try {
			// FIXME
			return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").parse("11/26/2020 09:50:00");
		} catch (ParseException exception) {
			return null;
		}
	}
	
	public String getRegio() {
		return this.regio;
	}
	
	public void addTemperatuur10cm(Element weerstation_element) {
		temperatuur10cm = new WeatherAttribute(
			"Temperatuur", getElement(weerstation_element, "temperatuur10cm")
		);
	}
	public WeatherAttribute getTemperatur10cm() {
		return temperatuur10cm;
	}
	
	public void addWindrichting(Element weerstation_element) {
		windrichting = new WeatherAttribute(
			"Windrichting", getElement(weerstation_element, "windrichting")
		);
	}
	public WeatherAttribute getWindrichting() {
		return windrichting;
	}
	
	public void addWindsnelheid(Element weerstation_element) {
		windsnelheidMS = new WeatherAttribute(
			"Windsnelheid", getElement(weerstation_element, "windsnelheidMS")
		);
	}
	public WeatherAttribute getWindsnelheid() {
		return windsnelheidMS;
	}
	/*
	
	public WeatherAttribute getLuchtdruk() {
		if (this.luchtdruk instanceof Float) {
			return new WeatherAttribute("Luchtdruk", this.luchtdruk.toString(), "hPa");
		} else {
			return new WeatherAttribute("Luchtdruk");
		}
	}
	
	public WeatherAttribute getZonintensiteit() {
		if (this.zonintensiteitWM2 instanceof Integer) {
			return new WeatherAttribute("Zonintensiteit", this.zonintensiteitWM2.toString(), "W/m²");
		} else {
			return new WeatherAttribute("Zonintensiteit");
		}
	}
	
	public WeatherAttribute getLuchtvochtigheid() {
		if (this.luchtvochtigheid instanceof Integer) {
			return new WeatherAttribute("Luchtvochtigheid", this.luchtvochtigheid.toString(), "%");
		} else {
			return new WeatherAttribute("Luchtvochtigheid");
		}
	}
	*/
}
