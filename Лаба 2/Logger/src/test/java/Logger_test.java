import ru.sg_muwa.Logger.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class Logger_test {

    @Test
    public void startTestLogger() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add(2);
        a.add(3);

        TestIterator<Integer> b = new TestIterator<Integer>(a);

        assertEquals(3, b.size());

        Logger.printAll(b);

        assertEquals(0, b.size());

        System.out.println("Тестирование логгера закончено.");

    }


    public class TestIterator<T> implements Iterator<T>, Iterable<T> {

        public int size() {
            return list.size();
        }

        public TestIterator(List<T> imput) {
            list = imput;
        }

        public Iterator<T> iterator() {
            return (Iterator<T>) this;
        }

        private List<T> list;

        public boolean hasNext() {
            return !list.isEmpty();
        }

        public T next() {
            T a = list.get(0);
            list.remove(0);
            return a;
        }
    }
}
