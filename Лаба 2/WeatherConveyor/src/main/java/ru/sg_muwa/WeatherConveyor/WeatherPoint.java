package ru.sg_muwa.WeatherConveyor;

import ru.sg_muwa.Point.Point;
import ru.sg_muwa.Weather.Weather;

import java.io.Serializable;

public class WeatherPoint implements Serializable {
	public final Point point;
	public final Weather weather;
	
	WeatherPoint(Point point)
	{
		this.point = point;
		this.weather = new Weather();
	}
        
        @Override
        public String toString() {
            return "Point: {" + point.toString() + "} weather: {" + weather.toString() + "}";
        }
}
