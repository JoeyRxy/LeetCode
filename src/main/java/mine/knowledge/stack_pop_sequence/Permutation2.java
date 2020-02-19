package mine.knowledge.stack_pop_sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Permutation2
 */
public class Permutation2 {

    private List<int[]> res = new ArrayList<>();
    private int[] tmp;
    private boolean[] marked;
    private int[] nums;

    public List<int[]> permute(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        marked = new boolean[nums.length];
        dfs(0);
        return res;
    }

    private void dfs(int depth) {
        if (depth == nums.length) {
            res.add(Arrays.copyOf(tmp, tmp.length));
            return;
        }
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                tmp[depth] = nums[i];// 覆写第depth个元素，如果用ArrayList.add(e)还需之后删掉第depth的元素
                dfs(depth + 1);
                marked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 4, 2, 8, 5, 7 };
        Permutation2 t = new Permutation2();
        List<int[]> permutate = t.permute(nums);
        for (int[] a : permutate)
            System.out.println(Arrays.toString(a));

        System.out.println("=== size : " + permutate.size() + " ===");
    }
}