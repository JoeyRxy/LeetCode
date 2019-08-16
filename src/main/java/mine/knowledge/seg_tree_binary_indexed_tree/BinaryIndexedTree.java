package mine.knowledge.seg_tree_binary_indexed_tree;

/**
 * BinaryIndexedTree
 * 
 * <p>
 * <a href=
 * "file:///C:\Users\29388\Documents\code\LeetCode\src\main\java\mine\knowledge\seg_tree_binary_indexed_tree\2019-05-11-11-52-46.png">Example
 * 1</a>
 * <p>
 * <a href=
 * "file:///C:\Users\29388\Documents\code\LeetCode\src\main\java\mine\knowledge\seg_tree_binary_indexed_tree\2019-05-11-10-43-20.png">Example
 * 2</a>
 * 
 * <p>
 * 从1开始计数！
 * <p>
 * 关键操作 {@code i&(-i)}： <a href=
 * "file:///C:\Users\29388\Documents\code\LeetCode\src\main\java\mine\knowledge\seg_tree_binary_indexed_tree\2019-05-11-12-45-38.png">rightSibling</a>
 */
public class BinaryIndexedTree {

    private double[] BIT;// BinaryIndexedTree
    private double[] nums;

    public BinaryIndexedTree(double[] a) {
        nums = a;
        BIT = new double[a.length + 1];// 从1开始计算
        int len = BIT.length;
        for (int i = 1; i != len; i++) // BIX[1...n]=a[0...n-1]
            BIT[i] = a[i - 1];
        int j;
        for (int idx = 1; idx != len; idx++) {
            j = rightSibling(idx);
            if (j < len)
                BIT[j] += BIT[idx];
        }
    }

    public double prefixSum(int idx) {
        idx += 1;
        double sum = 0;
        while (idx != 0) {
            sum += BIT[idx];
            idx = parent(idx);
        }
        return sum;
    }

    /**
     * 
     * @param i 起始位置（包括i）
     * @param j 终止位置（包括j）
     * @return rangeSum of [{@code i}...{@code j}]
     */
    public double rangeSum(int i, int j) {
        if (i == 0)
            return prefixSum(j);
        return prefixSum(j) - prefixSum(i - 1);
    }

    // TODO ：i&(-i)?可以将二进制进行什么变换？
    // ATTENTION : i&(-i)?可以将二进制进行什么变换？能理解但是想不到啊！
    private int parent(int idx) {
        return idx - (idx & (-idx));// WARNING : 涉及位运算的一定要加括号
    }

    /**
     * update nums[i]=val;
     */
    public void update(int idx, double val) {
        double diff = val - nums[idx];
        nums[idx] = val;
        idx += 1;
        while (idx < BIT.length) {// 遍历右侧的兄弟节点
            BIT[idx] += diff;
            idx = rightSibling(idx);
        }
    }

    // ATTENTION : 想不到啊
    private int rightSibling(int idx) {
        return idx + (idx & (-idx));
    }

    public static void main(String[] args) {
        double[] nums = { 1, 3, 5 };
        nums[0] = 0;
        BinaryIndexedTree obj = new BinaryIndexedTree(nums);
        System.out.println(obj.rangeSum(0, 2));
        obj.update(1, 2);
        System.out.println(obj.rangeSum(0, 2));
    }
}