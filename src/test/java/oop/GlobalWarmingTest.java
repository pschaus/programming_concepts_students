package oop;// This file must *not* be modified!

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Grade
public class GlobalWarmingTest {
    static final float THRESHOLD = 0.000001f;

    private static class Observer implements GlobalWarming.RecordObserver {
        private int countRecords;
        private float temperature;
        private String place;

        Observer(String place) {
            this.place = place;
        }

        @Override
        public void signalNewRecord(String place, float temperature) {
            if (place.equals(this.place)) {
                countRecords += 1;
                this.temperature = temperature;
            }
        }

        public int getCountRecords() {
            return countRecords;
        }

        public float getRecord() {
            if (countRecords > 0) {
                return temperature;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Test
    @Grade(value = 1, cpuTimeout=100)
    public void testSimple() {
        GlobalWarming.IPCCDatabase database = new GlobalWarming.IPCCDatabase();

        Observer paris = new Observer("Paris");
        assertEquals(0, paris.getCountRecords());
        database.addObserver(paris);

        Observer london = new Observer("London");
        assertEquals(0, london.getCountRecords());
        database.addObserver(london);

        database.temperatureMeasured("Paris", 15);
        assertEquals(1, paris.getCountRecords());
        assertEquals(0, london.getCountRecords());
        assertEquals(15, paris.getRecord(), THRESHOLD);

        database.temperatureMeasured("London", 25);
        assertEquals(1, paris.getCountRecords());
        assertEquals(1, london.getCountRecords());
        assertEquals(15, paris.getRecord(), THRESHOLD);
        assertEquals(25, london.getRecord(), THRESHOLD);

        database.temperatureMeasured("Paris", 10);
        assertEquals(1, paris.getCountRecords());
        assertEquals(1, london.getCountRecords());
        assertEquals(15, paris.getRecord(), THRESHOLD);
        assertEquals(25, london.getRecord(), THRESHOLD);

        database.temperatureMeasured("London", 10);
        assertEquals(1, paris.getCountRecords());
        assertEquals(1, london.getCountRecords());
        assertEquals(15, paris.getRecord(), THRESHOLD);
        assertEquals(25, london.getRecord(), THRESHOLD);

        database.temperatureMeasured("London", 30);
        assertEquals(1, paris.getCountRecords());
        assertEquals(2, london.getCountRecords());
        assertEquals(15, paris.getRecord(), THRESHOLD);
        assertEquals(30, london.getRecord(), THRESHOLD);

        database.temperatureMeasured("Paris", 28);
        assertEquals(2, paris.getCountRecords());
        assertEquals(2, london.getCountRecords());
        assertEquals(28, paris.getRecord(), THRESHOLD);
        assertEquals(30, london.getRecord(), THRESHOLD);
    }
    
}
