package mine.leetcode.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// import edu.princeton.cs.algs4.Out;

/**
 * ThreeSum
 * <p>
 * 答案（三元组）无重复……怎么做到？
 */
@SuppressWarnings("unused")
public class ThreeSum {
    // private int[] a;
    // private List<List<Integer>> ans = new ArrayList<>();
    // private int len;

    // public List<List<Integer>> threeSum(int[] nums) {
    // if (nums.length < 3)
    // return ans;
    // this.a = nums;
    // len = nums.length;
    // binSearch();
    // return validate();
    // }

    /**
     * SortAns
     */
    public class SortAns implements Comparator<List<Integer>> {

        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            if (o1.get(0) > o2.get(0))
                return 1;
            else if (o1.get(0) == o2.get(0)) {
                if (o1.get(1) > o2.get(1))
                    return 1;
                else if (o1.get(1) == o2.get(1))
                    return 0;
                else
                    return -1;
            } else
                return -1;
        }
    }

    public List<List<Integer>> validate(List<List<Integer>> numsList) {
        // Collections.sort(ans, new SortAns()); // "message": "The method sort(T[],
        // Comparator<? super T>) in thetype
        // Arrays is
        // not applicable for the arguments (List<List<Integer>>,ThreeSum.SortAns)"
        // ATTENTION : Use "Collections.sort" instead!
        if (numsList.size() == 0)
            return numsList;
        numsList.sort(new SortAns());
        int size = numsList.size();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = numsList.get(0), cur;
        res.add(tmp);
        for (int i = 1; i != size; i++) {
            cur = numsList.get(i);
            if (!cur.equals(tmp)) {
                tmp = cur;
                res.add(cur);
            }
        }
        return res;
    }

    // ============== NO.1:brute ==============//
    // private void brute() {
    // List<Integer> tmp;
    // for (int i = 0; i != len - 2; i++) {
    // for (int j = i + 1; j != len - 1; j++) {
    // for (int k = j + 1; k != len; k++) {
    // if (a[i] + a[j] + a[k] == 0) {
    // tmp = new ArrayList<>();
    // tmp.add(a[i]);
    // tmp.add(a[j]);
    // tmp.add(a[k]);
    // ans.add(tmp);
    // }
    // }
    // }
    // }
    // }

    // ============== NO.2:binary search ==============//
    private List<List<Integer>> binSearch(int[] a) {
        Arrays.sort(a);
        int tmp;
        int len = a.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i != len - 2; i++) {
            if (a[i] > 0)
                break;
            for (int j = i + 1; j != len - 1; j++) {
                tmp = a[i] + a[j];
                if (tmp > 0)
                    break;
                // if (TMP.size() != 0) {
                // if (a[i] == TMP.get(0) && a[j] == TMP.get(1))
                // continue;
                // }
                if (Arrays.binarySearch(a, j + 1, len, -tmp) > 0) {
                    ans.add(Arrays.asList(a[i], a[j], -tmp));
                }
            }
        }
        return validate(ans);
    }

    // TODO ： 还有O(n^2)的方法？？
    // public List<List<Integer>> theAnswer(int[] nums) {
    // Arrays.sort(nums);
    // List<List<Integer>> res = new LinkedList<>();
    // for (int i = 0; i < nums.length - 2; i++) {
    // if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
    // int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
    // while (lo < hi) {
    // if (nums[lo] + nums[hi] == sum) {
    // res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
    // while (lo < hi && nums[lo] == nums[lo + 1])
    // lo++;
    // while (lo < hi && nums[hi] == nums[hi - 1])
    // hi--;
    // lo++;
    // hi--;
    // } else if (nums[lo] + nums[hi] < sum)
    // lo++;
    // else
    // hi--;
    // }
    // }
    // }
    // return res;
    // }

    // public static int sum(List<Integer> a) {
    // int sum = 0;
    // for (int x : a) {
    // sum += x;
    // }
    // return sum;
    // }

}