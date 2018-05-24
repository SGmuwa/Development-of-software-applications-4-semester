package ru.sg_muwa;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class WeatherConveyor implements Runnable, AutoCloseable, Iterable<Task> {

    private static final String FileName = "WeatherConveyor.bin";

    public WeatherConveyor() {
        queueInbox = new ConcurrentLinkedQueueTask();
        queueOutbox = new ConcurrentLinkedQueueTask();
        loadDataBase();
    }

    public Set<Point> GetPoints()
    {
        return dataBase.keySet();
    }
    
    HashMap<Point, Weather> dataBase;

    public void AddData(Point point, Weather weather) {
        dataBase.put(point, weather);
    }

    // Загрузка базы данных из файла
    private void loadDataBase()  {
        try (FileInputStream fis = new FileInputStream(FileName); ObjectInputStream oin = new ObjectInputStream(fis)) {
            dataBase = (HashMap<Point, Weather>) oin.readObject();
        } 
        catch(Exception e)
        {
            dataBase = new HashMap<>();
        }
    }

    // Сохранение базы данных в файл
    private static void saveDataBase(HashMap<Point, Weather> input) throws FileNotFoundException, IOException {
        if (input == null) {
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(FileName)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(input);
            oos.flush();
            oos.close();
        }
    }

    // Делает шаг.
    public void step() {
        Task buffer = getWeather(queueOutbox.poll());
        if (buffer != null) {
            queueOutbox.add(buffer);
        }
    }

    // Функция ищит соответсвие погоды по запросу.
    private Task getWeather(Task input) {
        if (input == null) {
            return null; // Если нет задания, то выходим.
        }
        input.weatherPointEditor.weather.setWeather // Устанавливаем такую погоду, которую получаем из
                (
                        dataBase.get // Базы данных, которая возвращает значение
                                (
                                        input.weatherPointEditor.point // исходя из точки во времени и пространстве.
                                )
                );
        return input;
    }

    public void AddToQueueInbox(Task task)
    {
        queueInbox.add(task);
    }

    public Task pollFromQueueOutbox()
    {
        return queueOutbox.poll();
    }

    public int sizeQueueInbox()
    {
        return queueInbox.size();
    }

    public int sizeQueueOutbox()
    {
        return queueOutbox.size();
    }

    private ConcurrentLinkedQueueTask queueInbox;
    private ConcurrentLinkedQueueTask queueOutbox;

    @Override
    public void run() {
        
        while (!Thread.currentThread().isInterrupted()) {
            step();
        }
    }

    @Override
    public void close() throws Exception {
        saveDataBase(dataBase);
    }

    @Override
    public Iterator<Task> iterator() {
        return queueOutbox.iterator();
    }
}
