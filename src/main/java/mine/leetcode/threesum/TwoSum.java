package mine.leetcode.threesum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * TwoSum
 */
public class TwoSum {

    /**
     * Wrong！
     */
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int _t = target - nums[i];
            int j = Arrays.binarySearch(nums, _t);
            if (j >= 0) {
                if (j == i) {
                    if (j > 0 && nums[j - 1] == _t)
                        return new int[] { i, j - 1 };
                    if (j < nums.length - 1 && nums[j + 1] == _t)
                        return new int[] { i, j + 1 };
                } else {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }

    public int[] method2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(nums[i]);
            if (idx != null) {
                return new int[] { idx, i };
            }
            map.put(target - nums[i], i);
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 4 };
        int target = 6;
        int[] twoSum = new TwoSum().method2(nums, target);
        System.out.println(twoSum[0] + ", " + twoSum[1]);
    }
}