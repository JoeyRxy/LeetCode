package mine.knowledge.dynamic_programming;

import java.math.BigInteger;

/**
 * Hanoi
 * <p>
 * 可以用DP？
 * <p>
 * DP可以用来计算挪的总数，但是打印出来仍然需要2^n（是这样吗？）
 */
@SuppressWarnings("unused")
public class Hanoi {
    private BigInteger count;

    private BigInteger[] dp;

    public void recurseWithMemo(int N) {
        dp = new BigInteger[N + 1];
        for (int i = 0; i != N + 1; i++) {
            dp[i] = new BigInteger("0");
        }
        dp[1] = new BigInteger("1");
        count = BigInteger.ZERO;
        recurseWithMemo(N, 'A', 'B', 'C');
        count = dp[N];
    }

    public void recurseWithMemo(int N, char a, char b, char c) {
        if (!dp[N].equals(BigInteger.ZERO)) {
            count = count.add(dp[N]);
        } else {
            recurseWithMemo(N - 1, a, c, b);
            count = count.add(BigInteger.ONE);
            recurseWithMemo(N - 1, b, a, c);
        }
        dp[N] = count;
    }

    private void print(int a, int b) {
        System.out.println(Character.toString(a) + " --> " + Character.toString(b));
        count = count.add(BigInteger.ONE);
    }

    public BigInteger count() {
        return count;
    }

    public static void main(String[] args) {
        // int[] N = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90,
        // 100, 200 };
        Hanoi t = new Hanoi();
        // for (int i = 0; i != N.length; i++) {
        // System.out.println("===========N : " + N[i] + "=========");
        // t.recurseWithMemo(N[i]);
        // System.out.println(t.count() + "\n===========================");
        // }
        t.recurseWithMemo(8630);
        System.out.println("TOTAL MOVEMENT : \n" + t.count() + "\n========================");
    }
}