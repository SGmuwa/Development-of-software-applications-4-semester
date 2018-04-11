public class Test_Weather {
	
	// Отправить информацию на консоль.
	// boolean isStatusGood: True, если всё идёт по-плану. False, если произошла ошибка.
	// String message: Текст сообщения.
	public static void log(boolean isStatusGood, String message)
	{
		if(isStatusGood)
			System.out.println(message + " ok.");
		else
		{
			System.out.println("Error: " + message);
			System.err.println(message);
		}
	}
	
	public static void main(String[] args)
	{
		log(true, "Test started."); // Начало тестирования
		
		int countElms = 100; // количество тестируемых элементов.

		if(isTaskGeneratorRandomize() && isTaskGeneratorRandomize())
			log(true, "TaskGenerator: IsRandomize"); // Проверяем, что задачи наши не одни и те же.
		else log(false, "TaskGenerator: Not randomize"); // В случае, если есть схожести, пишем об этом.
		
		String[] сities = new String[] {
			"Самара", "Тольятти"
		};

		TaskGenerator tasks = new TaskGenerator(); // Готовим генератор задач к работе.

		WeatherConveyer wc = new WeatherConveyer(); // Готовим основной конвеер к работе.
		
		if(wc.queueInbox != null)
			log(true, "queueInbox is not null."); // Входящая очередь должна при этом инцелизироваться.
		else
		{ // Если нет, то тест нельзя дальше производить.
			log(false, "queueInbox is null.");
			return;
		}
		
		while(tasks.countReady() != countElms) // Пока не сгенерирует 100.
			wc.queueInbox.add(tasks.getTask()); // Добавить в входящую очередь задачу.
			
		if(wc.queueInbox.size() == countElms) // В входной очереди должно быть countElms элементов.
			log(true, "wc.queueInbox: size == countElms == " + countElms);
		else log(false, "wc.queueInbox: size (" + wc.queueInbox.size() + ") != countElms (" + countElms + ")");

		if(wc.queueOutbox != null) // Выходная очередь должна быть инцилизирована.
			log(true, "queueOutbox is not null.")
		else
		{
			log(false, "queueOutbox is null.");
			return;
		}
		
		wc.stepGetWeatherFromInboxToOutbox(); // Выполняем один шаг.

		if(wc.queueOutbox.size() == 1) // Проверяем, что в выходной очереди действительно оказался 1 элемент.
			log(true, "queueOutbox.count = 1");
		else
			log(false, "queueOutbox.count = " + wc.queueOutbox.size());

		if(wc.queueInbox.size() == countElms - 1)
			log(true, "queueOutbox.count = 99"); // Проверяем, что из входной один элемент пропал.
		else
			log(false, "queueInbox.count = " + wc.queueOutbox.count);

		wc.startGetWeatherFromInboxToOutbox(); // Начать высчитывать в ассинхронном режиме.
		
		Thread.sleep(100); // wait 100ms

		if(wc.queueOutbox.size() == countElms) // Проверяем, что вся выходная очередь заполнена.
			log(true, "queueOutbox.count = " + wc.queueOutbox.getCount);
		else 
			log(false, "queueOutbox.count = " + wc.queueOutbox.getCount);
		
		Logger.printAllOutput(wc);

		if(wc.queueOutbox.size() == 0)
			log(true, "wc.queueOutbox.size() == 0");
		else
			log(false, "wc.queueOutbox.size() == " + wc.queueOutbox.size());
		
	}

	// Отвечает на вопрос, рандомно ли генерируются задачи из экземпляра TaskGenerator.
	// Возвращает: True, если всё работает верно и данные действительно рандомны. False, если произошла ошибка.
	public boolean isTaskGeneratorRandomize()
	{
		TaskGenerator tg = new TaskGenerator(); // Создание экземпляра генератора
		// Генерирование задач:
		Task[] tsk = new Task[] {tg.getTask(), tg.getTask(), tg.getTask(), tg.getTask(), tg.getTask()};
		
		if(tg.getCountReady() == tsk.length) // Проверяем, что количество верно.
			log(true, "isTaskGeneratorRandomize: tg.getCountReady() == tsk.length ==" + tsk.length);
		else
			log(false, "isTaskGeneratorRandomize: tg.getCountReady() (" + tg.getCountReady() + ") != tsk.length (" + tsk.length + ").");
		// Проверяем, чтобы они не совпадали.
		for(long i = 0; i < tsk.length - 1; i++)
		{
			for(long j = i + 1; j < tsk.length; j++)
			{
				if(i == j) continue;
				if(tsk[i].equals(tsk[j])) // Если нашлась индентичная пара, то
					return false; // отвечаем, что TaskGenerator работает не верно.
			}
		}
		// Если такой пары не существует, то всё работает верно.
		return true; 
	}
}
