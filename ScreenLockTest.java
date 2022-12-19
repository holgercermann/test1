import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

// TODO: Replace examples and use TDD development by writing your own tests

public class ScreenLockTest {
    ScreenLock sl = new ScreenLock();
    @Test
    public void bascis (){
        assertEquals(0, sl.calculateCombinations('A',10));
        assertEquals(0, sl.calculateCombinations('A',0));
        assertEquals(0, sl.calculateCombinations('E',14));
        assertEquals(1, sl.calculateCombinations('B',1));
        assertEquals(5, sl.calculateCombinations('C',2));
        assertEquals(8, sl.calculateCombinations('E',2));
        assertEquals(256, sl.calculateCombinations('E',4));
    }
}