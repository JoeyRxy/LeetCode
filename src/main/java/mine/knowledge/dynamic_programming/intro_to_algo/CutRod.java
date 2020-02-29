package mine.knowledge.dynamic_programming.intro_to_algo;

import java.util.LinkedList;
import java.util.List;

/**
 * CutRod
 * <p>
 * 长度为n的钢条，给出一个各种长度钢条的价目表prices[]，求一个切割钢条的最优方案，使得赚的钱最多
 */
public class CutRod {
    private int[] lens;
    private double[] prices;

    public CutRod(int[] lens, double[] prices) {
        if (lens.length != prices.length)
            throw new IllegalArgumentException("不合法的数据");
        this.lens = lens;
        this.prices = prices;
    }

    private int[] recurAns;

    public double recursive(int n) {
        recurAns = new int[n + 1];
        return dfs(n);
    }

    private double dfs(int n) {
        if (n == 0)
            return 0;
        double max = Double.NEGATIVE_INFINITY;
        double res;
        int idx = 0;
        for (int i = 0; i < lens.length && n >= lens[i]; i++) {
            res = dfs(n - lens[i]) + prices[i];
            if (max < res) {
                max = res;
                idx = i;
            }
        }
        recurAns[n] = idx;
        return max;
    }

    public List<Integer> recurSolution(int n) {
        LinkedList<Integer> ans = new LinkedList<>();
        int _t;
        while (n > 0) {
            _t = lens[recurAns[n]];
            ans.add(_t);
            n -= _t;
        }
        return ans;
    }

    private double[] memo;
    private int[] memoAns;

    public double memo(int n) {
        memo = new double[Math.max(n, lens[lens.length - 1]) + 1];
        memoAns = new int[memo.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        return memoRecur(n);
    }

    private double memoRecur(int r) {
        if (r < 0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (memo[r] >= 0) {
            return memo[r];
        }
        double max = Double.NEGATIVE_INFINITY;
        if (r == 0)
            return 0;
        double res;
        int idx = 0;
        for (int i = 0; i < lens.length; i++) {
            res = memoRecur(r - lens[i]) + prices[i];
            if (max < res) {
                max = res;
                idx = i;
            }
        }
        memoAns[r] = idx;
        memo[r] = max;
        return max;
    }

    public List<Integer> memoSolution(int n) {
        LinkedList<Integer> ans = new LinkedList<>();
        int _t;
        while (n > 0) {
            _t = lens[memoAns[n]];
            ans.add(_t);
            n -= _t;
        }
        return ans;
    }

    private double[] dp;
    private int[] dpAns;

    /**
     * dp[r] = max{dp[r-lens[k]]+prices[k] | k in {0,1,...,lens.length-1}} dpAns[r]
     * =
     */
    public double dynamicProgramming(int n) {
        dp = new double[n + 1];
        dpAns = new int[n + 1];
        double max, res;
        int idx = -1;
        for (int r = 1; r < dp.length; r++) {
            max = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < lens.length && lens[i] <= r; i++) {
                res = dp[r - lens[i]] + prices[i];
                if (max < res) {
                    max = res;
                    idx = i;
                }
            }
            dpAns[r] = idx;
            dp[r] = max;
        }
        return dp[n];
    }

    public List<Integer> dpSolution(int n) {
        LinkedList<Integer> ans = new LinkedList<>();
        int _t;
        while (n > 0) {
            _t = lens[dpAns[n]];
            ans.add(_t);
            n -= _t;
        }
        return ans;
    }

}
