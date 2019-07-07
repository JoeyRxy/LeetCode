package mine.algo.num_array;

/**
 * NumArray2
 * <p>
 * 线段树 递归
 */
public class SegmentTreeRecursion {
    private int[] nums;

    public SegmentTreeRecursion(int[] nums) {
        if (nums.length == 0)
            return;
        this.nums = nums;
        segPreProcessing();
    }

    // ============== Recursion ==============//
    // ATTENTION : Segment Tree : first time saw
    /**
     * Node
     */
    public class Node {

        private int sum;
        private Node left;
        private Node right;
        private int i;
        private int j;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        Node(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }

    private Node root;

    public void segPreProcessing() {
        root = new Node(0, nums.length - 1);
        construct(root);
    }

    private void construct(Node r) {
        int i = r.i;
        int j = r.j;
        if (i == j) {
            r.sum = nums[i];
            return;
        }
        int mid = i + (j - i) / 2;
        r.left = new Node(i, mid);
        r.right = new Node(mid + 1, j);
        construct(r.left);
        construct(r.right);
        r.sum = r.left.sum + r.right.sum;
    }

    public void segUpdate(int i, int val) {
        segUpdate(root, i, val);
        nums[i] = val;
    }

    private void segUpdate(Node r, int index, int val) {
        if (r == null)
            return;
        r.sum += val - nums[index];
        if (r.i == r.j)
            return;
        if (r.right.i > index)
            segUpdate(r.left, index, val);
        else
            segUpdate(r.right, index, val);
    }

    public int segRangeSum(int i, int j) {
        return segRangeSum(root, i, j);
    }

    private final int THRESHHOLD = 10;

    private int segRangeSum(Node r, int lo, int hi) {
        if (r == null)
            return 0;
        if (lo == r.i && hi == r.j)
            return r.sum;
        if (hi <= lo + THRESHHOLD)
            return naiveSumRange(lo, hi);
        int mid = r.left.j;
        if (hi <= mid)
            return segRangeSum(r.left, lo, hi);
        else if (lo > mid)
            return segRangeSum(r.right, lo, hi);
        return segRangeSum(r.left, lo, mid) + segRangeSum(r.right, mid + 1, hi);
    }

    public int naiveSumRange(int i, int j) {
        int sum = 0;
        for (int l = i; l <= j; l++) {
            sum += nums[l];
        }
        return sum;
    }
}