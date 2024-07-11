package algorithms;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * This interface represents a collection of integers, similar to a set but allowing duplicates.
 * The collection is iterable and provides basic operations like adding, removing,
 * and counting elements.
 *
 * Here is an example of how to use a Bag:
 *
 *         Bag b = create();
 *         b.add(4);
 *         b.add(8);
 *         b.add(4);
 *         b.add(2);
 *         // at this point the content of the bag is {4, 8, 4, 2} (the order is not important)
 *         b.count(4); // returns 2 since there are two occurrences of 4 in the bag
 *         b.count(1); // returns 0 since there are no occurrences of 1 in the bag
 *         b.count(8); // returns 1 since there is one occurrence of 8 in the bag
 *         b.remove(4);
 *         // at this point the content of the bag is {4, 8, 2}
 *         b.count(4); // returns 1 since there is one occurrence of 4 in the bag
 *         b = b.filter(x -> x >= 4); // returns a new Bag containing 4, 8 since only those elements are >= 4
 *         // at this point the content of the bag is {4, 8}
 *         b.count(2); // returns 0 since there are no occurrences of 2 in the bag
 *         // iterate over the elements of the bag. This implicitly uses the iterator() method
 *         for (int i : b) { // prints 4, 8 (in any order)
 *             System.out.println(i);
 *         }
 *
 *   You have to complete the implementation of the Bag interface below.
 *
 */
public interface Bag extends Iterable<Integer> {

    /**
     * Adds the specified integer to the bag.
     * @param o The integer to add.
     */
    public void add(int o);

    /**
     * Removes one occurrence of the specified integer from the bag, if present.
     * @param o The integer to remove.
     */
    public void remove(int o);

    /**
     * Checks if the bag is empty.
     * @return true if the bag is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Counts the number of occurrences of a specified integer in the bag.
     * @param o The integer to count.
     * @return The number of occurrences of the integer.
     */
    public int count(int o);

    /**
     * Filters the bag based on a given predicate and returns a new bag containing
     * only elements that satisfy the predicate.
     * @param filter The predicate to apply to each element.
     * @return A new Bag containing the elements that satisfy the predicate.
     */
    public Bag filter(Predicate<Integer> filter);

    /**
     * Returns an iterator over elements of type {@code Integer}.
     * @return an Iterator.
     */
    public Iterator<Integer> iterator();

    /**
     * Creates a new instance of a Bag.
     * @return A new instance of a Bag.
     */
    public static Bag create() {
        return new BagImpl();
    }
}

class BagImpl implements Bag {
    // TODO: Implement the Bag interface here
    // Feel free to implement it using the data structure of your choice
    // or use any other class from the Java language to help you.
    // The time complexity of each method should be at most O(n) where n is the number of elements in the bag.


    public BagImpl() {
		// TODO
    }

    @Override
    public void add(int o) {
		// TODO

    }

    @Override
    public void remove(int o) {
		// TODO

    }

    @Override
    public boolean isEmpty() {
		// TODO
         return false;
    }

    @Override
    public int count(int o) {
		// TODO
         return 0;
    }

    @Override
    public Bag filter(Predicate<Integer> filter) {
		// TODO
         return null;
    }

    @Override
    public Iterator<Integer> iterator() {
		// TODO
         return null;
    }
}
