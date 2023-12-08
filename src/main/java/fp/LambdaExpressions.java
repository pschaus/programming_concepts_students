package fp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class LambdaExpressions {
    /**
     * Return a binary operator that computes the sum of two Integer objects.
     */
    public static Object sumOfIntegers() {
        // TODO
         return null;
    }

    /**
     * Return a predicate that tests whether a String is empty.
     */
    public static Object isEmptyString() {
        // TODO
         return null;
    }

    /**
     * Return a predicate that tests whether an Integer is an odd number.
     */
    public static Object isOddNumber() {
        // TODO
         return null;
    }

    /**
     * Return a function that computes the mean of a List of Double objects.
     * If the list is empty, an IllegalArgumentException must be thrown.
     */
    public static Object computeMeanOfListOfDoubles() {
        // TODO
         return null;
    }

    /**
     * Remove the even numbers from a list of Integer objects.
     */
    public static void removeEvenNumbers(List<Integer> lst) {
        // TODO
    }

    /**
     * Return a function that computes the factorial of an Integer.
     * If the number is zero, the factorial equals 1 by convention.
     * If the number is negative, an IllegalArgumentException must be thrown.
     */
    public static Object computeFactorial() {
        // TODO
         return null;
    }

    /**
     * Return a function that converts a list of String objects to lower case.
     */
    public static Object listOfStringsToLowerCase() {
        // TODO
         return null;
    }

    /**
     * Return a function that concatenates two String objects.
     */
    public static Object concatenateStrings() {
        // TODO
         return null;
    }

    public static class MinMaxResult {
        private int minValue;
        private int maxValue;

        MinMaxResult(int minValue,
                     int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        int getMinValue() {
            return minValue;
        }

        int getMaxValue() {
            return maxValue;
        }
    }

    /**
     * Return a function that computes the minimum and maximum values in a list.
     * The content of the Optional must be present if and only if the list is non-empty.
     */
    public static Function<List<Integer>, Optional<MinMaxResult>> computeMinMax() {
        // TODO
         return null;
    }

    /**
     * Return a function that, given a String object and a character, counts
     * the number of occurrences of the character inside the string.
     */
    public static Object countInstancesOfLetter() {
        // TODO
         return null;
    }
}
