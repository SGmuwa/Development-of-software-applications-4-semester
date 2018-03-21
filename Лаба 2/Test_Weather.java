public class Test_Weather {
	
	// Отправить информацию пользователю.
	public static void log(boolean IsStatusGood, String message) {
		if(status)
			System.out.println(message + " ok.");
		else {
			System.out.println("Error: " + message);
			System.err.println(message);
		}
	}
	public static void main(String[] args)
	{
		log(true, "Test started"); // Начало тестирования
		
		int countElms = 100; // количество тестируемых элементов
		
		TaskGenerator tasks = new TaskGenerator(countElms); // Готовим генератор задач к работе.
		if (tasks.getCount() != countElms) // В случае, если значение не верно.
			log(false, "TaskGenerator: count = " + tasks.getCount()); // Возвращаем ошибку.
		else log(true, "TaskGenerator: xcount."); // Иначе отвечаем, что всё хорошо.
		if(isTasksRandomize(tasks)) log(true, "TaskGenerator: IsRandomize"); // Проверяем, что задачи наши не одни и те же.
		else log(false, "TaskGenerator: Not randomize"); // В случае, если есть схожести, пишем об этом.
		
		WeatherConveyer wc = new WeatherConveyer(); // Готовим основной конвеер к работе.
		
		if(wc.quiueInbox != null)
			log(true, "quiueInbox is not null."); // Входящая очередь должна при этом инцелизироваться.
		else
		{ // Если нет, то тест нельзя дальше производить.
			log(false, "quiueInbox is null.");
			return;
		}
		
		while(!tasks.isNeedGenerate()) // Пока он не нужно генерировать.
			wc.quiueInbox.add(tasks.getTask()); // Добавить в входящую очередь задачу.
			
		if(wc.quiueInbox.getCount() == countElms) // В входной очереди должно быть countElms элементов.
			log(true, "wc.quiueInbox: count == countElms == " + countElms);
		else log(false, "wc.quiueInbox: count (" + wc.quiueInbox.getCount + ") != countElms (" + countElms + ")");

		if(wc.quiueOutbox != null) // Выходная очередь должна быть инцилизирована.
			log(true, "quiueOutbox is not null.")
		else
		{
			log(false, "quiueOutbox is null.");
			return;
		}
		
		wc.stepGetWeatherFromInboxToOutbox(); // Выполняем один шаг.

		if(wc.quiueOutbox.getCount() == 1) // Проверяем, что в выходной очереди действительно оказался 1 элемент.
			log(true, "quiueOutbox.count = 1");
		else
			log(false, "quiueOutbox.count = " + wc.quiueOutbox.getCount());

		if(wc.quiueInbox.getCount == countElms - 1)
			log(true, "quiueOutbox.count = 99"); // Проверяем, что из входной один элемент пропал.
		else
			log(false, "quiueInbox.count = " + wc.quiueOutbox.count);

		wc.startGetWeatherFromInboxToOutbox(); // Начать высчитывать в ассинхронном режиме.
		
		Thread.sleep(100); // wait 100ms

		if(wc.quiueOutbox.count == countElms) // Проверяем, что вся выходная очередь заполнена.
			log(true, "quiueOutbox.count = " + wc.quiueOutbox.getCount);
		else 
			log(false, "quiueOutbox.count = " + wc.quiueOutbox.getCount);
		
	}

	public boolean isRandomize(TaskGenerator arg)
	{
		for(long i = 0; i < arg.length - 1; i++)
		{
			for(long j = i + 1; j < arg.length; j++)
			{
				if(i == j) continue;
				if(!arg.getByIndex(i).equals(arg.getByindex(j))) return false;
			}
		}
		return true;
	}
}
