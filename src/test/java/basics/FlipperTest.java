package quizz;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

@Grade(value = 100)
public class FlipperTest {

    @Test
    @Grade(value = 0.05, cpuTimeout = 1)
    public void test1() {
        char[][] map = new char[][]{
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
        };


        assertEquals(5, Flipper.run(map));
    }

    @Test
    @Grade(value = 0.05, cpuTimeout = 1)
    public void test2() {
        char [][] map = new char[][]{
                {'.', '.','\\', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.','\\', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
        };
        assertEquals(10, Flipper.run(map));
    }

    @Test
    @Grade(value = 0.1, cpuTimeout = 1)
    public void test3() {
        char [][] map = new char[][]{
                {'.', '.','\\', '.', '.'},
                {'.','\\', '/', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.','\\', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
        };
        assertEquals(5, Flipper.run(map));
    }

    @Test
    @Grade(value = 0.05, cpuTimeout = 1)
    public void test4() {
        char[][] map = new char[][]{
                {'/'}
        };
        assertEquals(1, Flipper.run(map));
    }

    @Test
    @Grade(value = 0.05, cpuTimeout = 1)
    public void test5() {
        // empty map
        char[][] map = new char[][]{
                {}
        };
        assertEquals(0, Flipper.run(map));
    }

    @Test
    @Grade(value = 0.15, cpuTimeout = 1)
    public void test6() {
        char [][] map = new char[][]{
                {'.', '.', '.', '.','\\'},
                {'/', '.', '.','\\', '.'},
                {'.', '/','\\', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.','\\', '.', '/', '.'},
                {'\\', '.', '.', '.','/'},
        };
        assertEquals(40, Flipper.run(map));
    }

}
