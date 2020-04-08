package mine.leetcode.build_tree;

import java.util.HashMap;

import mine.leetcode.TreeNode;

public class BuildTreeInPost {

    private int[] postorder;
    private HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        map = new HashMap<>();
        int i = 0;
        for (int val : inorder) {
            map.put(val, i++);
        }
        return build(0, inorder.length, 0, inorder.length);
    }

    /**
     * 当前树包含的所有元素在inorder和postorder中的起始位置分别是[inlo,inhi),[postlo,posthi)
     * <p>
     * 注意是左闭右开区间
     * 
     * @param inlo
     * @param inhi
     * @param postlo
     * @param posthi
     * @return
     */
    private TreeNode build(int inlo, int inhi, int postlo, int posthi) {
        if (inlo >= inhi)
            return null;
        int rootVal = postorder[posthi - 1];
        // int idx = inlo;
        // while (inorder[idx++] != rootVal)
        // ;
        // idx--;

        // 考虑使用hashMap记录索引，更快
        TreeNode root = new TreeNode(rootVal);
        int idx = map.get(rootVal);
        root.left = build(inlo, idx, postlo, postlo + (idx - inlo));
        root.right = build(idx + 1, inhi, postlo + (idx - inlo), posthi - 1);
        return root;
    }

    

    public static void main(String[] args) {
        int[] inorder = { 9, 3, 15, 20, 7 };
        int[] postorder = { 9, 15, 7, 20, 3 };
        new BuildTreeInPost().buildTree(inorder, postorder);
    }
}