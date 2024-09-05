package algorithms;

import java.util.*;

/**
 * This is a data structure for tracking the best times
 * for participants in the Olympic Games of Paris.
 *
 * It also allows to iterate over the participants
 * in increasing order of their best times
 *
 * You must complete the implementation to pass the tests.
 *
 * Feel free to use all the imports and java classes you want.
 * You can add methods and instance variables.
 *
 * You can for instance look at the java.util.Map interface
 *       and the classes implementing this interface.
 *
 * We only ask you **not** to change the signature of the existing constructor and methods.
 */
public class BestTimeTracker implements Iterable<String> {

    // TODO: add instance variables of your choice

    /**
     * Constructs an empty BestTimeTracker.
     */
    public BestTimeTracker() {
        // TODO: implement the constructor
    }

    /**
     * Adds a new time for a participant.
     * If the participant already has a recorded time,
     * only keeps the best (lowest) time.
     *
     * @param participant the participant's name
     * @param time        the time to record
     */
    public void addTime(String participant, double time) {
        // TODO
    }

    /**
     * Returns the best time recorded for a participant.
     *
     * @param participant the participant's name
     * @return the best time recorded for the participant, or null if the participant has no recorded time
     */
    public Double getBestTime(String participant) {
         return -1.0;  // TODO
    }

    /**
     * Returns an iterator over the participants, in increasing order of their best time.
     * The iterator must implement the fail-fast strategy, i.e.
     * a ConcurrentModificationException must be thrown if there is a call to
     * "addTime()" between any two calls to the iterator methods.
     *
     * Hint 1: Unless you want to complexify your task,
     *       you should not implement any sorting algorithm by yourself.
     * Hint 2: you already get a few points if the order is not correct
     *
     * @return an iterator over the participants in increasing order of their best time
     */
    @Override
    public Iterator<String> iterator() {
         return null;  // TODO
    }

    public static void main(String[] args) {
        BestTimeTracker tracker = new BestTimeTracker();
        tracker.addTime("Alice", 12.5);
        tracker.addTime("Bob", 10.3);
        tracker.addTime("Alice", 11.2);
        tracker.addTime("Charlie", 9.8);
        tracker.addTime("Bob", 10.0);

        System.out.println("Best times in increasing order:");
        for (String participant : tracker) {
            System.out.println(participant + " best-time : " + tracker.getBestTime(participant));
        }
    }
}
