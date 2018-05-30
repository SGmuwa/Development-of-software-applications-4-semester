package ru.sg_muwa.WeatherConveyor;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTask extends ConcurrentLinkedQueue<Task> {
	private int id = 0;
	
	// Добавляет элемент в очередь
	@Override
	public boolean add(Task e) {
		e.setId(id++);
		return super.add(e);
	}
}
