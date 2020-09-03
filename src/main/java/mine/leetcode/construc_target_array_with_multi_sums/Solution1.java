package mine.leetcode.construc_target_array_with_multi_sums;

import java.util.Arrays;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.IndexMinPQ;

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

    private int[] a;
    private int len;
    private int sum;
    private int[] target;
    private boolean res;

    private IndexMinPQ<Integer> pq;

    public boolean isPossible(int[] target) {
        this.target = target;
        len = target.length;
        a = new int[len];
        res = false;
        pq = new IndexMinPQ<>(len);
        for (int i = 0; i < len; i++) {
            a[i] = 1;
            pq.insert(i, target[i]);
        }

        sum = len;
        for (int i = 0; i < len; i++)
            dfs(i);

        return res;
    }

    private void dfs(int i) {
        if (res)// 快速收尾
            return;
        if (sum > target[i])// 这个判断结束的条件实际上比较慢，对于target={9，3，5}来说，第一步dfs(0)进到状态{3,1,1}就已经可以判定该状态不合适了：因为在之后的动作中，sum越来越大，不可能在1位置填入3了。
            return;

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

        sum = a[i];// 还原sum;TODO利用a[i]来还原sum可行吗？待证明
        a[i] = _a_i;// 还原a[i]
    }

    // 待修改
    private void dfs2(int i) {
        if (res)// 快速收尾
            return;
        // if (sum > target[i])//
        // 这个判断结束的条件实际上比较慢，对于target={9，3，5}来说，第一步dfs(0)进到状态{3,1,1}就已经可以判定该状态不合适了：因为在之后的动作中，sum越来越大，不可能在1位置填入3了。
        // return;
        int idx = pq.minIndex();
        if (idx != i && target[idx] <= sum)
            return;

        int _a_i = a[i];
        // 更新a[i]
        a[i] = sum;
        if (sum == target[i]) {
            System.out.println(pq.delMin() == i);
        }
        // 更新sum
        sum += sum;
        sum -= _a_i;
        if (verify()) {
            res = true;
            return;
        }
        for (int j = 0; j < len; j++)
            dfs(j);

        sum = a[i];// 还原sum;TODO利用a[i]来还原sum可行吗？待证明
        a[i] = _a_i;// 还原a[i]
        pq.insert(i, target[i]);
    }

    private boolean verify() {
        for (int i = 0; i < len; i++) {
            if (a[i] != target[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] target = { 9, 3, 5 };
        Solution1 solution1 = new Solution1();
        boolean possible = solution1.isPossible(target);
        System.out.println("============================");
        System.out.println(possible);
        // System.out.println("==== " + solution1.count + " ======");
    }

}