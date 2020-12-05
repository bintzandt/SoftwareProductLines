public class WeatherStation {
	public WeatherAttribute getWindsnelheid() {
		if (this.windsnelheidBF instanceof Integer) {
			return new WeatherAttribute("Windsnelheid", this.windsnelheidBF.toString(), "B");
		} else if (this.windsnelheidMS instanceof Float) { 
			return new WeatherAttribute("Windsnelheid", this.convertMsToBeaufort(this.windsnelheidMS).toString(), "B");
		} else {
			return new WeatherAttribute("Windsnelheid");
		}
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