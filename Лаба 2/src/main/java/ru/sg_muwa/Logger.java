package ru.sg_muwa;

import static java.sql.DriverManager.println;

// Класс, который печатает выходные танные.
public class Logger {
	// ConcurrentLinkedQueue<Task>

	public static void printTasksAndClear(Iterable<Task> input) {
		for(Task elm : input)
			println(elm.toString());
	}
}
