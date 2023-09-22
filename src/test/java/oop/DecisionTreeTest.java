package oop;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@Grade
public class DecisionTreeTest {


    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test1() {

     //      4
     //     / \
     //    2   T
     //   / \
     //  F   T

        DecisionTree left = DecisionTree.splitNode(2,DecisionTree.decisionNode(false),DecisionTree.decisionNode(true));
        DecisionTree dt = DecisionTree.splitNode(4,left,DecisionTree.decisionNode(true));
        boolean [] input1 = new boolean[] {false,true,false,false,true};
        assertTrue(dt.predict(input1));
        boolean [] input2 = new boolean[] {false,true,true,false,false};
        assertTrue(dt.predict(input2));
        boolean [] input3 = new boolean[] {false,true,true,false,true};
        assertFalse(dt.predict(input3));

    }



}
