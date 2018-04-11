class WeatherPoint {
	public final Date date;
	public final String city;
	
	private boolean weatherChange; // Значение погоды менялось?
	private String weather;

	
	public WeatherPoint(Date date, String city) {
		this.date = date;
		this.city = city;
		this.weather = null;
		this.weatherChange = false;
	}
	
	public boolean setWeather(String weather) {
		if(weatherChange == false)
		{
			this.weather = weather;
			this.weatherChange = true;
			return true;
		}
		else return false;
	}

	public Weather getWeather() {
		return weather;
	}
}