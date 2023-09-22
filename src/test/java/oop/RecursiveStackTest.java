package oop;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Random;

@Grade
public class RecursiveStackTest {

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test1() {
        RecursiveStack<Integer> e = new RecursiveStack<>();

        assertEquals(0, e.size());

        e = e.add(2);
        e = e.add(3);
        e = e.add(4);



        assertEquals(3, e.size());
        assertEquals(Integer.valueOf(4), e.top());
        e = e.removeTop(); // remove 4
        assertEquals(2, e.size());
        assertEquals(Integer.valueOf(3), e.top());
        e = e.removeTop(); // remove 3
        assertEquals(1, e.size());
        assertEquals(Integer.valueOf(2), e.top());
        e = e.removeTop(); // remove 2
        assertEquals(0, e.size());

    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testReverseAlone() {
        RecursiveStack<Integer> e = new RecursiveStack<>();

        e = e.add(2);
        e = e.add(3);
        e = e.add(4);

        RecursiveStack re = e.reverse();



        assertEquals(Integer.valueOf(2),re.top());
        re = re.removeTop();
        assertEquals(Integer.valueOf(3),re.top());
        re = re.removeTop();
        assertEquals(Integer.valueOf(4),re.top());
        re = re.removeTop();

        e = e.reverse().reverse();

        assertEquals(Integer.valueOf(4),e.top());
        e = e.removeTop();
        assertEquals(Integer.valueOf(3),e.top());
        e = e.removeTop();
        assertEquals(Integer.valueOf(2),e.top());
        e = e.removeTop();


    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testReverse() {
        RecursiveStack<Integer> e = new RecursiveStack<>();

        e = e.add(2);
        e = e.add(3);
        e = e.add(4);

        RecursiveStack re = e.reverse();

        Iterator<Integer> ite = re.iterator();
        assertTrue(ite.hasNext());
        assertEquals(Integer.valueOf(2),ite.next());
        assertTrue(ite.hasNext());
        assertEquals(Integer.valueOf(3),ite.next());
        assertTrue(ite.hasNext());
        assertEquals(Integer.valueOf(4),ite.next());
        assertFalse(ite.hasNext());
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testEmptyIterator() {
        RecursiveStack<Integer> e = new RecursiveStack<>();

        Iterator<Integer> ite = e.iterator();
        assertFalse(ite.hasNext());
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testIteratorTopDown() {
        RecursiveStack<Integer> e = new RecursiveStack<>();

        e = e.add(2);
        e = e.add(3);
        e = e.add(4);

        Iterator<Integer> ite = e.iterator();
        assertTrue(ite.hasNext());
        assertEquals(Integer.valueOf(4),ite.next());
        assertTrue(ite.hasNext());
        assertEquals(Integer.valueOf(3),ite.next());
        assertTrue(ite.hasNext());
        assertEquals(Integer.valueOf(2),ite.next());
        assertFalse(ite.hasNext());
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testEmptyTop() {
        RecursiveStack<Integer> e = new RecursiveStack<>();
        assertThrows(EmptyStackException.class, () ->  e.top());
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testEmpty() {
        RecursiveStack<Integer> e = new RecursiveStack<>();

        e = e.add(2);
        e = e.add(3);

        e = e.removeTop();
        final RecursiveStack<Integer> ef = e.removeTop();
        assertThrows(EmptyStackException.class, () -> ef.removeTop());
    }




}