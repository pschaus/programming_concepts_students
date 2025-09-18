package oop;

import org.javagrader.Grade;
import org.javagrader.GradeFeedback;
import org.javagrader.TestResultStatus;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Grade
public class RunLengthEncodingTest {

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testWithoutRepetition() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("", rle.getUncompressed());
        rle.append('a');
        assertEquals("a", rle.getUncompressed());
        rle.append('b');
        assertEquals("ab", rle.getUncompressed());
        rle.append('a');
        assertEquals("aba", rle.getUncompressed());

        checkCompressed(rle, new int[]{1,1,1});
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1)
    public void testWithRepetition() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("", rle.getUncompressed());
        rle.append('a');
        rle.append('a');
        assertEquals("aa", rle.getUncompressed());
        rle.append('b');
        rle.append('b');
        assertEquals("aabb", rle.getUncompressed());
        rle.append('a');
        rle.append('a');
        rle.append('a');
        assertEquals("aabbaaa",rle.getUncompressed());

        checkCompressed(rle, new int[]{2,2,3});
    }

    @Test
    @Grade(value = 2, cpuTimeout = 1)
    public void testConstructor() {
        String s = "dddddaaazzzzzzddd";
        RunLengthEncoding rle = new RunLengthEncoding(s);
        assertEquals(s, rle.getUncompressed());

        checkCompressed(rle, new int[]{5,3,6,3});
    }

    @Test
    @Grade(value = 1, cpuTimeout = 5)
    @GradeFeedback(message = "Are you doing something inefficient in your code?", on=TestResultStatus.FAIL)
    public void testLarge() {
        RunLengthEncoding rle = new RunLengthEncoding();
        for(int i=0; i<10_000; i++) {
            rle.append('a');
        }
        for(int i=0; i<10_000; i++) {
            rle.append('b');
        }
        checkCompressed(rle, new int[]{10_000,10_000});
    }

    // This method checks whether the compressed representation contains the expected
    // numbers.
    // For example, for "aaaaaabb", the compressed representation should consist
    // of two RepeatingValue objects, one with 5 repetitions and one with two repetitions.
    private void checkCompressed(RunLengthEncoding rle, int[] countsExpected) {
        int[] countsReturned = rle.getCompressed().stream().mapToInt(rv->rv.getNumRepetitions()).toArray();
        assertArrayEquals(countsExpected, countsReturned);
    }

}
