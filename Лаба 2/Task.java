public class Task {
	private int id;
	private boolean isIdSet;
	public final WeatherPoint weatherPointEditor; // weatherPoint. Точка во времени и пространстве, а также хранит информацию о погоде.

	// Устанавливает идентификатор к заданию. Изменить идентификатор можно только один раз.
	// int id: присваеваемый идентификатор.
	// Возвращает: True, если операция принята и успешно завершена. False, если операция не разрешена и откланена.
	public boolean setId(int id) {
		if(isIdSet == false) {
			this.isIdSet = true;
			this.id = id;
			return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}
	
	public Task(Date date, String city) {
		this.id = -1;
		this.isIdSet = false;
		this.wp = new WeatherPoint(date, city);
	}
}
