// Класс реализует хранение точки во времени и пространстве.
class Point implements Serializable {
	public final Date date;
	public final String city;

	
	public Point(Date date, String city) {
		this.date = date;
		this.city = city;
	}
}
