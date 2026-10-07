package algorithms;

import org.javagrader.ConditionalOrderingExtension;
import org.javagrader.Grade;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.*;


@ExtendWith(ConditionalOrderingExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Grade
public class CircularLinkedListTest {


    @Grade(value=0.5)
    @Order(0)
    @Test
    public void simpleTestWithoutRemove(){
        // Build a circular linked list as follows
        // 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
        CircularLinkedList<Integer> student = new CircularLinkedList<>();
        List<Integer> correct = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            student.enqueue(i);
            correct.add(i);
        }
        Iterator<Integer> aIter = student.iterator();
        Iterator<Integer> bIter = correct.iterator();
        assertEquals(correct.size(),student.size());
        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
    }

    @Grade(value=0.5)
    @Order(0)
    @Test
    public void simpleTestWithRemoving(){
        // Build a circular linked list as follows
        // 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 .. -> 49
        CircularLinkedList<Integer> student = new CircularLinkedList<>();
        List<Integer> correct = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            student.enqueue(i);
            correct.add(i);
        }

        // Remove 0, 10, [End], add 50, remove 25, 30
        student.remove(0);
        correct.remove(0);
        student.remove(10);
        correct.remove(10);
        student.remove(correct.size() - 1);
        correct.remove(correct.size() - 1);
        student.enqueue(50);
        correct.add(50);
        student.remove(25);
        correct.remove(25);
        student.remove(30);
        correct.remove(30);


        Iterator<Integer> aIter = student.iterator();
        Iterator<Integer> bIter = correct.iterator();
        assertEquals(correct.size(),student.size());
        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
    }

}
