package mine;

import java.util.Random;

import org.junit.Test;

/**
 * Test
 */
public class BasicTest {
    @Test
    public void f() {
        Random random = new Random(System.currentTimeMillis());
        int i = 0;
        while (i < 10) {
            System.out.println(Math.pow(random.nextGaussian(), 3));
            i++;
        }
    }

    @Test
    public void StringFormat() {
        System.out.println(String.format("%f", Math.random()));
    }
}
