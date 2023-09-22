package basics;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class KNNTest {


    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test1() {

        // small example based on two inginious exercises
        KNN.Student[] students = new KNN.Student[] {
                new KNN.Student(new double[]{90,91}, true),
                new KNN.Student(new double[]{80,75}, true),
                new KNN.Student(new double[]{70,65}, false), // surprisingly, this student has failed despite a good preparation
                new KNN.Student(new double[]{30,40}, true),  // surprisingly, this student has succeeded despite a poor preparation
                new KNN.Student(new double[]{20,30}, false),
                new KNN.Student(new double[]{45,33}, false),
        };


        // let's take a student with good score at the exercises for our prediction with k=3
        double [] goodResults = new double[]{88,95};
        assertTrue(KNN.predictSuccess(students,goodResults,3));

        // let's take a student with poor score at the exercises for our prediction with k=3
        double [] badResults = new double[]{35,41};
        assertFalse(KNN.predictSuccess(students,badResults,3));

        // k = 6 = the number of students in the data (3 true, 3 false)
        // so not strict majority of true, the prediction should be false
        assertFalse(KNN.predictSuccess(students,goodResults,6));

    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test2() {

        // small example based on one a single inginious exercises
        KNN.Student[] students = new KNN.Student[] {
                new KNN.Student(new double[]{90}, true),
                new KNN.Student(new double[]{81}, true),
                new KNN.Student(new double[]{50}, false),
                new KNN.Student(new double[]{50}, true),
                new KNN.Student(new double[]{20}, false),
                new KNN.Student(new double[]{15}, false),
        };

        double [] goodResults = new double[]{92};
        assertTrue(KNN.predictSuccess(students,goodResults,3));
        double [] badResults = new double[]{21};
        assertFalse(KNN.predictSuccess(students,badResults,3));

    }


    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test3() {

        KNN.Student[] students = new KNN.Student[] {
                new KNN.Student(new double[]{100,100}, true),
                new KNN.Student(new double[]{90,91}, true),
                new KNN.Student(new double[]{80,75}, true),
                new KNN.Student(new double[]{70,65}, false),
                new KNN.Student(new double[]{70,33}, false),
                new KNN.Student(new double[]{70,50}, true),
                new KNN.Student(new double[]{70,54}, true),
                new KNN.Student(new double[]{30,48}, false),
                new KNN.Student(new double[]{90,10}, false),
                new KNN.Student(new double[]{0,100}, true),
                new KNN.Student(new double[]{30,40}, true),
                new KNN.Student(new double[]{20,30}, false),
                new KNN.Student(new double[]{45,33}, false),
        };




        assertFalse(KNN.predictSuccess(students, new double[]{66,20},6));
        assertFalse(KNN.predictSuccess(students, new double[]{66,20},4));
        assertTrue(KNN.predictSuccess(students, new double[]{0,100},1));
        assertFalse(KNN.predictSuccess(students, new double[]{70,33},1));
        assertTrue(KNN.predictSuccess(students, new double[]{50,50},5));
        assertTrue(KNN.predictSuccess(students, new double[]{60,80},5));

    }



}
