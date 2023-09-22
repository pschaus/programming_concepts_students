package basics;// This file must *not* be modified!

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.math.BigDecimal;
import java.util.Random;



@Grade
public class PascalTest {
    
    
    @Test
    @Grade(value=1, cpuTimeout=100)
    public void testBasic() {    
        int [] row1 = new int[]{1};
        int [] row2 = new int[]{1, 1};
        int [] row3 = new int[]{1, 2, 1};
        int [] row4 = new int[]{1, 3, 3, 1};
        int [] row5 = new int[]{1, 4, 6, 4, 1};
        
        int [] r1 = Pascal.pascal(1);
        int [] r2 = Pascal.pascal(2);
        int [] r3 = Pascal.pascal(3);
        int [] r4 = Pascal.pascal(4);
        int [] r5 = Pascal.pascal(5);
        assertEquals(1, r1.length,"The first row is not of size 1");
        assertArrayEquals(row1, r1);
        assertEquals(2, r2.length, "The second row is not of size 2");
        assertArrayEquals(row2, r2);
        assertEquals( 3, r3.length, "The third row is not of size 3");
        assertArrayEquals(row3, r3);
        assertEquals(4, r4.length, "The fourth row is not of size 4");
        assertArrayEquals(row4, r4);
        assertEquals( 5, r5.length, "The fifth row is not of size 5");
        assertArrayEquals(row5, r5);
    }
    
}
