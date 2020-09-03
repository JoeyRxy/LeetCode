package mine.knowledge.seg_tree_binary_indexed_tree;

/**
 * SegmentTree
 * <p>
 * 完全二叉树即可，使用2n空间从下往上构造，即使数组长度不是2^n也可以;
 * <p>
 * {@code 完全二叉树}！从1开始填数。右孩子的index为奇数，左孩子的index为偶数
 */
public class SegmentTree {
    // ATTENTION ：很多位运算！见 Numbered Bookmark
    private int[] tree;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        tree = new int[n << 1];
        for (int i = n, j = 0; j != n; i++, j++)
            tree[i] = nums[j];
        // 可以构造max - seg tree 但我们这里构造了sum tree
        // ATTENTION 到1就结束了，0处没有元素哦
        for (int i = n - 1; i != 0; i--)
            // tree[i] = tree[(i << 1) + 1] + tree[i << 1];// WARN : 位运算符加括号！优先级低
            tree[i] = tree[i << 1] + tree[i << 1 | 1];// | 相当于+1了
    }

    // ATTENTION 从下往上更新树 和当时想的Node+递归的方法不同哦
    public void update(int i, int val) {
        i += nums.length;
        tree[i] = val;
        // int sibling;
        while (i != 0) {
            /*
             * if (i % 2 == 0) sibling = i + 1; else sibling = i - 1; tree[i >> 1] = tree[i]
             * + tree[sibling];
             */
            tree[i >> 1] = tree[i] + tree[i ^ 1];
            i >>= 1;
        }
    }

    // 可能是rangeMax等……
    // ATTENTION 他这个想法和我那个不一样啊！我是从下往上走的
    /**
     * {@link mine.leetcode.num_array.SegmentTreeRecursion} My First Thought</a>
     */
    public int rangeSum(int i, int j) {
        i += nums.length;
        j += nums.length;
        int sum = 0;
        while (i <= j) {
            if ((i & 1) == 1) {// 左边界为一个右孩子
                sum += tree[i];
                i++;// 左边界右移一个
            }
            if ((j & 1) == 0) {// 右边界为一个左孩子
                sum += tree[j];
                j--;// 右边界左移一个
            }
            // 其他情况
            i >>= 1;
            j >>= 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 4, 3, 5, 8 };
        SegmentTree obj = new SegmentTree(nums);
        System.out.println(obj.rangeSum(1, 3));
        obj.update(1, 2);
        System.out.println(obj.rangeSum(1, 3));
    }
}