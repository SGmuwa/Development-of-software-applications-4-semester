package ru.sg_muwa.Point;

import java.io.Serializable;
import java.time.LocalDate;

// Класс реализует хранение точки во времени и пространстве.
public class Point implements Serializable {

    public final LocalDate date;
    public final String city;

    public Point(LocalDate date, String city) {
        this.date = date;
        this.city = city;
    }

    @Override
    public boolean equals(Object ex) {
        if (this == ex) {
            return true;
        }
        if(ex instanceof Point) {
            return date.equals(((Point)ex).date) && city.equals(((Point)ex).city);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return city.hashCode() + date.hashCode();
    }
    
    @Override
    public String toString() {
        return "Date: " + date.toString() + " city: " + city;
    }
}
