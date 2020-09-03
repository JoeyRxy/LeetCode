package mine.leetcode.zhao_qian;

import java.util.Arrays;

/**
 * 描述：
 * <p>
 * value数组给出了几种硬币的面值；S为最终需要凑的总数；硬币数量无限；
 * <p>
 * 给出最少的硬币数
 */
@SuppressWarnings("unused")
public class InfCoinsEqualCapacity {

    public InfCoinsEqualCapacity(int S, int[] value) {
        Arrays.sort(value);
        solve(S, value);
    }

    public InfCoinsEqualCapacity() {
    }

    private int[] dp;//
    private int[] value;

    public int solve(int S, int[] value) {
        this.value = value;
        dp = new int[S];// dp[s]表示得到s所需的最少硬币数
        for (int i = 0; i != dp.length; i++)
            dp[i] = Integer.MAX_VALUE >> 1;

        return dp[S];
    }

    // 如何表示没法找零呢？
    private int dfs(int S) {
        if (S == 0)
            return 0;
        if (S < value[0])
            return Integer.MAX_VALUE >> 1;
        int min = Integer.MAX_VALUE >> 1, tmp;
        for (int i = 0; i != value.length; i++) {
            tmp = dfs(S - value[i]) + 1;
            if (tmp < min)
                min = tmp;
        }
        return min;
    }

    // top-down memorize
    private int memodfs(int s) {
        if (dp[s] >= 0)
            return dp[s];
        int ans = Integer.MAX_VALUE >> 1;
        int tmp;
        if (s == 0)
            ans = 0;
        else {
            for (int i = 0; i != value.length; i++) {
                tmp = dfs(s - value[i]) + 1;
                if (tmp < ans)
                    ans = tmp;
            }
        }
        dp[s] = ans;
        return ans;
    }

    int[] coinLast;
    private int amount;

    // down-top
    public int dpSolve(int amount, int[] coins) {
        Arrays.sort(coins);
        this.amount = amount;
        coinLast = new int[amount + 1];
        dp = new int[amount + 1];
        int LARGE = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = (LARGE);
        }
        dp[0] = 0;
        coinLast[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int min = LARGE;
            int idx = -1;
            for (int j = 0; j < coins.length && coins[j] <= i; j++) {
                if (min > 1 + dp[i - coins[j]]) {
                    min = 1 + dp[i - coins[j]];
                    idx = j;
                }
            }
            dp[i] = min;
            if (idx != -1)
                coinLast[i] = coins[idx];
        }
        return dp[amount] >= LARGE ? -1 : dp[amount];
    }

    public int[] coinList() {
        if (dp[amount] < (Integer.MAX_VALUE >> 1)) {
            int[] res = new int[dp[amount]];
            int j = amount;
            int i = 0;
            while (j > 0) {
                res[i++] = coinLast[j];
                j -= coinLast[j];
            }
            return res;
        } else
            return null;
    }

    public static void main(String[] args) {
        InfCoinsEqualCapacity t = new InfCoinsEqualCapacity();
        int[] sets = new int[] { 7, 29, 31, 991 };
        for (int i = 100; i < 1100; i++) {
            System.out.println(i + " : " + t.dpSolve(i, sets) + " --- " + Arrays.toString(t.coinList()));
        }
    }
}