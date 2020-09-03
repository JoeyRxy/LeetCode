package mine.knowledge.stack_pop_sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PermutationUnique {
    private List<List<Integer>> res = new ArrayList<>();
    // private LinkedList<Integer> tmp;
    private int[] tmp;
    private boolean[] marked;
    private int[] nums;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        // tmp = new LinkedList<>();
        tmp = new int[nums.length];
        marked = new boolean[nums.length];
        dfs(0);
        return res;
    }

    private void dfs(int depth) {
        if (depth == nums.length) {
            // res.add(new LinkedList<>(tmp));
            ArrayList<Integer> _t = new ArrayList<Integer>(nums.length);
            for (int i = 0; i < tmp.length; i++) {
                _t.add(tmp[i]);
            }
            res.add(_t);
            return;
        }
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i] && (i == 0 || nums[i] != nums[i - 1] || marked[i - 1])) {
                marked[i] = true;
                tmp[depth] = nums[i];
                dfs(depth + 1);
                // tmp.pop();
                marked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new PermutationUnique().permuteUnique(new int[] { 1, 1, 2 });
        for (List<Integer> list : res) {
            System.out.println(list);
        }
        System.out.println("======== size : " + res.size() + " =============");
    }
}