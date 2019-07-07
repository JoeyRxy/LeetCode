package mine.basictest;

import java.util.Random;

/**
 * UsingLength
 * 
 * <p>
 * 1 : 7 ms
 * <p>
 * 2 : 3 ms
 * <p>
 * 差不了多少
 */
@SuppressWarnings("unused")
public class UsingLength {

    public static void main(String[] args) {
        Random r = new Random(38365737);
        int[] a = new int[234628];
        for (int i = 0; i != a.length; i++) {
            a[i] = r.nextInt();
        }
        int len;
        long start = System.currentTimeMillis();
        for (int i = 0; i != 999999999; i++) {
            len = a.length;
        }
        long end = System.currentTimeMillis();
        System.out.println("1 : " + (end - start));
        int tmp = a.length;
        start = System.currentTimeMillis();
        for (int i = 0; i != 999999999; i++) {
            len = tmp;
        }
        end = System.currentTimeMillis();
        System.out.println("2 : " + (end - start));
    }
}