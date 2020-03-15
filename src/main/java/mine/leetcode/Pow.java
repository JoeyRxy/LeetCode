package mine.leetcode;

/**
 * Pow
 */
public class Pow {
    public double myPow(double x, int n) {
        if (x == 1)
            return 1;
        if (x == -1) {
            if (n % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        if (n > 0) {
            double t = x;
            while (n > 1) {
                x *= t;
                n--;
            }
            return x;
        } else if (n == 0) {
            return 1;
        } else {
            double t = x;
            while (n < -1) {
                x *= t;
                n++;
            }
            return 1 / x;
        }
    }

    public static void main(String[] args) {
        double x = 2.00000;
        int n = -2147483648;
        double res = new Pow().myPow(x, n);
        System.out.println(res);
    }
}