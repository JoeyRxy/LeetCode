package mine.knowledge.seg_tree_binary_indexed_tree;

/**
 * QuickSumSlowUpdate
 * <p>
 * <li>{@code update}: O(n)
 * <li>{@code prefixSum} & {@code rangeSum} : O(1)
 * <p>
 * 适合经常性查询，不适合经常更新
 */
public class QuickSumSlowUpdate {

    private int[] prefixSum;
    private int[] nums;

    public QuickSumSlowUpdate(int[] nums) throws IllegalArgumentException {
        if (nums.length == 0)
            throw new IllegalArgumentException("Illegal Array Length : Length is 0");
        this.nums = nums;
        prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i != nums.length; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];
    }

    public void update(int i, int val) {
        int diff = (val - nums[i]);
        for (int j = i; j != prefixSum.length; j++) {
            prefixSum[i] += diff;
        }
        nums[i] = val;
    }

    public int prefixSum(int i) {
        return prefixSum[i];
    }

    public int rangeSum(int i, int j) {
        if (i == 0)
            return prefixSum[j];
        return prefixSum[j] - prefixSum[i - 1];
    }
}