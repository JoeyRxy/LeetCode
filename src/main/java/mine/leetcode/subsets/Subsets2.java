package mine.leetcode.subsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Subsets2
 */
public class Subsets2 {

    private int[] nums;
    private int len;
    private LinkedList<Integer> tmpList;
    private List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        len = nums.length;
        res = new ArrayList<>(2 << (len - 1));
        tmpList = new LinkedList<>();
        dfs(0);
        return res;
    }

    private void dfs(int depth) {
        if (depth == len) {
            res.add(new ArrayList<>(tmpList));
            return;
        }
        tmpList.push(nums[depth]);
        dfs(depth + 1);
        tmpList.pop();// 通过pop()方式 回归到子问题的初始位置
        dfs(depth + 1);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> subsets = new Subsets2().subsets(nums);
        for (List<Integer> list : subsets) {
            System.out.println(list);
        }
    }
}