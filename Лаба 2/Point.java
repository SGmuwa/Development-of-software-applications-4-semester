
import java.io.Serializable;
import java.util.Date;

// Класс реализует хранение точки во времени и пространстве.
class Point implements Serializable {

    public final Date date;
    public final String city;

    public Point(Date date, String city) {
        this.date = date;
        this.city = city;
    }
    
    @Override
    public String toString() {
        return "Date: " + date.toString() + " city: " + city;
    }
}
