import ru.sg_muwa.Weather.*;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class Weather_Test {

    @Test
    public void Weather_Test() {
        Weather a = new Weather();
        assertTrue(a.setWeather("123"));
        assertFalse(a.setWeather("1243"));

        System.out.println("Test class Weather is end.");
    }
}