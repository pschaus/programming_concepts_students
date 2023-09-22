package complexity;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@Grade
public class MaximumSumSubarrayTest {
    
    
    @Test
    @Grade(value = 1)
    public void testSimple() {
        int [] array = new int[]{1 ,1 , 3, -10, 3, 4, -5, -3, 2, 1};
        
        MaximumSumSubarray.ArrayIndex solution = MaximumSumSubarray.maximumSumSubarray(array);
        assertEquals(4, solution.start);
        assertEquals(5, solution.end);
    }
    
    
}
