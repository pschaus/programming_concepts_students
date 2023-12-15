package parallelization;

import org.javagrader.Allow;
import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@Grade
@Allow("java.lang.Thread")
public class CountPrimeNumbersTest {

    @Test
    @Grade(value = 1, cpuTimeout = 2000)
    public void testIsPrime() {
        assertFalse(CountPrimeNumbers.isPrime(-10));
        assertFalse(CountPrimeNumbers.isPrime(-1));
        assertFalse(CountPrimeNumbers.isPrime(0));
        assertFalse(CountPrimeNumbers.isPrime(1));
        assertTrue(CountPrimeNumbers.isPrime(2));
        assertTrue(CountPrimeNumbers.isPrime(3));
        assertFalse(CountPrimeNumbers.isPrime(4));
        assertTrue(CountPrimeNumbers.isPrime(5));
        assertFalse(CountPrimeNumbers.isPrime(6));
        assertTrue(CountPrimeNumbers.isPrime(7));
        assertFalse(CountPrimeNumbers.isPrime(8));
        assertFalse(CountPrimeNumbers.isPrime(9));
        assertFalse(CountPrimeNumbers.isPrime(10));
        assertTrue(CountPrimeNumbers.isPrime(11));
        assertTrue(CountPrimeNumbers.isPrime(19));
        assertTrue(CountPrimeNumbers.isPrime(17));
        assertFalse(CountPrimeNumbers.isPrime(0));
        assertFalse(CountPrimeNumbers.isPrime(27));
        assertTrue(CountPrimeNumbers.isPrime(8191));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 2000)
    public void testCountSequential() {
        assertEquals(0, CountPrimeNumbers.countPrimesInInterval(-1000, 2));
        assertEquals(1, CountPrimeNumbers.countPrimesInInterval(-1000, 3));
        assertEquals(24, CountPrimeNumbers.countPrimesInInterval(-1000, 97));
        assertEquals(25, CountPrimeNumbers.countPrimesInInterval(-1000, 98));
        assertEquals(6, CountPrimeNumbers.countPrimesInInterval(70, 100));
        assertEquals(1061, CountPrimeNumbers.countPrimesInInterval(1000, 10000));
        assertEquals(0, CountPrimeNumbers.countPrimesInInterval(10000, 1000));
    }

    private int executeRunnable(int startIndex, int endIndex) {
        CountPrimeNumbers.CountPrimesRunnable r = new CountPrimeNumbers.CountPrimesRunnable(startIndex, endIndex);
        r.run();
        return r.getResult();
    }        
        
    private int executeCallable(int startIndex, int endIndex) {
        return new CountPrimeNumbers.CountPrimesCallable(startIndex, endIndex).call();
    }        
        
    private int executeSharedCounter(int startIndex, int endIndex) {
        CountPrimeNumbers.SharedCounter counter = new CountPrimeNumbers.SharedCounter();
        counter.set(-42);
        CountPrimeNumbers.CountPrimesSharedCounter r = new CountPrimeNumbers.CountPrimesSharedCounter(counter, startIndex, endIndex);
        r.run();
        return counter.getValue() + 42;
    }        
        
    @Test
    @Grade(value = 1, cpuTimeout = 2000)
    public void testCountRunnable() {
        assertEquals(0, executeRunnable(-1000, 2));
        assertEquals(1, executeRunnable(-1000, 3));
        assertEquals(24, executeRunnable(-1000, 97));
        assertEquals(25, executeRunnable(-1000, 98));
        assertEquals(6, executeRunnable(70, 100));
        assertEquals(1061, executeRunnable(1000, 10000));
        assertEquals(14, executeRunnable(700, 800));
        assertEquals(92, executeRunnable(1300, 2000));
    }
        
    @Test
    @Grade(value = 1, cpuTimeout = 2000)
    public void testCountCallable() {
        assertEquals(0, executeCallable(-1000, 2));
        assertEquals(1, executeCallable(-1000, 3));
        assertEquals(24, executeCallable(-1000, 97));
        assertEquals(25, executeCallable(-1000, 98));
        assertEquals(6, executeCallable(70, 100));
        assertEquals(1061, executeCallable(1000, 10000));
        assertEquals(114, executeRunnable(5000, 6000));
    }
        
    @Test
    @Grade(value = 1, cpuTimeout = 2000)
    public void testSharedCounter() {
        assertEquals(0, executeCallable(-1000, 2));
        assertEquals(1, executeCallable(-1000, 3));
        assertEquals(24, executeCallable(-1000, 97));
        assertEquals(25, executeCallable(-1000, 98));
        assertEquals(6, executeCallable(70, 100));
        assertEquals(59, executeCallable(500, 900));
        assertEquals(68, executeRunnable(4700, 5300));
    }


}
