package mine.algo.stone_game;

/**
 * 用一个数组 sum，sum[i] 指的就是 piles[i] ... piles[n-1] 的和。搞一个函数 helper 接收 start 和 end
 * 两个参数，返回从 start 开始到 end 这段的子问题，也就是在这一段 Alex 可以拿到的最大值。这一段的总数就是 sum[start] -
 * sum[end]。Alex 有两种选择，拿 start 位置的或者 end 位置的，如果拿前者，那么 Lee 可以拿到的最大值就是
 * helper(piles, start+1, end)，那么 Alex 就剩下 all - helper(piles, start+1, end)。
 * 如果拿后者，也类似。然后返回较大的那个即可。中间使用记忆化加快速度。
 */
public class Solution {

    private int[] sum;
    private int[][] hash;

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        sum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--)
            sum[i] = sum[i + 1] + piles[i];
        hash = new int[n][n];
        int max = helper(piles, 0, n - 1);
        return max > sum[0] - max;
    }

    private int helper(int[] piles, int start, int end) {
        if (start == end)
            return piles[start];
        if (hash[start][end] != 0)
            return hash[start][end];
        int all = sum[start] - sum[end + 1];// Great!
        int left = all - helper(piles, start + 1, end);
        int right = all - helper(piles, start, end - 1);
        hash[start][end] = Math.max(left, right);
        return hash[start][end];
    }
}