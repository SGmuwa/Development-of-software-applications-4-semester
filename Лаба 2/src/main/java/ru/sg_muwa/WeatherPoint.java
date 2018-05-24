package ru.sg_muwa;

import java.io.Serializable;

class WeatherPoint implements Serializable {
	public final Point point;
	public final Weather weather;
	
	WeatherPoint(Point point)
	{
		this.point = point;
		this.weather = new Weather();
	}
        
        @Override
        public String toString() {
            return "Point: {" + point.toString() + "} weather: " + weather;
        }
}
