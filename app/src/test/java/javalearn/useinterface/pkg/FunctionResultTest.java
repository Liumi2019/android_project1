package javalearn.useinterface.pkg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionResultTest {

    int printResult() {
        return 0;
    }

    @Test
    public void treadTest() {

        assertEquals(7, 2 + 3);
    }

    private class UseFunctionResult<T extends FunctionResult> {
        long getResult() {
            return 0;
        }
    }
}
