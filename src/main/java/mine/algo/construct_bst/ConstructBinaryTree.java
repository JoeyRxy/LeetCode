package mine.algo.construct_bst;

import mine.algo.TestProcessor;
import mine.algo.TreeNode;

/**
 * ConstructBinaryTree
 * <p>
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * </p>
 * <p>
 * assume that duplicates do not exist in the tree.
 * </p>
 * <p>
 * 如何准确地描述自己的想法？尤其是"边界条件"——在这里就是没有左子树和没有右子树的特殊情况
 * </p>
 */
public class ConstructBinaryTree {
    private int[] pre;
    private int[] in;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length)
            throw new IllegalArgumentException("preorder.length must agree with inorder.length!");
        if (preorder.length == 0)
            return null;

        this.pre = preorder;
        this.in = inorder;
        // return recurseBuild(0, preorder.length, 0, inorder.length);
        return helper(0, 0, pre.length - 1);
    }

    /**
     * 用例{[1,2],[2,1]}这组不行
     * 
     * @param prelo
     * @param prehi
     * @param inlo
     * @param inhi
     * @return
     */
    // private TreeNode recurseBuild(int prelo, int prehi, int inlo, int inhi) {
    // if (prelo >= pre.length)// 解决数组越界：{[1,2],[2,1]}
    // return null;
    // int val = pre[prelo];
    // TreeNode node = new TreeNode(val);
    // if (prehi == prelo + 1)
    // return node;
    // int i = indexOf(in, val);
    // int j = i == 0 ? 0 : (indexOf(pre, in[i - 1]) + 1);// 解决数组越界：{[1,2],[1,2]}
    // node.left = recurseBuild(prelo + 1, j, inlo, i);
    // node.right = recurseBuild(j, prehi, i + 1, inhi);
    // return node;
    // }

    private int indexOf(int[] a, int val) {
        int i = 0;
        try {
            while (a[i] != val)
                i++;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("No val(" + val + ") in inoreder array");
        }
        return i;
    }

    // The Answer
    public TreeNode helper(int prelo, int inlo, int inhi) {
        if (prelo >= pre.length || inlo > inhi)
            return null;
        int val = pre[prelo];
        TreeNode node = new TreeNode(val);
        int i = indexOf(in, val);
        node.left = helper(prelo + 1, inlo, i - 1);
        node.right = helper(prelo + i - inlo + 1, i + 1, inhi);// TODO : 还没理解
        return node;
    }

    public static void main(String[] args) {
        String[][] test = { { "[1,2]", "[1,2]" }, { "[1,2]", "[2,1]" }, { "[3,9,20,15,7]", "[9,3,15,20,7]" } };
        for (String[] x : test) {
            int[] preorder = TestProcessor.stringToIntegerArray(x[0]);
            int[] inorder = TestProcessor.stringToIntegerArray(x[1]);
            TreeNode root = new ConstructBinaryTree().buildTree(preorder, inorder);
            System.out.println(root.val);
        }
    }
}