package mine.leetcode.construc_target_array_with_multi_sums;

/**
 * ConstructTargetArrayWithMultiSums
 * <p>
 * https://leetcode.com/problems/construct-target-array-with-multiple-sums/
 * <p>
 * Given an array of integers&nbsp;<code>target</code>. From a starting array,
 * <code>A</code>&nbsp;consisting of all 1's, you may perform the following
 * procedure :
 * </p>
 * <ul>
 * <li>let <code>x</code> be the sum of all elements currently in your
 * array.</li>
 * <li>choose index <code>i</code>, such
 * that&nbsp;<code>0 &lt;= i &lt; target.size</code> and set the value of
 * <code>A</code> at index <code>i</code> to <code>x</code>.</li>
 * <li>You may repeat this procedure&nbsp;as many times as needed.</li>
 * </ul>
 * <p>
 * Return <code>True</code> if it is possible to construct the
 * <code>target</code> array from <code>A</code> otherwise&nbsp;return
 * <code>False</code>.
 * </p>
 */
public class Solution1 {
    public int count;

    private int[] a;
    private int min_i;
    private int len;
    private int sum;
    private int[] target;
    private boolean res;

    public boolean isPossible(int[] target) {
        this.target = target;
        len = target.length;
        a = new int[len];
        res = false;
        for (int i = 0; i < len; i++) {
            a[i] = 1;
        }
        min_i = len - 1;
        sum = len;
        for (int i = 0; i < len; i++) {
            dfs(i);
        }
        return res;
    }

    private boolean helper() {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 1)
                return false;
        }
        return true;
    }

    private void dfs(int i) {
        count++;
        if (res)// 快速收尾
            return;
        if (sum > target[i])
            return;

        int _sum = sum;
        int _a_i = a[i];
        // 更新a[i]
        a[i] = sum;
        // 更新sum
        sum += sum;
        sum -= _a_i;
        if (verify()) {
            res = true;
            return;
        }
        for (int j = 0; j < len; j++)
            dfs(j);

        a[i] = _a_i;// 还原a[i]
        sum = _sum;// 还原sum
    }

    private boolean verify() {
        for (int i = 0; i < len; i++) {
            if (a[i] != target[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] target = { 1, 100 };
        Solution1 solution1 = new Solution1();
        boolean possible = solution1.isPossible(target);
        System.out.println(possible);
        System.out.println("==== " + solution1.count + " ======");
    }

}