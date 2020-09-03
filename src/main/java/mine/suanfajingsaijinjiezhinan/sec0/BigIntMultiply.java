package mine.suanfajingsaijinjiezhinan.sec0;

import java.util.Scanner;

public class BigIntMultiply {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long p = scanner.nextLong();
        scanner.close();
        long res = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res += a;
                res %= p;
            }
            b >>= 1;
            a <<= 1;
            a %= p;
        }
        System.out.println(res);
    }
}