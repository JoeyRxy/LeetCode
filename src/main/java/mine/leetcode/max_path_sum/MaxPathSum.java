package mine.leetcode.max_path_sum;

import mine.leetcode.TreeNode;

/**
 * MaxPathSum <a href="www.example.com"></a>
 */
public class MaxPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        singlePathMax(root);
        return max;
    }

    private int singlePathMax(TreeNode r) {
        if (r == null)
            return 0;
        int left = singlePathMax(r.left);
        int right = singlePathMax(r.right);
        left = left > 0 ? left : 0;// 这两步很关键啊……何时以及如何废掉一个结果
        right = right > 0 ? right : 0;
        // int tmp = left > right ? left : right;
        // max = Math.max(max, Math.max(tmp, left + right + r.val));
        // return tmp + r.val;

        max = Math.max(max, left + right + r.val);
        return (left > right ? left : right) + r.val;
    }

    // int leftSingle, rightSingle;

    // private int helper(TreeNode r) {
    // int leftMax = helper(r.left), rightMax = helper(r.right);
    // leftSingle = single(r.left);
    // rightSingle = single(r.right);
    // int tmp = Math.max(leftSingle + rightSingle + r.val, Math.max(leftMax,
    // rightMax));
    // if (tmp > max)
    // max = tmp;
    // return tmp;
    // }

    // private int single(TreeNode r) {
    // return Math.max(leftSingle, rightSingle) + r.val;
    // }

    // private int maxPathSumFrom(TreeNode r) {
    // if (r == null)
    // return 0;
    // int left = singlePathSum(r.left);
    // int right = singlePathSum(r.right);
    // left = left > 0 ? left : 0;
    // right = right > 0 ? right : 0;

    // }

    // private int singlePathSum(TreeNode r) {
    // if(r==null)
    // return 0;
    // singlePathSum(r.left)
    // }

    public static void main(String[] args) {
        String[] test = { "[1,2,null,3,null,4,null,5]", "[1,2,3]", "[-3]", "[-10,9,20,null,null,15,7]" };
        for (String str : test) {
            TreeNode root = TreeNode.stringToTreeNode(str);
            MaxPathSum t = new MaxPathSum();
            int ret = t.maxPathSum(root);
            System.out.println(ret);
        }
    }
}