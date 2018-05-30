package ru.sg_muwa.Weather;

import java.io.Serializable;

// Класс, который реализует контейнер для погоды. Его задача: позволить ожидать команды записи погоды. После записи запретить дальнейшние изменения погоды.
public class Weather implements Serializable {

    private boolean weatherChange; // Значение погоды менялось?
    private String weather;


    public Weather(String weather) {
        Builder(weather);
    }

    public Weather() {
        Builder(null);
    }

    public
    synchronized // По плану сюда и не будут забегать сразу несколько потоков. Но для защиты добавлено это ключевое слово.
    boolean setWeather(String weather) {
        if (weatherChange == false) {
            this.weatherChange = true;
            this.weather = weather;
            return true;
        } else return false;
    }

    public boolean setWeather(Weather weather) {
        if (weatherChange == false) {
            this.weatherChange = true;
            if(weather == null)
                this.weather = null;
            else
                this.weather = weather.getWeather();
            return true;
        } else return false;
    }

    public String getWeather() {
        return weather;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof Weather)
            return this.weather.equals(((Weather)obj).weather);
        if(obj instanceof String)
            return this.weather.equals((String)obj);
        else return false;
    }

    private void Builder(String weather) {
        this.weather = weather;
        this.weatherChange = weather != null && !weather.equals("");
    }

    public String toString() {
        return "Weather: " + this.weather + ", weatherChange: " + this.weatherChange + "";
    }
}
