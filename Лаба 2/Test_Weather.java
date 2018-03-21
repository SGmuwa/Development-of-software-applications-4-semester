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
		log(true, "Test started");
		TaskGenerator tasks = new TaskGenerator(100);
		if (tasks.length != 100) log(false, "TaskGenerator: length = " + tasks.length);
		else log(true, "TaskGenerator: length.");
		if(isTasksRandomize(tasks)) log(true, "TaskGenerator: IsRandomize");
		else log(false, "TaskGenerator: Not randomize");
		
		WeatherConveyer wc = new WeatherConveyer();
		while(!tasks.isNeedGenerate) wc.quiueInbox.add(tasks.getTask());
		if(wc.quiueInbox != null) log(true, "quiueInbox is not null.");
		else
		{
			log(false, "quiueInbox is null.");
			return;
		}
		if(wc.quiueInbox.count == tasks.length) log(true, "wc.quiueInbox: count == tasks.length == " + tasks.length);
		else log(false, "wc.quiueInbox: count (" + wc.quiueInbox.count + ") != tasks.length (" + tasks.length + ")");

		if(wc.quiueOutbox != null) log(true, "quiueOutbox is not null.")
		else
		{
			log(false, "quiueOutbox is null.");
			return;
		}
		
		wc.stepGetWeatherFromInboxToOutbox();

		if(wc.quiueOutbox.count == 1) log(true, "quiueOutbox.count = 1");
		else log(false, "quiueOutbox.count = " + wc.quiueOutbox.count);

		if(wc.quiueInbox.count == 99) log(true, "quiueOutbox.count = 99");
		else log(false, "quiueInbox.count = " + wc.quiueOutbox.count);

		wc.start();
		
		Thread.sleep(100); // wait 100ms

		if(wc.quiueOutbox.count == 100) log(true, "quiueOutbox.count = 100.");
		else log(false, "quiueOutbox.count = " + wc.quiueOutbox.count);
		
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
