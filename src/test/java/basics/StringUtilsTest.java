package basics;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;
import java.util.stream.Stream;


@Grade
public class StringUtilsTest {

    @Test
    @Grade(value = 1)
    public void test1(){
        String init = "A.bcd.e.";
        char marker = '.';
        String [] expected = {"A", "bcd", "e",""};
        String [] result = StringUtils.split(init, marker);
        assertArrayEquals(expected, result);
    }

    @Test
    @Grade(value = 1)
    public void test2(){
        String init = "Abcdef";
        char marker = '.';
        String [] expected = {"Abcdef"};
        String [] result = StringUtils.split(init, marker);
        assertArrayEquals(expected, result);
    }

    @Test
    @Grade(value = 1)
    public void test3(){
        String init = "Abcd";
        String pattern = "bc";
        int expected = 1;
        int result = StringUtils.indexOf(init, pattern);
        assertEquals(expected, result);
        pattern = "z";
        expected = -1;
        result = StringUtils.indexOf(init, pattern);
        assertEquals(expected, result);
        pattern = "Abcd";
        expected = 0;
        result = StringUtils.indexOf(init, pattern);
        assertEquals(expected, result);
    }

    @Test
    @Grade(value = 1)
    public void test4(){
        String upper = "heLlo WOrlD";
        String lower = "hello world";
        assertEquals(lower, StringUtils.toLowerCase(upper));
    }

    @Test
    @Grade(value = 1)
    public void test5(){
        String palindrome = "abba";
        assertTrue(StringUtils.palindrome(palindrome));
        palindrome = "abbaa";
        assertFalse(StringUtils.palindrome(palindrome));
        palindrome = "bab";
        assertTrue(StringUtils.palindrome(palindrome));
        palindrome = "baba";
        assertFalse(StringUtils.palindrome(palindrome));
    }

}
