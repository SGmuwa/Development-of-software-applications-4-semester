import org.junit.Test;
import ru.sg_muwa.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;

import static org.junit.Assert.assertTrue;

public class Test_Weather {


	// Отправить информацию на консоль.
	// boolean isStatusGood: True, если всё идёт по-плану. False, если произошла ошибка.
	// String message: Текст сообщения.
	public void log(boolean isStatusGood, String message)
	{
		assertTrue(message, isStatusGood);
		System.out.println(message);
	}

	public void addDefaultData(String FileName)
    {
        try(WeatherConveyor wc = new WeatherConveyor(FileName)) {
            wc.addData(new Point(LocalDate.of(2018, 1, 1), "Moscow"), new Weather("-30, snow"));
			wc.addData(new Point(LocalDate.of(2018, 1, 2), "Moscow"), new Weather("-32, sun"));
			wc.addData(new Point(LocalDate.of(2018, 1, 3), "Moscow"), new Weather("-26, cloud"));
			wc.addData(new Point(LocalDate.of(2018, 1, 4), "Moscow"), new Weather("-18, sun"));
			wc.addData(new Point(LocalDate.of(2018, 1, 5), "Moscow"), new Weather("-22, sun"));
			wc.addData(new Point(LocalDate.of(2018, 1, 6), "Moscow"), new Weather("-21, cloud"));
			wc.addData(new Point(LocalDate.of(2018, 1, 7), "Moscow"), new Weather("-20, cloud"));
			wc.addData(new Point(LocalDate.of(2018, 1, 8), "Moscow"), new Weather("-22, snow"));
			wc.addData(new Point(LocalDate.of(2018, 1, 9), "Moscow"), new Weather("-20, snow"));
			wc.addData(new Point(LocalDate.of(2018, 1, 10), "Moscow"), new Weather("-19, snow"));
			wc.addData(new Point(LocalDate.of(2018, 1, 11), "Moscow"), new Weather("-19, rain"));
			wc.addData(new Point(LocalDate.of(2018, 1, 11), "Moscow"), new Weather("-20, sun"));
        }
        catch (IOException e)
        {
            log(false, "Не получилось сохранить настройки по-умолчанию.");
        }
    }

	@Test
	public void main() {
		log(true, "Test started."); // Начало тестирования

        String FileName = "WeatherConveyor.bin";

		addDefaultData(FileName);

		int countElms = 100; // количество тестируемых элементов.

		if(isTaskGeneratorRandomize(FileName) && isTaskGeneratorRandomize(FileName))
			log(true, "TaskGenerator: IsRandomize"); // Проверяем, что задачи наши не одни и те же.
		else log(false, "TaskGenerator: Not randomize"); // В случае, если есть схожести, пишем об этом.

		WeatherConveyor wc = new WeatherConveyor(FileName); // Готовим основной конвеер к работе.

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
			log(false, "queueOutbox.count = " + wc.sizeQueueOutbox());

		if(wc.sizeQueueInbox() == countElms - 1)
			log(true, "queueInbox.count = 99"); // Проверяем, что из входной один элемент пропал.
		else
			log(false, "queueInbox.count = " + wc.sizeQueueInbox());

		if (wc.pollFromQueueOutbox().weatherPointEditor.weather.getWeather() != null)
			log(true, "weather find.");
		else
			log(false, "weather not find.");

		Thread zds[] = new Thread[3];
		for(int i = 0; i < zds.length; i++) zds[i] = new Thread(wc);
		for( Thread zd : zds)
			zd.start(); // Начать высчитывать в ассинхронном режиме.
            try {
                Thread.sleep(100); // wait 100ms
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Test_Weather.class.getName()).log(Level.SEVERE, null, ex);
            }

		if(wc.sizeQueueOutbox() == countElms - 1) // Проверяем, что вся выходная очередь заполнена.
			log(true, "queueOutbox.count = " + wc.sizeQueueOutbox());
		else 
			log(false, "queueOutbox.count = " + wc.sizeQueueOutbox());
		
		Logger.printTasksAndClear(wc);

		if(wc.sizeQueueOutbox() == 0)
			log(true, "wc.queueOutbox.size() == 0");
		else
			log(false, "wc.queueOutbox.size() == " + wc.sizeQueueOutbox());

		try {
            wc.close();
        } catch (IOException e)
        {
            log(false, "Не удалось закрыть wc. " + e.getMessage());
        }
	}

	// Отвечает на вопрос, рандомно ли генерируются задачи из экземпляра TaskGenerator.
	// Возвращает: True, если всё работает верно и данные действительно рандомны. False, если произошла ошибка.
	public boolean isTaskGeneratorRandomize(String FileName)
	{
	    log(true, "isTaskGeneratorRandomize start.");

        WeatherConveyor a = new WeatherConveyor(FileName);

		TaskGenerator tg = new TaskGenerator(new WeatherConveyor(FileName).GetPoints()); // Создание экземпляра генератора

        try {
            a.close();
        }
        catch (IOException e) {
            log(false, "Не удалось закрыть wc в isTaskGeneratorRandomize. " + e.getMessage());
        }

        try
        {
            tg.getTask();
        } catch (java.lang.IllegalArgumentException e)
        {
            log(false, "Не правильная работа внутри weatherConveyor: " + e.getMessage());
            return false;
        }

		// Генерирование задач:
		Task[] tsk = new Task[] {tg.getTask(), tg.getTask(), tg.getTask(), tg.getTask(), tg.getTask()};
		
		if(tg.getCountReady() == tsk.length + 1) // Проверяем, что количество верно.
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
