public class ConcurrentLinkedQueueTask extends ConcurrentLinkedQueue<Task> {
	private int id = 0;
	
	// Добавляет элемент в очередь
	@Override
	public boolean add(Task e) {
		e.setId(id++);
		return super.add(e);
	}
}