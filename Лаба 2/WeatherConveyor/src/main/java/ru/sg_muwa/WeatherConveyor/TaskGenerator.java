package ru.sg_muwa.WeatherConveyor;

import ru.sg_muwa.Point.Point;

import java.util.Random;
import java.util.Set;
// Класс для генерации задач.
public class TaskGenerator {
	private Set<Point> keys;
        private static final Random rand = new Random();
        private long countReady = 0;
	
	// Конструктор. В него следует передать список городов, которые поддерживаются WeatherConveyer
	public TaskGenerator(Set<Point> keys) throws IllegalArgumentException {
	    if(keys.size() < 1)
	        throw new IllegalArgumentException("keys.size must be more 0!");
		this.keys = keys;
	}

	public Task getTask() {
            int i = getRandom(0, keys.size() - 1);
            countReady++;
            for(Point a : keys)
            {
                if(i-- == 0) return new Task(a);
            }
            countReady--;
            return null;
	}
        
        private int getRandom(int min, int max)
        {
            return min + rand.nextInt(max + 1);
        }

	
	public long getCountReady() {
		return countReady;
	}
}
