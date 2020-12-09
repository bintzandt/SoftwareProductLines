import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date; import org.w3c.dom.Element; 

public   class  WeatherStation {
	
	private final String regio;

	
	
	private final String stationnaam;

	
	private final Float lat;

	
	private final Float lon;

	
	private final Date date;

	
	private WeatherAttribute luchtvochtigheid;

	
	private final Float temperatuurGC;

	
	private WeatherAttribute windsnelheidMS;

	
	private final Integer windsnelheidBF;

	
	private final Integer windrichtingGR;

	
	private WeatherAttribute windrichting;

	
	private WeatherAttribute luchtdruk;

	
	private final Integer zichtmeters;

	
	private final Float windstotenMS;

	
	private final Float regenMMPU;

	
	private WeatherAttribute zonintensiteitWM2;

	
	private WeatherAttribute temperatuur10cm;

	
	
	
	
	public WeatherStation(Element weerstation_element, String regio) {
		this.regio = regio;
		
		// Get all attribters
		this.stationnaam = getElement(weerstation_element, "stationnaam");
		this.lat = getFloatElement(weerstation_element, "lat");
		this.lon = getFloatElement(weerstation_element, "lon");
		this.date = getDateElement(weerstation_element, "datum");
		this.addLuchtvochtigheid(weerstation_element);
		this.temperatuurGC = getFloatElement(weerstation_element, "temperatuurGC");
		this.addWindsnelheid(weerstation_element);
		this.windsnelheidBF = getIntegerElement(weerstation_element, "windsnelheidBF");
		this.windrichtingGR = getIntegerElement(weerstation_element, "windrichtingGR");
		this.addWindrichting(weerstation_element);
		this.addLuchtdruk(weerstation_element);
		this.zichtmeters = getIntegerElement(weerstation_element, "zichtmeters");
		this.windstotenMS = getFloatElement(weerstation_element, "windstotenMS");
		this.regenMMPU = getFloatElement(weerstation_element, "regenMMPU");
		this.addZonintensiteit(weerstation_element);
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

	
	
	public String floatString(String s) throws NumberFormatException {
		// Ensures a string is formatted as float, or throws exception
		return floatString(Float.parseFloat(s));
	}

	
	public String floatString(Float f) {
		return ((Float) f).toString();
	}

	
	
	 private void  addTemperatuur10cm__wrappee__Base  (Element weerstation_element) {
		temperatuur10cm = new WeatherAttribute(
			"Temperatuur", getElement(weerstation_element, "temperatuur10cm")
		);
	}

	
	 private void  addTemperatuur10cm__wrappee__Celcius  (Element weerstation_element) {
		addTemperatuur10cm__wrappee__Base(weerstation_element);

		temperatuur10cm.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(apiValue);
				}
			}, "°C"
		);
	}

	
	 private void  addTemperatuur10cm__wrappee__Kelvin  (Element weerstation_element) {
		addTemperatuur10cm__wrappee__Celcius(weerstation_element);

		temperatuur10cm.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(celciusToKelvin(Float.parseFloat(apiValue)));
				}
			}, "°K"
		);
	}

	
	public void addTemperatuur10cm(Element weerstation_element) {
		addTemperatuur10cm__wrappee__Kelvin(weerstation_element);

		temperatuur10cm.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(celciusToFahrenheit(Float.parseFloat(apiValue)));
				}
			}, "°F"
		);
	}

	
	public WeatherAttribute getTemperatur10cm() {
		return temperatuur10cm;
	}

	
	
	 private void  addWindrichting__wrappee__Base  (Element weerstation_element) {
		windrichting = new WeatherAttribute(
			"Windrichting", getElement(weerstation_element, "windrichting")
		);
	}

	
	public void addWindrichting(Element weerstation_element) {
		addWindrichting__wrappee__Base(weerstation_element);

		windrichting.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return apiValue;
				}
			}, null
		);
	}

	
	public WeatherAttribute getWindrichting() {
		return windrichting;
	}

	
	
	 private void  addWindsnelheid__wrappee__Base  (Element weerstation_element) {
		windsnelheidMS = new WeatherAttribute(
			"Windsnelheid", getElement(weerstation_element, "windsnelheidMS")
		);
	}

	
	 private void  addWindsnelheid__wrappee__MeterPerSecond  (Element weerstation_element) {
		addWindsnelheid__wrappee__Base(weerstation_element);
		
		windsnelheidMS.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(apiValue);
				}
			}, "m/s"
		);
	}

	
	public void addWindsnelheid(Element weerstation_element) {
		addWindsnelheid__wrappee__MeterPerSecond(weerstation_element);
		
		windsnelheidMS.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return convertMsToBeaufort(Float.parseFloat(apiValue)).toString();
				}
			}, "B"
		);
	}

	
	public WeatherAttribute getWindsnelheid() {
		return windsnelheidMS;
	}

	
	
	 private void  addLuchtdruk__wrappee__Base  (Element weerstation_element) {
		luchtdruk = new WeatherAttribute(
			"Luchtdruk", getElement(weerstation_element, "luchtdruk")
		);
	}

	
	public void addLuchtdruk(Element weerstation_element) {
		addLuchtdruk__wrappee__Base(weerstation_element);

		luchtdruk.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return floatString(apiValue);
				}
			}, "hPa"
		);
	}

	
	public WeatherAttribute getLuchtdruk() {
		return luchtdruk;
	}

	
	
	 private void  addZonintensiteit__wrappee__Base  (Element weerstation_element) {
		zonintensiteitWM2 = new WeatherAttribute(
			"Zonintensiteit", getElement(weerstation_element, "zonintensiteitWM2")
		);
	}

	
	public void addZonintensiteit(Element weerstation_element) {
		addZonintensiteit__wrappee__Base(weerstation_element);

		zonintensiteitWM2.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return ((Integer) Integer.parseInt(apiValue)).toString();
				}
			}, "W/m²"
		);
	}

	
	public WeatherAttribute getZonintensiteit() {
		return zonintensiteitWM2;
	}

	
	
	 private void  addLuchtvochtigheid__wrappee__Base  (Element weerstation_element) {
		luchtvochtigheid = new WeatherAttribute(
			"Luchtvochtigheid", getElement(weerstation_element, "luchtvochtigheid")
		);
	}

	
	public void addLuchtvochtigheid(Element weerstation_element) {
		addLuchtvochtigheid__wrappee__Base(weerstation_element);

		luchtvochtigheid.addShownValue(
			new ValueConverter() {
				String getShownValue(String apiValue) {
					return ((Integer) Integer.parseInt(apiValue)).toString();
				}
			}, "%"
		);
	}

	
	public WeatherAttribute getLuchtvochtigheid() {
		return luchtvochtigheid;
	}

	
	
	private Float celciusToKelvin( Float tempInCelcius ) {
		return (float) (tempInCelcius + 273.15);
	}

	
	
	private Float celciusToFahrenheit( Float tempInCelcius ) {
		return (float) (tempInCelcius * 1.8 + 32);
	}

	
	
	private Integer convertMsToBeaufort( Float speedInMs) {
		if (speedInMs > 32.7) return 12;
		if (speedInMs > 28.5) return 11;
		if (speedInMs > 24.5) return 10;
		if (speedInMs > 20.8) return 9;
		if (speedInMs > 17.2) return 8;
		if (speedInMs > 13.9) return 7;
		if (speedInMs > 10.8) return 6;
		if (speedInMs > 8.0) return 5;
		if (speedInMs > 5.5) return 4;
		if (speedInMs > 3.4) return 3;
		if (speedInMs > 1.6) return 2;
		if (speedInMs > 0.3) return 1;
		
		return 0;
	}


}
