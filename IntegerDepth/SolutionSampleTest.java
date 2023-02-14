package IntegerDepth;

import org.junit.Assert;
import org.junit.Test;

public class SolutionSampleTest {

    @Test
    public void sampleTest1() {
        Assert.assertEquals(10, new MySolution().computeDepth(1));
    }

    @Test
    public void sampleTest2() {
        Assert.assertEquals(9, new MySolution().computeDepth(42));
    }

    @Test
    public void sampleTest3() {
        Assert.assertEquals(12, new MySolution().computeDepth(8));
    }

}