package mine.leetcode.zhao_qian;

/**
 * CoinChange
 * <p>
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that
 * amount. You may assume that you have infinite number of each kind of coin.
 * </p>
 * <p>
 * <code>dp[i]=min{dp[i - coins[v]] + 1 | v = 0 -> n-1}</code>
 */
public class CoinChange {
    private int[] dp;

    public int coinChange(int[] coins, int amount) {
        int LEN = amount + 1;
        int LARGE = Integer.MAX_VALUE >> 1;
        dp = new int[LEN];
        for (int i = 0; i != LEN; i++) {
            dp[i] = LARGE;
        }
        dp[0] = 0;
        int min, tmp, idx, len = coins.length;
        for (int i = 1; i != LEN; i++) {
            min = LARGE;
            for (int j = 0; j != len; j++) {
                idx = i - coins[j];
                if (idx < 0)
                    tmp = LARGE;
                else
                    tmp = dp[idx];
                if (tmp < min)
                    min = tmp;
            }
            dp[i] = min + 1;
        }
        return dp[amount] < LARGE ? dp[amount] : -1;
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        CoinChange t = new CoinChange();
        System.out.println(t.coinChange(coins, 11));

    }
}