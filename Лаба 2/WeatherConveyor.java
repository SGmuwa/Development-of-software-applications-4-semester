public class WeatherConveyor implements Runnable {
	
	
	public WeatherConveyor()
	{
		queueInbox = new ConcurrentLinkedQueueTask();
		queueOutbox = new ConcurrentLinkedQueueTask();
	}
	
	HashMap<K, V>
	
	// Делает шаг.
	public void stepGetWeatherFromInboxToOutbox()
	{
		queueOutbox.push(
			getWeather(
				queueOutbox.pop()
			)
		);
	}
	
	// Функция ищит соответсвие погоды по запросу.
	private Task getWeather(Task input)
	{
		
	}
	
	
	ConcurrentLinkedQueueTask queueInbox;
	ConcurrentLinkedQueueTask queueOutbox;
	
	public void run() {
		//
	}
	
	// Функция, которая возвращает список поддерживаемых городов.
	public String[] getCities() {
		
	}
}
