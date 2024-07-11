package algorithms;

import org.javagrader.Grade;
import org.javagrader.GradeFeedback;
import org.javagrader.TestResultStatus;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


@Grade
class BagTest {

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testAddIsEmpty() {
        Bag bag = Bag.create();
        assertTrue(bag.isEmpty());
        bag.add(1);
        assertFalse(bag.isEmpty());
        bag.add(-5);
        assertFalse(bag.isEmpty());
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testAddRemoveIsEmpty() {
        Bag bag = Bag.create();
        bag.add(1);
        bag.add(1);
        bag.add(-5);
        bag.remove(1);
        bag.remove(11); // does nothing since 11 is not in the bag
        bag.remove(1);
        assertFalse(bag.isEmpty());
        bag.remove(-5);
        assertTrue(bag.isEmpty());
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testAddCount() {
        Bag bag = Bag.create();
        bag.add(1);
        bag.add(1);
        bag.add(2);
        assertEquals(2, bag.count(1));
        assertEquals(1, bag.count(2));
        assertEquals(0, bag.count(-7)); // -7 is not in the bag
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testAddRemoveCount() {
        Bag bag = Bag.create();
        bag.add(1);
        bag.add(1);
        bag.add(2);
        bag.add(-7);
        assertEquals(2, bag.count(1));
        assertEquals(1, bag.count(2));
        assertEquals(1, bag.count(-7));
        bag.remove(1);
        assertEquals(1, bag.count(1));
        bag.remove(88);
        assertEquals(1, bag.count(1));
        bag.remove(-7);
        bag.remove(1);
        assertEquals(0, bag.count(-7));
        assertEquals(0, bag.count(1));
    }



    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testFilterAddRemoveIsEmpty() {
        Bag bag = Bag.create();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(3);
        Bag filteredBag = bag.filter(x -> x % 2 != 0);
        // the filteredBag contains 1,3,3
        assertFalse(filteredBag.isEmpty());
        assertTrue(filteredBag.filter(x -> false).isEmpty()); // remove all the elements and test the result is empty
        filteredBag.remove(1);
        assertFalse(filteredBag.isEmpty());
        filteredBag.remove(3);
        assertFalse(filteredBag.isEmpty());
        filteredBag.remove(3);
        assertTrue(filteredBag.isEmpty());
    }



    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testAddIterator() {
        Bag bag = Bag.create();
        bag.add(0);
        bag.add(1);
        bag.add(2);
        bag.add(0);
        bag.add(1);
        bag.add(2);
        int [] count = new int[3];
        for (int i : bag) {
            count[i]++;
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(2, count[i]);
        }
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testAddFailFastIterator() {
        Bag bag = Bag.create();
        bag.add(0);
        bag.add(1);
        bag.add(2);
        bag.add(0);
        bag.add(1);
        bag.add(2);
        Iterator<Integer> ite = bag.iterator();
        ite.next();
        bag.add(3);
        assertThrows(ConcurrentModificationException.class, () -> {
            // an exception is thrown when the next() method is called because the bag has been modified
            // while the iterator was active
            ite.next();
        });
    }






}