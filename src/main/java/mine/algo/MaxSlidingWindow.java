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
     * <p>
     * 方法一
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
     * <p>
     * 方法二
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
     * <p>
     * 方法三
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

    // ======================DEQUE=======================//
    ArrayDeque<Integer> deq = new ArrayDeque<>();// 存储的是数组的index而不是值

    public void clean_deque(int[] nums, int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])// 如何保证最小的都在后面？加入的时候是按照顺序加的？
            deq.removeLast();
    }

    /**
     * 方法四 使用双边队列进行优化
     * <p>
     * 难道是O(n)吗？我觉得不是啊……
     * <p>
     * 困扰之一就是：从deque中删除数据的时候，难道能够保证删除所有比当前元素小的所有元素吗？ 比如：队列里有10,2,8,那么下一个如果是6，就不能删除掉2
     * 但实际上这是不可能的事情：因为当8入列的时候，肯定会把2删除。
     * <p>
     * 和《算法导论》里介绍的循环不变式的证明的思想类似。但是如何才能想到呢？That's a question.
     * 
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] optimizeWithDeque(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0)
            return new int[0];
        if (k == 1)
            return nums;

        // init deque and output
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(nums, i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx])
                max_idx = i;
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i = k; i < n; i++) {
            clean_deque(nums, i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    // ===================END OF DEQUE===============//

    /**
     * 方法五 经典方法！！分组。有点像抽象代数里的`同余类` REAL O(n)!!
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] DP(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0)
            return new int[0];
        if (k == 1)
            return nums;

        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0)
                left[i] = nums[i]; // block_start
            else
                left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0)
                right[j] = nums[j]; // block_end
            else
                right[j] = Math.max(right[j + 1], nums[j]);
        }

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);
        return output;
    }

    public static void main(String[] args) {
        int N = 100000000;
        int[] nums = new int[N];
        Random r = new Random(System.currentTimeMillis() % 10000000);
        for (int i = 0; i < N; i++) {
            nums[i] = r.nextInt(1000000);
        }
        /*
         * 对于随机的数据，结果竟然大大出乎意料：
         * 
         * naive duration:471
         * 
         * priority queue duration:3404
         * 
         * my simple method duration:580
         * 
         * deque duration:1949
         * 
         * dp duration:1906
         * 
         * 最菜的那个方法竟然是最快的
         */

        int k = 3;
        long start, end;
        MaxSlidingWindow t = new MaxSlidingWindow();

        start = System.currentTimeMillis();
        int[] ans = t.naive(nums, k);
        end = System.currentTimeMillis();

        // for (int x : ans) {
        // System.out.print(x + "\t");
        // }
        System.out.println("\nnaive duration:" + (end - start));

        // ==============
        start = System.currentTimeMillis();
        int[] ans2 = t.optimize2(nums, k);
        end = System.currentTimeMillis();

        // for (int x : ans2) {
        // System.out.print(x + "\t");
        // }
        System.out.println("\npriority queue duration:" + (end - start));

        // ===============
        start = System.currentTimeMillis();
        int[] ans3 = t.simpleOptimize(nums, k);
        end = System.currentTimeMillis();

        // for (int x : ans3) {
        // System.out.print(x + "\t");
        // }
        System.out.println("\nmy simple method duration:" + (end - start));

        // ===============TEST DEQUE
        start = System.currentTimeMillis();
        int[] ans4 = t.optimizeWithDeque(nums, k);
        end = System.currentTimeMillis();

        // for (int x : ans4) {
        // System.out.print(x + "\t");
        // }
        System.out.println("\ndeque duration:" + (end - start));

        // ========Test DP
        start = System.currentTimeMillis();
        int[] ans5 = t.optimizeWithDeque(nums, k);
        end = System.currentTimeMillis();

        // for (int x : ans5) {
        // System.out.print(x + "\t");
        // }
        System.out.println("\ndp duration:" + (end - start));
    }
}