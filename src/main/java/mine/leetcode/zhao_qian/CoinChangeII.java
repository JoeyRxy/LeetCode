package mine.leetcode.zhao_qian;

import java.util.Arrays;

/**
 * CoinChangeII <div class="notranslate">
 * <p>
 * 考虑dp的方式很妙！和第一个CoinChange思考维度不同！自己先试试看看
 * <p>
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。&nbsp;
 * </p>
 * 
 * <p>
 * &nbsp;
 * </p>
 * 
 * <ul>
 * </ul>
 * 
 * <p>
 * <strong>示例 1:</strong>
 * </p>
 * 
 * <pre>
 * <strong>输入:</strong> amount = 5, coins = [1, 2, 5]
<strong>输出:</strong> 4
<strong>解释:</strong> 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 * </pre>
 * 
 * <p>
 * <strong>示例 2:</strong>
 * </p>
 * 
 * <pre>
 * <strong>输入:</strong> amount = 3, coins = [2]
<strong>输出:</strong> 0
<strong>解释:</strong> 只用面额2的硬币不能凑成总金额3。
 * </pre>
 * 
 * <p>
 * <strong>示例 3:</strong>
 * </p>
 * 
 * <pre>
 * <strong>输入:</strong> amount = 10, coins = [10] 
<strong>输出:</strong> 1
 * </pre>
 * 
 * <p>
 * &nbsp;
 * </p>
 * 
 * <p>
 * <strong>注意</strong><strong>:</strong>
 * </p>
 * 
 * <p>
 * 你可以假设：
 * </p>
 * 
 * <ul>
 * <li>0 &lt;= amount (总金额) &lt;= 5000</li>
 * <li>1 &lt;= coin (硬币面额)&nbsp;&lt;= 5000</li>
 * <li>硬币种类不超过 500 种</li>
 * <li>结果符合 32 位符号整数</li>
 * </ul>
 * </div>
 */
public class CoinChangeII {
    /**
     * 以coins为遍历对象
     * <p>
     * IMPORTANT告诉我们，<strong>给定的几个参数都可以作为考察对象</strong>
     * <p>
     * 实际上，这是一个<strong>完全背包问题</strong>：
     * 
     * dp[i][j] 前i种硬币凑出面额j的组合数
     * <p>
     * 1. 不使用第i种硬币 dp[i][j] += dp[i-1][j]
     * <p>
     * 2. 使用第i种硬币 dp[i][j] += dp[i-1][j-k*w[i]]
     * <p>
     * 下边这种是错的
     * <p>
     * dp[i,j]:只包含coins[0...j)中零钱，总金额为i，有多少种方式:
     * <p>
     * dp[i,j+1] = dp[i,j],如果i<strong>不是</strong>coins[j]的倍数;
     * <p>
     * dp[i,j+1] = 1 + dp[i,j],如果i<strong>是</strong>coins[j]的倍数
     * 
     * @param amount
     * @param coins
     * @return
     */
    public int dpSolve(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            // 这种方法大错特错！！！
            // for (int t = coin; t <= amount; t += coin)
            // dp[t]++;
            for (int i = coin; i < dp.length; i++) {
                dp[i] += dp[i - coin];// 妙啊！
            }
        }
        return dp[amount];
    }

    /**
     * FAV :
     * https://leetcode-cn.com/problems/coin-change-2/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-liweiw/
     * <p>
     * f[i,j] :利用0...i的coins，凑出j 的方法数
     * <p>
     * f[i,j] = sum{f[i-1,j-k*coins[i]]|k=0...j/coins[i]};
     * <p>
     * 其中： i:1->coins.length-1,j:1->amount
     * <p>
     * 初始时：f[0,k*coins[0]] = 0;k=0...j/coins[0]
     * <p>
     * 简化空间复杂度：消除f[i,j] -> f[j]
     * WRONG!这也是为什么完全背包数组为2维的原因！因为在i确定，更新j的过程中，每次都需要f[i-1,j-k*coins[i]]
     * 
     * @param amount
     * @param coins
     * @return
     */
    public int f(int amount, int[] coins) {
        int[][] dp = new int[2][amount + 1];
        // 1. 首行不能忘！！
        for (int t = 0; t <= amount; t += coins[0])
            dp[0][t] = 1;

        int before, now, coins_i, sum;
        for (int i = 1; i < coins.length; i++) {
            before = i - 1 & 1;
            now = i & 1;
            coins_i = coins[i];
            for (int j = 0; j <= amount; j++) {
                sum = 0;
                for (int t = 0; t <= j; t += coins_i)
                    sum += dp[before][j - t];
                dp[now][j] = sum;
            }
        }
        return dp[coins.length - 1 & 1][amount];
    }

    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0) {
            if (amount == 0) {
                return 1;
            }
            return 0;
        }

        int[][] dp = new int[2][amount + 1];
        // 这个值语义不正确，但是是一个被其它状态参考的值，这样设置是正确的
        dp[0][0] = 1;

        // 填第 1 行
        for (int i = coins[0]; i <= amount; i += coins[0]) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= amount; j++)
                dp[i & 1][j] = 0;
            for (int j = 0; j <= amount; j++)
                for (int k = 0; j - k * coins[i] >= 0; k++)
                    dp[i & 1][j] += dp[(i - 1) & 1][j - k * coins[i]];
            System.out.println(Arrays.deepToString(dp));
        }
        return dp[(len - 1) & 1][amount];
    }

    public static void main(String[] args) {
        // int amount = 500;
        // int[] coins = { 2, 7, 13 };
        int amount = 5;
        int[] coins = { 1, 2, 5 };
        int dpSolve = new CoinChangeII().f(amount, coins);
        // int dpSolve = new CoinChangeII().change(amount, coins);
        // int dpSolve = new CoinChangeII().dpSolve(amount, coins);
        System.out.println(dpSolve);
    }
}