package parallelization;

import org.javagrader.Allow;
import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

@Grade
public class BrickCounterTest {
    private static class MyBrick implements BrickCounter.Brick {
        private final String color;
        private final int size;

        public MyBrick(String color, int size) {
            this.color = color;
            this.size = size;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public int getSize() {
            return size;
        }
    }

    @Test()
    @Grade(value = 2, cpuTimeout = 1000)
    public void testThreadsTwoBricks() {
        BrickCounter.Brick[] bricks = new BrickCounter.Brick[]{
                new MyBrick("red", 1),
                new MyBrick("green", 2)
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            int[] counts = BrickCounter.countBricksTwoThreads(bricks, 2, brick -> brick.getSize() - 1, executor);
            assertEquals(2, counts.length);
            assertEquals(1, counts[0]);
            assertEquals(1, counts[1]);
        } finally {
            executor.shutdown();
        }
    }

    @Test()
    @Grade(value = 1, cpuTimeout = 1000)
    public void testThreeBricks() {
        // three bricks
        BrickCounter.Brick[] bricks = new BrickCounter.Brick[]{
                new MyBrick("red", 1),
                new MyBrick("green", 5),
                new MyBrick("red", 2),
        };

        // we have three boxes (box 0, box 1, and box 2)
        int numberOfBoxes = 3;

        // we put all red bricks in box 0 and all other bricks in box 2.
        // Note that box 1 is not used.
        Function<BrickCounter.Brick, Integer> sorter = brick -> {
            if(brick.getColor().equals("red")) {
                return 0; // box 0
            }
            else {
                return 2; // box 2
            }
        };

        // count how many bricks go into box 0, box 1, box 2
        int[] counts = BrickCounter.countBricks(bricks, numberOfBoxes, sorter);
        assertEquals(numberOfBoxes, counts.length);

        // there should be two (red) bricks in box 0, no bricks in box 1,
        // and one (green) brick in box 2.
        assertEquals(2, counts[0]);
        assertEquals(0, counts[1]);
        assertEquals(1, counts[2]);
    }

}
