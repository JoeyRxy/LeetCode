package mine.algo.zhao_qian;

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

    // down-top

    public static void main(String[] args) {
    }
}