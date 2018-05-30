package ru.sg_muwa.WeatherConveyor;

// Класс, который печатает выходные танные.
public class Logger {
	// ConcurrentLinkedQueue<Task>

	public static <T> void printTasksAndClear(Iterable<T> input) {
		for(T elm : input)
			System.out.println(elm.toString());
	}
}
