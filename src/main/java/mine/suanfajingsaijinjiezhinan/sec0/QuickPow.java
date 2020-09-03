package mine.suanfajingsaijinjiezhinan.sec0;

import java.util.Scanner;

public class QuickPow {
    public static long quickPow(long a, long b, long p) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
                res %= p;
            }
            b >>= 1;
            a *= a;
            a %= p;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long p = scanner.nextLong();
        scanner.close();
        if (a == 0) {
            if (b == 0)
                System.out.println(1);
            else
                System.out.println(0);
            return;
        }
        if (p == 1) {
            System.out.println(0);
            return;
        }
        System.out.println(quickPow(a, b, p));
    }
}