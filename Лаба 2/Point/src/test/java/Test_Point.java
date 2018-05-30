import ru.sg_muwa.Point.*;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class Test_Point {
    @Test
    public void StartTest() {
        Point a = new Point(LocalDate.of(2005, 05, 10), "Tlt");
        Point b = new Point(LocalDate.of(2005, 05, 10), "Tlt");

        assertEquals("Ожидалось, что они одинаковы.", a, b);

        System.out.println("Testing of Point is end.");
    }
}