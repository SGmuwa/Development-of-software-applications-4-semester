import org.junit.Test;
import ru.sg_muwa.*;
import java.util.logging.Level;

import static org.junit.Assert.assertTrue;

public class Test_Weather {


	// Отправить информацию на консоль.
	// boolean isStatusGood: True, если всё идёт по-плану. False, если произошла ошибка.
	// String message: Текст сообщения.
	public static void log(boolean isStatusGood, String message)
	{
		assertTrue(message, isStatusGood);
	}

	@Test
	public static void main(String[] args)
	{
		log(true, "Test started."); // Начало тестирования
		
		int countElms = 100; // количество тестируемых элементов.

		if(isTaskGeneratorRandomize() && isTaskGeneratorRandomize())
			log(true, "TaskGenerator: IsRandomize"); // Проверяем, что задачи наши не одни и те же.
		else log(false, "TaskGenerator: Not randomize"); // В случае, если есть схожести, пишем об этом.

		WeatherConveyor wc = new WeatherConveyor(); // Готовим основной конвеер к работе.

		TaskGenerator tasks = new TaskGenerator(wc.GetPoints()); // Готовим генератор задач к работе.
		
		while(tasks.getCountReady() != countElms) // Пока не сгенерирует 100.
			wc.AddToQueueInbox(tasks.getTask()); // Добавить в входящую очередь задачу.
			
		if(wc.sizeQueueInbox() == countElms) // В входной очереди должно быть countElms элементов.
			log(true, "wc.queueInbox: size == countElms == " + countElms);
		else log(false, "wc.queueInbox: size (" + wc.sizeQueueInbox() + ") != countElms (" + countElms + ")");
		
		wc.step(); // Выполняем один шаг.

		if(wc.sizeQueueOutbox() == 1) // Проверяем, что в выходной очереди действительно оказался 1 элемент.
			log(true, "queueOutbox.count = 1");
		else
			log(false, "queueOutbox.count = " + wc.queueOutbox.size());

		if(wc.sizeQueueOutbox() == countElms - 1)
			log(true, "queueOutbox.count = 99"); // Проверяем, что из входной один элемент пропал.
		else
			log(false, "queueInbox.count = " + wc.queueOutbox.size());

		Thread zds[] = new Thread[3];
		for( Thread zd : zds)
			zd = new Thread(wc);
		for( Thread zd : zds)
			zd.start(); // Начать высчитывать в ассинхронном режиме.
            try {
                Thread.sleep(100); // wait 100ms
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Test_Weather.class.getName()).log(Level.SEVERE, null, ex);
            }

		if(wc.sizeQueueOutbox() == countElms) // Проверяем, что вся выходная очередь заполнена.
			log(true, "queueOutbox.count = " + wc.queueOutbox.size());
		else 
			log(false, "queueOutbox.count = " + wc.queueOutbox.size());
		
		Logger.printTasksAndClear(wc);

		if(wc.sizeQueueOutbox() == 0)
			log(true, "wc.queueOutbox.size() == 0");
		else
			log(false, "wc.queueOutbox.size() == " + wc.queueOutbox.size());
	}

	// Отвечает на вопрос, рандомно ли генерируются задачи из экземпляра TaskGenerator.
	// Возвращает: True, если всё работает верно и данные действительно рандомны. False, если произошла ошибка.
	public static boolean isTaskGeneratorRandomize()
	{
		TaskGenerator tg = new TaskGenerator(new WeatherConveyor().GetPoints()); // Создание экземпляра генератора
		// Генерирование задач:
		Task[] tsk = new Task[] {tg.getTask(), tg.getTask(), tg.getTask(), tg.getTask(), tg.getTask()};
		
		if(tg.getCountReady() == tsk.length) // Проверяем, что количество верно.
			log(true, "isTaskGeneratorRandomize: tg.getCountReady() == tsk.length ==" + tsk.length);
		else
			log(false, "isTaskGeneratorRandomize: tg.getCountReady() (" + tg.getCountReady() + ") != tsk.length (" + tsk.length + ").");
		// Проверяем, чтобы они не совпадали.
		for(int i = 0; i < tsk.length - 1; i++)
		{
			for(int j = i + 1; j < tsk.length; j++)
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
