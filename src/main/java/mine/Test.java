package mine;

import java.util.Random;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        while (true) {
            System.out.println(Math.pow(random.nextGaussian(), 3));
        }
    }
}
