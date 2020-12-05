
public class GUI {
	private void addAttributes(WeatherStation weatherStation) {
		original(weatherStation);
		try {
			addAttribute(weatherStation.getZonintensiteit());
		} catch(Exception e) {
			addAttribute(new WeatherAttribute("Zonintensiteit", "n/a", ""));
		}
	}
}