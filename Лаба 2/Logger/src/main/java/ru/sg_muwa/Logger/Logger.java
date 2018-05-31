package ru.sg_muwa.Logger;

// Класс, который печатает выходные танные.
public class Logger {
	// ConcurrentLinkedQueue<Task>

	public static <T> void printAll(Iterable<T> input) {
		for(T elm : input)
			System.out.println(elm.toString());
	}
}
