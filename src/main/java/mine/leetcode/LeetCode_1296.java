package mine.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * LeetCode_1296
 * <p>
 * Divide Array in Sets of K Consecutive Numbers
 * <p>
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into sets of k consecutive numbers Return True
 * if its possible otherwise return False.
 * <p>
 * {@link https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/}
 */
public class LeetCode_1296 {

    // ========= first========//
    public boolean first(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0)
            return false;
        Arrays.sort(nums);
        boolean[] marked = new boolean[n];
        int[] idx = new int[k];// k个标
        while (idx[k - 1] != n - 1) {
            int m = 0;
            while (marked[m])// 从头检查很费时
                m++;
            idx[0] = m;
            int j, numsi;
            int l = 0;
            while (l != k) {
                j = idx[l];
                marked[j] = true;
                if (l == k - 1)
                    break;
                numsi = nums[idx[l]];
                while (j != n && nums[j] == numsi)
                    j++;
                while (j != n && marked[j])
                    j++;
                if (j == n)
                    break;
                if (nums[j] == numsi + 1)
                    idx[++l] = j;
                else
                    return false;

            }
        }
        return true;
    }

    // ============== greedy =======//
    public boolean greedy(int[] nums, int k) {
        /** <nums, freq> */
        int n = nums.length;
        if (n % k != 0)
            return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            Integer n_ = map.get(key);
            map.put(key, (n_ == null ? 1 : n_ + 1));
        }

        while (map.size() != 0) {
            Entry<Integer, Integer> firstEntry = map.firstEntry();// 找到最小元素
            Integer key = firstEntry.getKey();
            Integer value = firstEntry.getValue();
            for (int i = 0; i < k; i++) {
                Integer l = map.get(key + i);// 在最小元素基础上寻找连续的k个元素
                if (l == null)
                    return false;

                map.put(key + i, l - value);
                if (l <= value)
                    map.remove(key + i);
            }
        }

        return true;
    }

    // =============== third improved ===========//
    public boolean improved(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();// 用于保存最小元素

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            Integer n_ = map.get(key);
            map.put(key, (n_ == null ? 1 : n_ + 1));
        }

        // for (int i = 0; i < nums.length; i++)
        // if (!map.containsKey(nums[i] - 1))
        // q.add(nums[i]);
        Set<Integer> keySet = map.keySet();
        for (int key : keySet) {
            if (!map.containsKey(key - 1))
                q.add(key);
        }

        int t;
        int _key;
        while (map.size() != 0) {
            Integer key = q.poll();
            Integer val = map.get(key);
            for (int i = k - 1; i != -1; i--) {
                _key = key + i;
                Integer l = map.get(_key);
                if (l == null)
                    return false;
                t = l - val;
                map.put(_key, t);
                if (t == 0) {
                    Integer _x = map.get(_key + 1);
                    if (_x != null && _x != 0)
                        q.add(_key + 1);
                    map.remove(_key);
                }
                if (t < 0)
                    return false;
            }
        }
        return true;
    }

    // ============== fourth : 大佬 ===========//
    static boolean reduce(int[] nums, int ix, int k) {
        int v = nums[ix];
        if (v == 0)
            return true;
        for (; ix < nums.length; ix++) {
            int v1 = nums[ix];
            if (v == v1) {
                nums[ix] = 0;
                if (--k == 0)
                    return true;
                v++;
            } else if (v1 > v) {
                return false;
            }
        }
        return false;
    }

    public static boolean niubi(int[] nums, int k) {
        if (nums.length % k != 0)
            return false;
        if (k == 1)
            return true;

        int min, max;
        min = max = nums[0];
        int sum = 0;
        for (int v : nums) {
            if (v < min)
                min = v;
            if (v > max)
                max = v;
            sum = (sum + v * 2) % k;
        }
        if (sum != 0)
            return false;
        if (nums.length * 8 < max - min) {
            // nums = nums.clone();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (!reduce(nums, i, k))
                    return false;
            }
        } else {
            int[] cs = new int[max - min + 1];
            for (int v : nums)
                cs[v - min]++;
            for (int i = 0; i < cs.length; i++) {
                int c = cs[i];
                if (c != 0) {
                    for (int j = 0; j < k; j++) {
                        if ((cs[i + j] -= c) < 0)
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        return improved(nums, k);
    }

    public static void main(String[] args) {
        int[] nums = { 12, 12, 2, 11, 22, 20, 11, 13, 3, 21, 1, 13 };
        LeetCode_1296 t = new LeetCode_1296();
        boolean ans = t.isPossibleDivide(nums, 3);
        System.out.println(ans);
    }
}
