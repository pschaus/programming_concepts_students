package basics;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Grade
public class MatrixTest {


    @Test
    @Grade(value = 1)
    public void test1(){
        String input = "1 2 3\n4 5 6\n7 8 9";
        int [][] expected = {{1,2,3},{4,5,6},{7,8,9}};
        int [][] result = Matrix.buildFrom(input);
        assertArrayEquals(expected, result);
    }

    @Test
    @Grade(value = 1)
    public void test2(){
        String input = "1 2 3";
        int [][] expected = {{1,2,3}};
        int [][] result = Matrix.buildFrom(input);
        assertArrayEquals(expected, result);
    }

    @Test
    @Grade(value = 1)
    public void test3(){
        String input = "1\n2\n3";
        int [][] expected = {{1},{2},{3}};
        int [][] result = Matrix.buildFrom(input);
        assertArrayEquals(expected, result);
    }

    @Test
    @Grade(value = 1)
    public void test4(){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        assertEquals(45, Matrix.sum(matrix));
    }

    @Test
    @Grade(value = 1)
    public void test5(){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] transposed = {{1,4,7},{2,5,8},{3,6,9}};
        assertArrayEquals(transposed, Matrix.transpose(matrix));
    }

    @Test
    @Grade(value = 1)
    public void test6(){
        int[][] matrix1 = {{1,2,3},{4,5,6}};
        int[][] matrix2 = {{2},{4},{6}};
        int[][] product = {{28},{64}};
        assertArrayEquals(product, Matrix.product(matrix1, matrix2));
    }

}
