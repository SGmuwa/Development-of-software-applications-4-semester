import java.util.Date;
import java.util.Random;
import java.util.Set;
// Класс для генерации задач.
public class TaskGenerator {
	private Set<Point> keys;
        private static final Random rand = new Random();
        private long countReady = 0;
	
	// Конструктор. В него следует передать список городов, которые поддерживаются WeatherConveyer
	TaskGenerator(Set<Point> keys){
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
