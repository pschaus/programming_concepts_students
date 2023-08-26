package fp;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 *
 */
public class PrimeNumber {

    /**
     * Check that number is prime (can be divided by 1 and himself)
     *
     * @param number a non negative number
     * @return true if number is prime, false otherwise
     */
    public static boolean isPrime(int number) {
        // TODO
         return true;
    }

    /**
     * Generates an infinite stream of consecutive integers starting from a given value
     * @param from the start of the stream
     * @return an infinite stream from, from+1, from+2, ...
     */
    public static IntStream streamFrom(int from) {
        // TODO:
        // Hint: Consider using java.util.stream.IntStream.iterate method
         return null;
    }

    public static IntStream primeStreamFrom(int from) {
        // TODO
        // Hint: use filter
         return null;
    }

    /**
     * Generate an infinite stream of prime gaps (difference between two successive prime numbers)
     * computed on the stream of prime numbers
     * starting at the first prime number larger or equal to from.
     * Example: from = 5 (5, 7, 11, 13, 17, 19, ...) , the stream of prime gaps is thus 2, 4, 2, 4, 2, ...
     *
     * @param from
     * @return an infinite stream of prime gaps
     */
    public static IntStream primeGapStreamFrom(int from) {
        // TODO
         return null;
    }

}
