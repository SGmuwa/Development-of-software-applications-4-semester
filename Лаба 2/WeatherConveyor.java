public class WeatherConveyor implements Runnable {
	
	
	public void run() {
		//
	}
	
	// Функция, которая возвращает список поддерживаемых городов.
	public String[] getCities() {
		
	}

//-------------------------

	public class ConcurrentLinkedQueueTask extends ConcurrentLinkedQueue<Task> {
		private int id = 0;
		
		// Добавляет элемент в очередь
		@Override
		public boolean add(Task e) {
			e.setId(id++);
			return super.add(e);
		}
	}
//------------------------------
}
