﻿class WeatherPoint {
	public final Point point;
	public final Weather weather;
	
	WeatherPoint(Point point)
	{
		this.point = point;
		this.weather = new Weather();
	}
}