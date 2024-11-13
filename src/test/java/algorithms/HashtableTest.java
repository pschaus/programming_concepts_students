package algorithms;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Grade
public class HashtableTest {

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test() {

        Hashtable map = new Hashtable();
        map.put("Apple", 5);
        map.put("Orange", 10);
        map.put("Banana", 1);
        map.put("Grape", 20);

        assertEquals(1, map.delete("Banana"));
        assertNull(map.delete("Peach"));
    }


}
