package mine.labuladong;

/**
 * TrapRain
 * <p>
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/jie-yu-shui
 */
public class TrapRain {

    public int trap(int[] height) {
        return doublePointer(height);
    }

    /**
     * O(N)=N
     * <p>
     * 双指针
     * 
     * @param height
     * @return
     */
    private int doublePointer(int[] height) {
        int lMax = height[0], rMax = height[height.length - 1];
        int left = 0, right = height.length - 1;

        int res = 0;

        while (left <= right) {
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);
            if (lMax < rMax) {
                res += (lMax - height[left]);
                ++left;
            } else {
                res += (rMax - height[right]);
                --right;
            }
        }
        return res;
    }

    /**
     * O(N)
     * <p>
     * 分两次进行；每次只找出当前点i左（右）侧的最大值；这样就可以 “在线处理” 了
     */
    private int improved(int[] height) {
        int N = height.length;
        if (N <= 2)
            return 0;
        // left
        int[] maxl = new int[N];
        int[] maxr = new int[N];
        int l = height[0], r = height[N - 1];
        // pre-process
        for (int i = 1; i != N; i++) {
            if (height[i - 1] > l) {
                l = height[i - 1];
            }
            maxl[i] = l;
        }
        for (int i = N - 2; i != -1; i--) {
            if (height[i + 1] > r) {
                r = height[i + 1];
            }
            maxr[i] = r;
        }

        int sum = 0;
        for (int i = 1; i < N - 1; i++) {
            int min = Math.min(maxl[i], maxr[i]);
            min -= height[i];
            sum += (min > 0) ? min : 0;
        }
        return sum;
    }

    /**
     * 针对每个位置i，计算可放下的水的高度
     * <p>
     * 具体方法：对每个位置，计算其左侧最高的高度和右侧最高的高度
     * <p>
     * 每次都计算，说不定可以进行某种预处理，可以直接获得某点处的“两端最大值”
     * 
     * @param height
     * @return
     */
    private int naive(int[] height) {
        int M = height.length - 1;
        if (M <= 1)
            return 0;
        int h1, h2;
        int j;
        int sum = 0;
        for (int i = 1; i != M; i++) {
            j = i - 1;
            h1 = height[i];
            h2 = height[i];
            while (j != -1) {
                if (h1 < height[j])
                    h1 = height[j];
                j--;
            }
            j = i + 1;
            while (j != M + 1) {
                if (h2 < height[j])
                    h2 = height[j];
                j++;
            }
            h1 -= height[i];
            h2 -= height[i];
            sum += (h1 < h2 ? h1 : h2);
        }
        return sum;
    }

    public static void main(String[] args) {
        // int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int[] height = { 2, 0, 2 };
        TrapRain t = new TrapRain();
        int ans = t.trap(height);
        System.out.println(ans);
    }

}