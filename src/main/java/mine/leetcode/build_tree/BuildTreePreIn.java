package mine.leetcode.build_tree;

import java.util.HashMap;

import mine.leetcode.TreeNode;

public class BuildTreePreIn {

    private int[] pre;
    private HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] pre, int in[]) {
        this.pre = pre;
        map = new HashMap<>();
        int i = 0;
        for (int v : in) 
            map.put(v, i++);
        
        return build(0, pre.length, 0, pre.length);
    }

    private TreeNode build(int prelo, int prehi, int inlo, int inhi) {
        if (prelo >= prehi)
            return null;
        TreeNode root = new TreeNode(pre[prelo]);
        Integer idx = map.get(pre[prelo]);
        int _prehi = prelo + (idx - inlo) + 1;
        root.left = build(prelo + 1, _prehi, inlo, idx);
        root.right = build(_prehi, prehi, idx + 1, inhi);
        return root;
    }

    public static void main(String[] args) {
        int[] pre = { 5, -1, 3, 2, 0, 7, 11, 12, 9, 6, 10, 8 };
        int[] in = { 3, 2, -1, 0, 5, 12, 11, 7, 10, 6, 8, 9 };
        TreeNode root = new BuildTreePreIn().buildTree(pre, in);
        System.out.println(root);
    }
}