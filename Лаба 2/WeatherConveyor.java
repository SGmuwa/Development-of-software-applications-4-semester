public class WeatherConveyor implements Runnable {
	
	private const static String FileName = "WeatherConveyor.bin";
	
	public WeatherConveyor()
	{
		queueInbox = new ConcurrentLinkedQueueTask();
		queueOutbox = new ConcurrentLinkedQueueTask();
		dataBase = loadDataBase();
	}
	
	~WeatherConveyor()
	{
		saveDataBase(dataBase);
	}
	
	HashMap<Point, Weather> dataBase;
	
	public void AddData(Point point, Weather weather)
	{
		dataBase.put(point, weather);
	}
	
	// Загрузка базы данных из файла
	private HashMap<Point, Weather> loadDataBase()
	{
		FileInputStream fis = new FileInputStream(FileName);
		ObjectInputStream oin = new ObjectInputStream(fis);
		dataBase = (HashMap<Point, Weather>) oin.readObject();
		oin.close();
		fis.close();
	}
	
	// Сохранение базы данных в файл
	private static void saveDataBase(HashMap<Point, Weather> input)
	{
		if(input == null) return;
		FileOutputStream fos = new FileOutputStream(FileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(input);
		oos.flush();
		oos.close();
		fos.close();
	}
	
	
	// Делает шаг.
	public void step()
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
		while(!Thread.IsInterrupted()) step();
	}
	
	// Функция, которая возвращает список поддерживаемых городов.
	public String[] getCities() {
		
	}
}
