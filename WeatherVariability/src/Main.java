public class Main {

	public static void main(String[] args) {
		WeatherStations weatherStations = WeatherStations.getInstance();
		weatherStations.printWeatherStations();

		GUI gui = new GUI();
	}

}
