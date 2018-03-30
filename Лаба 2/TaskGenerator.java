import java.util.Random;
// Класс для генерации задач.
public class TaskGenerator {
	private String[] city;
	
	// Конструктор. В него следует передать список городов, которые поддерживаются WeatherConveyer
	TaskGenerator(String[] city) {
		this.city = city.clone();
	}

	public Task getTask() {
		if(city == null || city.length == 0)
			return null;
		Task tsk = new Task(getRandomDate(), getRandomCity());
		countReady++;
		return tsk;
	}

	private Date getRandomDate() {
		return (Date) rand.nextInt();
	}

	private String getRandomCity() {
		return city[0 + rand.nextInt((city.length - 1) - 0 + 1)];
	}

	private int countReady = 0;
	public int getCountReady() {
		return countReady;
	}
}
