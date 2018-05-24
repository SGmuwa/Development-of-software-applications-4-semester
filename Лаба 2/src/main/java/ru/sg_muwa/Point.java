package ru.sg_muwa;

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
    public String toString() {
        return "Date: " + date.toString() + " city: " + city;
    }
}
