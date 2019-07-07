package mine.algo.ugly_number_super;

/**
 * Solution
 * <p>
 * <a href="https://leetcode.com/problems/super-ugly-number/">LEETCODE</a>
 */
public class SuperUglyNum {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] list = new int[n + 1];
        list[1] = 1;
        int len = primes.length;
        int[] primes_index = new int[len];
        int next;
        int[] next_primes = new int[len];
        int j;
        for (int i = 2; i <= n; i++) {
            for (j = 0; j < len; j++)
                next_primes[j] = primes[j] * (list[primes_index[j] + 1]);
            next = next_primes[0];
            for (j = 1; j < len; j++) {
                if (next > next_primes[j])
                    next = next_primes[j];
            }
            list[i] = next;
            for (j = 0; j < len; j++)
                if (next == next_primes[j])
                    primes_index[j]++;
        }
        return list[n];
    }

    public static void main(String[] args) {
        SuperUglyNum solution = new SuperUglyNum();
        int[] primes = { 2, 7, 13, 19 };
        System.out.print(solution.nthSuperUglyNumber(12, primes));
    }
}