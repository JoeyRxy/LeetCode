package mine.algo.gen_largest_num;

import java.util.Arrays;

//ATTENTION 这个问题不仅仅是字符串排序那么简单！关键在于如何确定两个字符串的大小：comparator方法的设计！
/**
 * GenLargestNum
 * <p>
 * <a href="https://leetcode.com/problems/largest-number/">leetcode</a>
 */
public class LargestNum {

    public String largestNumber(int[] nums) {
        int length = nums.length;
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs);
        StringBuffer buffer = new StringBuffer();
        for (int i = length - 1; i >= 0; i--) {
            buffer.append(strs[i]);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        int[] a = { 3, 30, 34, 5, 9 };
        System.out.println(new LargestNum().largestNumber(a));
    }
}