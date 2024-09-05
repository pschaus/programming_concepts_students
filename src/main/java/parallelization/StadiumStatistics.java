package parallelization;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * You are part of the organizing committee of the 2024 Summer Olympics in Paris.
 * Your task is to create a software that computes the age distribution of the supporters attending
 * the events in the Stade de France, taking into account the nationality of the supporters.
 * Because the stadium can gather a lot of people, your computation must use multithreading.
 */
public class StadiumStatistics {

    /**
     * Interface defining one supporter attending the event in the stadium.
     */
    public interface Supporter {
        /**
         * Get the nationality of the supporter.
         *
         * @return The nationality.
         */
        public String getNationality();

        /**
         * Get the age of the supporter.
         *
         * @return The age (in number of years).
         */
        public int getAge();
    }

    /**
     * The different categories of ages you have to consider to describe the age distribution of the supporters.
     */
    public enum AgeCategory {
        BETWEEN_0_AND_9,      // Between 0 and 9 years old (inclusive)
        BETWEEN_10_AND_19,    // Between 10 and 19 years old (inclusive)
        BETWEEN_20_AND_29,    // Between 20 and 29 years old (inclusive)
        BETWEEN_30_AND_39,    // Between 30 and 39 years old (inclusive)
        BETWEEN_40_AND_49,    // Between 40 and 49 years old (inclusive)
        BETWEEN_50_AND_59,    // Between 50 and 59 years old (inclusive)
        BETWEEN_60_AND_69,    // Between 60 and 69 years old (inclusive)
        BETWEEN_70_AND_79,    // Between 70 and 79 years old (inclusive)
        ABOVE_80              // Above 80 years old (inclusive)
    }

    /**
     * Histogram encoding the age distribution of the supporters. This histogram must associate each
     * category of ages (cf. the "AgeCategory" enumeration) with the number of supporters of this age.
     */
    static public class AgeHistogram {
         // TODO - Add the private members of your data structure at this point

        /**
         * Your constructor. Initially, the number of supporters must be zero for each age category.
         */
        AgeHistogram() {
             // TODO (only if your data structure requires an initialization)
        }

        /**
         * Get the number of supporters in the given age category.
         *
         * @param category The age category of interest.
         * @return The number of supporters.
         * @throws IllegalArgumentException This exception must be thrown is the
         * requested age category does not exist in the "AgeCategory" enumeration.
         */
        public int getNumberOfSupporters(AgeCategory category) {
             return -1;  // TODO
        }

        /**
         * Add one new supporter to the age distribution.
         *
         * @param age The age of the supporter.
         * @throws IllegalArgumentException This exception must be thrown is the age is invalid
         * (i.e., if the age is a negative number).
         */
        public void addSupporter(int age) {
             // TODO
        }

        /**
         * Combine two distributions of ages. This consists in summing the number of
         * supporters in both age distributions, for each age category.
         *
         * @param histogram1 The first age distribution.
         * @param histogram2 The second age distribution.
         * @return The combined age distribution.
         */
        static public AgeHistogram combine(AgeHistogram histogram1,
                                           AgeHistogram histogram2) {
             return null;  // TODO
        }
    }

    /**
     * Sequential algorithm to compute the age distribution of the supporters in the stadium,
     * for one nationality of interest. This method must *not* use threads.
     * <p>
     * In order to enable subsequent multithreading, this algorithm must consider only a subarray of the
     * provided array of supporters. More precisely, you are given two parameters "startIndex" and "endIndex",
     * and you must only consider the supporters in the range from "supporters[startIndex]" to
     * "supporters[endIndex - 1]". In the case where "startIndex == endIndex", there is no supporter to consider
     * and the resulting histogram contains zero for each age category.
     *
     * @param supporters  Information about all the supporters in the stadium.
     * @param nationality The nationality of interest. When computing the age distribution, you must
     *                    only include the supporters whose nationality corresponds to this argument.
     * @param startIndex  Start index of the subarray of supporters (inclusive).
     * @param endIndex    End index of the subarray of supporters (exclusive).
     * @return The age distribution.
     * @throws IllegalArgumentException If the value of "startIndex" or "endIndex" would lead to the reading
     * of an item that is out of the bounds of the "supporters" array. This exception is already implemented
     * for you before the "TODO": You do *not* have to raise this exception by yourself.
     */
    public static AgeHistogram computeAgeHistogramSequential(Supporter[] supporters,
                                                             String nationality,
                                                             int startIndex,
                                                             int endIndex) {
        if (startIndex < 0 || startIndex > endIndex || endIndex > supporters.length) {
            throw new IllegalArgumentException();
        }

         return null;  // TODO
    }

    /**
     * Parallel algorithm to compute the age distribution of the supporters in the stadium,
     * for one nationality of interest.
     * <p>
     * You *must* use two threads to carry on this computation, and you *must* use the provided thread pool.
     * You are encouraged to use the method "AgeHistogram.combine()" to combine the partial results computed
     * by the two threads.
     * <p>
     * You MUST catch all exceptions related to multithreading. If such an exception is thrown, you must return "null".
     *
     * @param executorService The thread pool to be used. You must *not* call the method "Executors.newFixedThreadPool()"
     *                        to create the thread pool, neither the method "executor.shutdown()" (this is done for you
     *                        in the unit tests).
     * @param supporters      Information about all the supporters in the stadium.
     * @param nationality     The nationality of interest. When computing the age distribution, you must only
     *                        include the supporters whose nationality corresponds to this argument.
     * @return The age distribution.
     */
    public static AgeHistogram computeAgeHistogramParallel(ExecutorService executorService,
                                                           Supporter[] supporters,
                                                           String nationality) {
         return null;  // TODO
    }
}
