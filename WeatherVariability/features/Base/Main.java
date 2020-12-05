public class Main {

	public static void main(String[] args) {
		WeatherStations weatherStations = WeatherStations.getInstance();
		
		// debug purpose		
//		weatherStations.printWeatherStations();

		GUI gui = new GUI(weatherStations);
		gui.populateWeatherStations();
	}

}
