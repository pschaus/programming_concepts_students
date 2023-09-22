package basics;

import org.javagrader.Grade;
import org.javagrader.GradeFeedback;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class MagicSquareTest {



    @Test
    @Grade(value = 6, cpuTimeout = 1000)
    @GradeFeedback(message = "Verify permutation (all numbers from 1..n^2 present exactly once")
    public void testPermutation() {

        int[][] magic = new int[][]
                       {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};

        int[][] notMagic1 = new int[][]
                       {{1, 9, 5},
                        {9, 5, 1},
                        {5, 1, 9}};

        int[][] notMagic2 = new int[][]
                       {{1,1,16,16},
                        {1,16,1,16},
                        {16,1,16,1},
                        {16,16,1,1}};



        assertTrue(MagicSquare.isMagicSquare(magic));
        assertFalse(MagicSquare.isMagicSquare(notMagic1));
        assertFalse(MagicSquare.isMagicSquare(notMagic2));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    @GradeFeedback(message = "Verify the sum of columns")
    public void testSumColumns() {
        int[][] magic = new int[][]
                       {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};

        int[][] notMagic = new int[][]
                       {{2, 7, 4},
                        {9, 5, 1},
                        {6, 3, 8}};



        assertTrue(MagicSquare.isMagicSquare(magic));
        assertFalse(MagicSquare.isMagicSquare(notMagic));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    @GradeFeedback(message = "Verify the sum of rows")
    public void testSumRows() {
        int[][] magic = new int[][]
                       {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};

        int[][] notMagic = new int[][]
                       {{2, 7, 4},
                        {9, 5, 1},
                        {6, 3, 8}};

        assertTrue(MagicSquare.isMagicSquare(magic));
        assertFalse(MagicSquare.isMagicSquare(notMagic));
    }


    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    @GradeFeedback(message = "Verify sum of diagonals")
    public void testSumDiag() {
        int[][] magic = new int[][]
                       {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};

        // sum of diagonal problem
        int[][] notMagic = new int[][]
                       {{7, 2, 6},  // 15
                        {5, 9, 1},  // 15
                        {3, 4, 8}}; //

        assertTrue(MagicSquare.isMagicSquare(magic));
        assertFalse(MagicSquare.isMagicSquare(notMagic));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    @GradeFeedback(message = "Verify the range of values {1..n*n}")
    public void testRange() {
        int[][] magic = new int[][]
                       {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};

        int[][] notMagic = new int[][]
                       {{1, 6, 5},
                        {8, 4, 0},
                        {3, 2, 7}};

        assertTrue(MagicSquare.isMagicSquare(magic));
        assertFalse(MagicSquare.isMagicSquare(notMagic));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testSquare6() {

        int [][] sol6 = new int[][]{
                {1, 2, 3, 34, 35, 36, 4, 18, 28, 29, 5, 27, 10, 26, 30, 8, 23, 14, 31, 25, 13, 21, 15, 6, 32, 16, 20, 12, 22, 9, 33, 24, 17, 7, 11, 19},
                {1, 2, 3, 34, 35, 36, 4, 18, 29, 27, 5, 28, 10, 25, 30, 9, 23, 14, 31, 26, 12, 21, 15, 6, 32, 16, 20, 13, 22, 8, 33, 24, 17, 7, 11, 19},
                {1, 2, 3, 34, 35, 36, 4, 18, 29, 27, 5, 28, 10, 26, 30, 9, 23, 13, 31, 24, 12, 21, 15, 8, 32, 16, 20, 14, 22, 7, 33, 25, 17, 6, 11, 19},
                {1, 2, 3, 34, 35, 36, 4, 18, 29, 28, 5, 27, 10, 25, 30, 7, 24, 15, 31, 26, 13, 21, 14, 6, 32, 17, 20, 12, 22, 8, 33, 23, 16, 9, 11, 19},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 26, 5, 28, 10, 27, 30, 9, 21, 14, 31, 25, 8, 23, 13, 11, 32, 20, 24, 7, 22, 6, 33, 18, 17, 12, 15, 16},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 26, 5, 28, 10, 27, 30, 9, 22, 13, 31, 25, 11, 23, 15, 6, 32, 17, 24, 12, 18, 8, 33, 21, 14, 7, 16, 20},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 26, 5, 28, 10, 27, 30, 9, 24, 11, 31, 23, 13, 21, 17, 6, 32, 15, 16, 14, 22, 12, 33, 25, 20, 7, 8, 18},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 28, 5, 26, 10, 27, 30, 6, 21, 17, 31, 25, 11, 22, 15, 7, 32, 20, 14, 13, 23, 9, 33, 18, 24, 8, 12, 16},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 28, 5, 26, 10, 27, 30, 6, 24, 14, 31, 20, 13, 22, 17, 8, 32, 18, 15, 12, 23, 11, 33, 25, 21, 9, 7, 16},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 28, 5, 26, 10, 27, 30, 6, 23, 15, 31, 21, 13, 25, 9, 12, 32, 18, 20, 11, 22, 8, 33, 24, 16, 7, 17, 14},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 28, 5, 26, 10, 27, 30, 6, 17, 21, 31, 22, 13, 24, 14, 7, 32, 18, 16, 11, 25, 9, 33, 23, 20, 8, 15, 12},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 28, 5, 26, 10, 30, 27, 6, 21, 17, 31, 20, 13, 24, 14, 9, 32, 18, 16, 12, 25, 8, 33, 22, 23, 7, 11, 15},

        };

        int [][] notSol6 = new int[][]{
                {1, 2, 3, 34, 36, 36, 4, 18, 28, 29, 5, 27, 10, 26, 30, 8, 23, 14, 31, 25, 13, 21, 15, 6, 32, 16, 20, 12, 22, 9, 33, 24, 17, 7, 11, 19},
                {1, 2, 3,34, 36,  4, 18, 29, 27, 5, 28, 10, 25, 30, 9, 23, 14, 31, 26, 12, 21, 15, 6, 32, 16, 20, 35, 13, 22, 8, 33, 24, 17, 7, 11, 19},
                {2, 3, 34, 35, 36, 4, 18, 29, 27, 5, 28, 10, 26, 30, 9, 23, 13, 1, 31, 24, 12, 21, 15, 8, 32, 16, 20, 14, 22, 7, 33, 25, 17, 6, 11, 19},
                {1, 2, 3, 34, 35, 36, 4, 18, 29, 28, 5, 27, 10, 25, 30, 7, 24, 15, 31, 26, 13, 21, 14, 6, 33, 17, 20, 12, 22, 8, 33, 23, 16, 9, 11, 19},
                {1, 2, 3, 34, 35, 36, 4, 18, 29, 30, 5, 25, 11, 27, 28, 8, 22, 16, 31, 26, 14, 21, 13, 6, 32, 15, 20, 11, 24, 9, 33, 23, 17, 7, 12, 19},
                {1, 2, 3, 34, 35, 36, 4,18, 30, 26, 5, 28, 10, 25, 29, 12, 20, 15, 31, 27, 9, 22, 14, 8, 32, 16, 21, 11, 7,  24, 33, 23, 19, 6, 13, 17},
                {1, 2, 3, 34, 35, 36, 4, 19, 27, 30, 5, 26, 10, 29, 28, 6, 23, 15, 31, 22, 17, 21, 13, 7, 32, 15, 20, 12, 24, 9, 33, 25, 16, 8, 11, 18},
                {1, 2, 3, 34, 35, 36, 4, 19, 27, 30, 5, 26, 10, 29, 28, 6, 24, 14, 31, 21, 16, 22, 13, 8, 32, 15, 21, 12, 21, 9, 33, 25, 17, 7, 11, 18},
                {1, 2, 3, 34, 35, 36, 19, 28, 25, 5, 30, 10, 29, 8, 23, 14, 31, 20, 12, 24, 4, 15, 9, 32, 17, 21, 13, 22, 6, 33, 26, 18, 7, 27, 11, 16},
                {7, 2, 3, 34, 35, 36, 4, 19, 28, 25, 5, 30, 10, 29, 27, 8, 20, 17, 31, 24, 13, 23, 14, 6, 32, 16, 18, 12, 26, 7, 33, 21, 22, 9, 15, 11},
                {1, 3, 2, 34, 35, 36, 4, 19, 28, 26, 5, 29, 10, 30, 27, 6, 24, 14, 31, 23, 15, 22, 13, 7, 32, 16, 18, 12, 25, 8, 33, 21, 20, 11, 9, 17},
                {1, 4, 2, 3, 34, 35, 36, 19, 29, 28, 5, 26, 10, 27, 30, 6, 17, 21, 31, 22, 13, 24, 14, 7, 32, 18, 16, 11, 25, 9, 33, 23, 20, 8, 15, 12},
                {1, 2, 3, 34, 35, 36, 4, 19, 29, 28, 5, 26, 10, 30, 27, 6, 21, 17, 31, 20, 13, 24, 14, 9, 32, 18, 16, 25, 12, 8, 33, 22, 23, 7, 11, 15},
                {1, 2, 3, 34, 35, 36, 5, 19, 29, 28, 5, 26, 10, 30, 27, 6, 21, 17, 31, 20, 13, 24, 14, 9, 32, 18, 16, 12, 25, 8, 33, 22, 23, 7, 11, 15},
                {4, 19, 29, 28, 5, 26, 1, 2, 3, 34, 35, 36, 10, 30, 27, 6, 21, 17, 31, 20, 13, 24, 14, 9, 32, 18, 16, 12, 25, 8, 33, 22, 23, 7, 11, 15},
        };

        for (int i = 0; i < sol6.length; i++) {
            assertTrue(MagicSquare.isMagicSquare(toMatrix(sol6[i])));
        }

        for (int i = 0; i < notSol6.length; i++) {
            assertFalse(MagicSquare.isMagicSquare(toMatrix(notSol6[i])));
        }
    }

    static int[][] toMatrix(int [] array) {
        int n = (int) Math.round(Math.sqrt(array.length));
        int [][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = array[i*n+j];
            }
        }
        return res;
    }


}
