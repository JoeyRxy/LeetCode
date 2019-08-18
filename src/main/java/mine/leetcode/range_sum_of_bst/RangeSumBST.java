package mine.leetcode.range_sum_of_bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import mine.leetcode.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class RangeSumBST {

    private int sum;

    public void Sum(TreeNode root, int L, int R) {
        if (root.val >= L && root.val <= R)
            sum += root.val;
        if (root.left != null)
            Sum(root.left, L, R);
        if (root.right != null)
            Sum(root.right, L, R);
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        // Sum(root, L, R);
        sum = 0;

        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        // TreeNode node;
        // while (!s.isEmpty()) {
        // node = s.peek();
        // if (node.val >= L && node.val <= R) {
        // sum += node.val;
        // }
        // if(node.left!=null)
        // s.push(node.left);
        // if(node.right!=null)
        // s.push(node.right);
        // }

        return sum;
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int L = Integer.parseInt(line);
            line = in.readLine();
            int R = Integer.parseInt(line);

            int ret = new RangeSumBST().rangeSumBST(root, L, R);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
    // 过于笨拙
    /*
     * public int rangeSumBST(TreeNode root, int L, int R) { int sum = 0;
     * Stack<TreeNode> s = new Stack<>(); if (root.val >= L && root.val <= R) sum +=
     * root.val; s.push(root); TreeNode tmp; while (!s.isEmpty()) { tmp = s.peek();
     * if (tmp.left != null) { if (tmp.left.val >= L && tmp.left.val <= R) sum +=
     * tmp.left.val; s.push(tmp.left); } else if (tmp.right != null) { if
     * (tmp.right.val >= L && tmp.right.val <= R) sum += tmp.right.val;
     * s.push(tmp.right); } else s.pop(); }
     * 
     * return sum; }
     */

}