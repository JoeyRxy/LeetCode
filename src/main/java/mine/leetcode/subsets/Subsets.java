package mine.leetcode.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets
 */
public class Subsets {
    private boolean[] marked;
    private int[] nums;
    private List<List<Integer>> res;
    private boolean[] choice;
    private int len;

    public List<List<Integer>> subsets(int[] nums) {
        len = nums.length;
        res = new ArrayList<>(2 << (len - 1));
        marked = new boolean[len];
        choice = new boolean[len];
        this.nums = nums;
        dfs(0, true);
        dfs(0, false);
        return res;
    }

    private void dfs(int depth, boolean val) {
        marked[depth] = true;
        choice[depth] = val;
        if (depth + 1 == len) {
            res.add(gen());
        } else if (!marked[depth + 1]) {
            dfs(depth + 1, true);
            dfs(depth + 1, false);
        }
        marked[depth] = false;
    }

    private List<Integer> gen() {
        List<Integer> _t = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (choice[i]) {
                _t.add(nums[i]);
            }
        }
        return _t;
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        List<List<Integer>> sets = subsets.subsets(new int[] { 1, 2, 3 });
        for (List<Integer> list : sets) {
            System.out.println(list);
        }
    }
}