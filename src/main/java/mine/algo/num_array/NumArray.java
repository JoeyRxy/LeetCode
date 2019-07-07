package mine.algo.num_array;

/**
 * NumArray
 */
@SuppressWarnings("unused")
public class NumArray {

    private int[][] dp;
    private int N;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        if (nums.length == 0)
            return;
    }

    // ============== NO.1:naive ==============//
    public int naiveSumRange(int i, int j) {
        int sum = 0;
        for (int l = i; l <= j; l++) {
            sum += nums[l];
        }
        return sum;
    }

    public void naiveUpdate(int i, int val) {
        nums[i] = val;
    }
    // Time Limit Exceeded

    // ============== NO.2:DP ==============//
    // Memory Limit Exceeded : Space : O(n^2)
    private void DP1() {
        N = nums.length;
        dp = new int[N][N];
        for (int i = 0; i != N; i++)
            dp[i][i] = nums[i];
        for (int i = 0; i != N; i++)
            for (int j = i + 1; j != N; j++)
                dp[i][j] = dp[i][j - 1] + nums[j];
    }

    // Time : O(n^2) ? 也很慢啊
    public void dp1Update(int i, int val) {
        int inc = val - dp[i][i];
        for (int k = i; k != N; k++)
            for (int l = 0; l != i + 1; l++)
                dp[l][k] += inc;
    }

    // Time : O(1)
    public int dp1SumRange(int i, int j) {
        return dp[i][j];
    }

    // ============== NO.3:Sqrt Decompositin ==============//
    // Preprocessing : O(n)
    // SumQuery : O(sqrt(n))
    // Update : O(1)
    // Space : O(n)

    public static void main(String[] args) {
        int[] nums = {};
        NumArray obj = new NumArray(nums);
    }
}