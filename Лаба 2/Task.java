public class Task {
	private int id;
	private boolean isIdSet;
	public final Date date;
	public final String city;
	private String weather;

	public boolean setId(int id) {
		if(isIdSet == false) {
			this.isIdSet = true;
			this.id = id;
			return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public setWeather(String weather) {
		this.weather = weather;
	}

	public Weather getWeather() {
		return weather;
	}
	
	public Task(Date date, String city) {
		this.id = 0;
		this.isIdSet = false;
		this.date = date;
		this.city = city;
	}
}
