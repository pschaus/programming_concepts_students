package basics;

import java.util.Random;


import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@Grade
public class PatternMatchingTest {

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test1() {
        assertEquals(3, PatternMatching.find("Hello", "abcHello"));
        assertEquals(-1, PatternMatching.find("hello", "abcHello"));
        assertEquals(-1, PatternMatching.find("Hello", "abcHell"));
        assertEquals(0, PatternMatching.find("Hello", "HelloHelloHello"));
        assertEquals(4, PatternMatching.find("Hello", "elloHelloHello"));
    }


}
