package mine.algo;

import java.util.*;

/**
 * MaxSlidingWindow
 */
public class MaxSlidingWindow {

    public MaxSlidingWindow() {
    }

    /**
     * O(nk)
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] naive(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return nums;
        int max, end = n - k + 1;
        int endj;
        int[] ans = new int[end];
        for (int i = 0; i != end; i++) {
            max = nums[i];
            endj = i + k;
            for (int j = i + 1; j != endj; j++)
                if (max < nums[j])
                    max = nums[j];
            ans[i] = max;
        }
        return ans;
    }

    /**
     * using {@link PriorityQueue}
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] optimize2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return nums;

        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;// 最大堆
            }
        };
        int end = n - k + 1;
        int[] ans = new int[end];

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, comparator);
        int i, idx = 0;
        for (i = 0; i < k; i++) {
            pq.add(nums[i]);
        }

        for (i = k; i < n; i++) {
            ans[idx++] = pq.peek();
            pq.remove(nums[i - k]);// O(n)
            pq.add(nums[i]);
        }

        ans[idx] = pq.poll();

        return ans;
    }

    /**
     * simple but is O(n)(不完全是O(n)，极端情况下可能是O(nk)); record the index of max elem
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] simpleOptimize(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return nums;
        int idxOfMax = 0;
        int max = nums[0];
        // init
        for (int i = 1; i < k; i++) {
            if (max < nums[i]) {
                max = nums[i];
                idxOfMax = i;
            }
        }

        int[] ans = new int[n + 1 - k];
        ans[0] = max;
        int l = 1;// index of ans[]
        // begin
        for (int i = k; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idxOfMax = i;
            } else if (idxOfMax == i - k) {
                // 重新计算区间最大值
                // 虽然重新计算需要O(k)，但是一般来说这种情况比较少，应该不会太浪费；事实证明也确实如此
                // 见：https://leetcode.com/submissions/detail/252165544/
                idxOfMax++;
                max = nums[idxOfMax];
                for (int j = idxOfMax + 1; j <= i; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                        idxOfMax = j;
                    }
                }
            }
            // idxOfMax,max更新完毕
            ans[l++] = max;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        long start, end;
        MaxSlidingWindow t = new MaxSlidingWindow();
        start = System.currentTimeMillis();
        int[] ans = t.naive(nums, k);
        end = System.currentTimeMillis();

        for (int x : ans) {
            System.out.print(x + "\t");
        }
        System.out.println("\nduration:" + (end - start));

        // ==============
        start = System.currentTimeMillis();
        int[] ans2 = t.optimize2(nums, k);
        end = System.currentTimeMillis();

        for (int x : ans2) {
            System.out.print(x + "\t");
        }
        System.out.println("\nduration:" + (end - start));

        // ===============
        start = System.currentTimeMillis();
        int[] ans3 = t.simpleOptimize(nums, k);
        end = System.currentTimeMillis();

        for (int x : ans3) {
            System.out.print(x + "\t");
        }
        System.out.println("\nduration:" + (end - start));
    }
}