
import java.io.*;
import java.util.HashMap;

public class WeatherConveyor implements Runnable, AutoCloseable {

    private static final String FileName = "WeatherConveyor.bin";

    public WeatherConveyor() {
        queueInbox = new ConcurrentLinkedQueueTask();
        queueOutbox = new ConcurrentLinkedQueueTask();
        loadDataBase();
    }

    HashMap<Point, Weather> dataBase;

    public void AddData(Point point, Weather weather) {
        dataBase.put(point, weather);
    }

    // Загрузка базы данных из файла
    private void loadDataBase() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(FileName);
        ObjectInputStream oin = new ObjectInputStream(fis);
        dataBase = (HashMap<Point, Weather>) oin.readObject();
        oin.close();
        fis.close();
    }

    // Сохранение базы данных в файл
    private static void saveDataBase(HashMap<Point, Weather> input) throws FileNotFoundException, IOException {
        if (input == null) {
            return;
        }
        FileOutputStream fos = new FileOutputStream(FileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(input);
        oos.flush();
        oos.close();
        fos.close();
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

    ConcurrentLinkedQueueTask queueInbox;
    ConcurrentLinkedQueueTask queueOutbox;

    public void run() {
        while (!Thread.IsInterrupted()) {
            step();
        }
    }

    @Override
    public void close() throws Exception {
        saveDataBase(dataBase);
    }
}
