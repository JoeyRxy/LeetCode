package mine.leetcode;

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
public class ConstructTargetArrayWithMultiSums {

    private int[] a;
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
        sum = len;
        for (int i = 0; i < len; i++)
            dfs(i);
        return res;
    }

    private void dfs(int i) {
        if (res)// 快速收尾
            return;
        if (sum > target[i])// 剪枝
            return;
        else if (sum == target[i]) {
            a[i] = sum;
            return;
        }
        int _a_i = a[i];
        int _sum = sum;
        // 更新a[i]
        a[i] = sum;
        // 更新sum
        sum += sum;
        sum -= _a_i;
        if (verify()) {
            res = true;
            return;
        }
        for (int j = 0; j < len; j++) {
            dfs(j);
        }
        a[i] = _a_i;// 还原a[i]
        sum = _sum;// 还原sum
    }

    private boolean verify() {
        for (int i = 0; i < len; i++) {
            if (a[i] < target[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] target = { 9, 3, 5 };
        boolean possible = new ConstructTargetArrayWithMultiSums().isPossible(target);
        System.out.println(possible);
    }

}