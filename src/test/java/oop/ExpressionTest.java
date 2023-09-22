package oop;


import java.util.Random;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Grade
public class ExpressionTest {

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test1() {

        Expression x = Expression.x();
        Expression four = Expression.value(4);

        // x^2+x+4
        Expression exp = x.mul(x).plus(x).plus(four);
        assertEquals(10,exp.evaluate(2),0.001);

        // 2x + 1
        Expression expPrime = exp.derivate();
        assertEquals(5,expPrime.evaluate(2),0.001);


    }

}
