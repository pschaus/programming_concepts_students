package oop;// This file must *not* be modified!

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;


import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class DotProductIteratorTest {
    static final float THRESHOLD = 0.000001f;

    private static double computeDotProduct(double[] a,
                                            double[] b) {
        return DotProductIterator.computeDotProduct(
                DoubleStream.of(a).iterator(), DoubleStream.of(b).iterator());
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testBasicProduct() {
        double[] a = {4, 5};
        double[] b = {6, 7};
        assertEquals(4 * 6 + 5 * 7, computeDotProduct(a, b), THRESHOLD);
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testBasicIterator() {
        double[] a = {2, 7};
        DotProductIterator.VectorIterator it = new DotProductIterator.VectorIterator(a);
        assertTrue(it.hasNext());
        assertEquals(2, it.next(), THRESHOLD);
        assertTrue(it.hasNext());
        assertEquals(7, it.next(), THRESHOLD);
        assertFalse(it.hasNext());
    }

}
