package mine.leetcode;

/**
 * Massage
 * <p>
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * </p>
 */
public class Massage {
    /**
     * dp[0][i] = max{dp[1][i-1], dp[0][i-1]}; dp[1][i] = dp[0][i-1] + nums[i]
     * 
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[2][len];
        dp[1][0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[0][i - 1]);
            dp[1][i] = dp[0][i - 1] + nums[i];
        }
        return Math.max(dp[0][len - 1], dp[1][len - 1]);
    }

    public static void main(String[] args) {
        int[] nums = { 2, 1, 4, 5, 3, 1, 1, 3 };
        int ans = new Massage().massage(nums);
        System.out.println(ans);
    }
}