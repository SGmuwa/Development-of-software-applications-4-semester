public class WeatherConveyer implements Runnable {
	public void run() {
		//
	}

//-------------------------

	public class ConcurrentLinkedQueueTask extends ConcurrentLinkedQueue<Task> {
		private int id = 0;
		
		@Override
		// Добавляет элемент в очередь
		public boolean add(Task e) {
			e.setId(id++);
			return super.add(e);
		}
	}
//------------------------------
}
