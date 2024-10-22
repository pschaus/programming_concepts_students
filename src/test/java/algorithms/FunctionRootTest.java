package algorithms;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Grade
public class FunctionRootTest {

    private class Function1 implements FunctionRoot.Function {

        public int getMinDomain() {
            return -10;
        }

        public int getMaxDomain() {
            return 10;
        }

        public int evaluates(int x) {
            if (x < -10 && x > 10) {
                throw new IllegalArgumentException("Querying the function on a value outside its domain");
            }
            return x;
        }
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void test1() {
        FunctionRoot.Function f = new Function1();
        assertEquals(0, FunctionRoot.findRoot(f));
    }


}
