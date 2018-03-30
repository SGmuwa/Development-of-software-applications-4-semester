class WeatherPoint {
	public final Date date;
	public final String city;
	private String weather;

	
	public WeatherPoint(Date date, String city) {
		this.date = date;
		this.city = city;
	}
	
	public setWeather(String weather) {
		this.weather = weather;
	}

	public Weather getWeather() {
		return weather;
	}
}