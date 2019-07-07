package mine.algo.pattern123;

import java.util.Arrays;
import java.util.Stack;

/**
 * Find132Pattern
 * <p>
 * <a href="https://leetcode.com/articles/132-pattern/">Leetcode Article</a>
 * <p>
 * there is something I have understand yet:{@value Approach 4 & 6}
 */
@SuppressWarnings("unused")
public class FindPattern123 {

    public boolean find132pattern(int[] nums) {
        // return Brute(nums);
        // return Improve(nums);
        return usingBinarySearch(nums);
    }

    /**
     * With Stack.Don't understand temporarily
     */
    private boolean usingStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] min = new int[nums.length];
        int minTmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minTmp)
                minTmp = nums[i];
            min[i] = minTmp;
        }
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j])
                    stack.pop();
                if (!stack.isEmpty() && stack.peek() < nums[j])
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }

    // ATTENTION : Using search
    /**
     * Genius!
     */
    private boolean usingBinarySearch(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return false;
        int[] min = new int[len];
        min[0] = nums[0];
        for (int i = 1; i < len; i++)
            min[i] = Math.min(min[i - 1], nums[i]);
        for (int j = len - 1, k = len; j >= 0; j--) {
            if (nums[j] > min[j]) {
                k = Arrays.binarySearch(nums, k, len, min[j] + 1);// binarySearch(int[]a,int fromIndex,int toIndex,int
                                                                  // key);
                // find elem which is just bigger than min of array[0...j];
                // how to find?the method <code>binaySearch</code> will return the key's index
                // or the (-1 - <code>insertionPoint</code>);so use min[j]+1 will give the index
                // of key or the insertion point
                if (k < 0)
                    k = -1 - k;// compute insertion point
                if (k < len && nums[k] < nums[j])
                    return true;
                nums[--k] = nums[j];// sort the array[j...len) simultaneously
            }
        }
        return false;
    }

    /**
     * <p>
     * Accepted!Can't find all of 132 patterns,but tell whether there is or not.
     * </p>
     * <p>
     * time complexity:{@code O(n^2)}
     * </p>
     * <p>
     * space complextity:{@code O(1)}
     * </p>
     */
    private boolean Improve(int[] nums) {
        int len = nums.length;
        boolean hasGreat = false;
        int tmp, nums_j;
        for (int i = 0; i < len; i++) {
            tmp = nums[i];
            hasGreat = false;// use a flag to record whether there is a nearest nums[j] greater than cur
            for (int j = i - 1; j >= 0; j--) {// scan nums[0...i-1] from i-1 to 0
                nums_j = nums[j];
                if (hasGreat) {// if there is already a greater num,we just to find a num less than cur
                    if (nums_j < tmp) {
                        return true;
                    }
                } else if (nums_j > tmp) {// if there hasn't been a bigger num
                    hasGreat = true;
                }
            }
        }
        return false;
    }

    /**
     * @deprecated too slow
     *             <p>
     *             Accepted!Can't find all of 132 patterns,but tell whether there is
     *             or not.
     *             <p>
     *             time complexity:O(n^3)
     *             <p>
     *             space complextity:O(1)
     */
    private boolean Brute(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                if (nums[i] < nums[j])
                    for (int k = j + 1; k < len; k++) {
                        if (nums[i] < nums[k] && nums[k] < nums[j])
                            return true;
                    }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int nums[] = { 6, 12, 3, 4, 5, 11, 20, 1, 2 };
        FindPattern123 t = new FindPattern123();
        boolean ans = t.find132pattern(nums);
        System.out.print(ans);
    }
}