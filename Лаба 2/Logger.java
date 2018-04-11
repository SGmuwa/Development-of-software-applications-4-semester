// Класс, который печатает выходные танные.
public class Logger {
	// ConcurrentLinkedQueue<Task>

	public static void printTasksAndClear(Collection<Task> input)
	{
		for(Task elm : input)
			println("id: " + elm.getId() + ", city: " + elm.city + ", date: " + elm.date.toString() + ", weather: " + elm.getWeather());
		input.clear();
	}
}
