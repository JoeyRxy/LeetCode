package mine.basictest;

import java.math.BigInteger;

/**
 * LargeNumTest
 */
public class LargeNumTest {

    public static void main(String[] args) {
        BigInteger x = new BigInteger("1");
        System.out.println(x.toString());
        BigInteger y = new BigInteger("1");
        x = x.add(y);
        System.out.println(x.toString());
    }
}