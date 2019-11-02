package mine.labuladong;

/**
 * TrapRain
 * <p>
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrapRain {

    public int trap(int[] heights) {

        return naive(heights);
    }

    /**
     * 针对每个位置i，计算可放下的水的高度
     * 
     * @param heights
     * @return
     */
    private int naive(int[] heights) {
        int len = heights.length;
        if (len <= 2)
            return 0;
        int h1, h2, hi;
        int x, j;
        int sum = 0;
        for (int i = 1; i != len - 1; i++) {
            x = heights[i];
            h1 = 0;
            h2 = 0;
            hi = 0;
            j = i - 1;
            while (heights[j] > x) {
                if (j == 0)
                    break;
                j--;
            }
            h1 = heights[j] - x;
            h1 = h1 > 0 ? h1 : 0;
            j = i + 1;
            while (heights[j] > x) {
                if (j == len - 1)
                    break;
                j++;
            }
            h2 = heights[j] - x;
            h2 = h2 > 0 ? h2 : 0;
            hi = h1 > h2 ? h1 : h2;
            sum += hi;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] heights = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        TrapRain t = new TrapRain();
        int ans = t.trap(heights);
        System.out.println(ans);
    }

}