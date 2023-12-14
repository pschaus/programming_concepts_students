package fp;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * Exercise about creating infinite streams. The goal is to model the
 * stream of prime numbers, as well as the stream of gap size between
 * successive prime numbers.
 */
public class PrimeNumberStream {

    /**
     * Check whether the given number is prime. A prime is an integer
     * that is not a product of two smaller natural numbers. By
     * convention, negative numbers, zero, and 1 are not considered as
     * prime numbers.
     *
     * @param number The number to be tested.
     * @return true if the number is prime, false otherwise.
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
