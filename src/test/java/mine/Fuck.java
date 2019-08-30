package mine;

import java.util.Random;

import org.junit.Test;

/**
 * Test
 */
public class Fuck {
    @Test
    public void f(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        int i = 0;
        while (i < 10) {
            System.out.println(Math.pow(random.nextGaussian(), 3));
            i++;
        }
    }
}
