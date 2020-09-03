package mine.knowledge.seg_tree_binary_indexed_tree;

public class SegmentTree2<T> {
    private T[] vals;
    private ComputeFunction<T> func;
    private Node<T> root;

    public SegmentTree2(T[] vals, ComputeFunction<T> f) {
        this.vals = vals;
        this.func = f;
        root = new Node<>(0, vals.length);
        build(root);
    }

    private void build(Node<T> node) {
        if (node.l + 1 == node.r) {
            node.val = vals[node.l];
            return;
        }
        int mid = (node.l + node.r) >> 1;
        Node<T> leftChild = new Node<T>(node.l, mid);
        build(leftChild);
        Node<T> rightChild = new Node<T>(mid, node.r);
        build(rightChild);
        node.leftChild = leftChild;
        node.rightChild = rightChild;
        node.val = func.f(leftChild.val, rightChild.val);
    }

    /**
     * [l, r)
     * 
     * @param l
     * @param r
     * @return
     */
    public T rangeQuery(int l, int r) {
        return range(root, l, r);
    }

    private T range(Node<T> node, int l, int r) {
        if (node.l == l && node.r == r)
            return node.val;
        int mid = (node.l + node.r) >> 1;
        if (l >= mid)
            return range(node.rightChild, l, r);
        else if (r <= mid)
            return range(node.leftChild, l, r);
        else if (l < mid && r > mid)
            return func.f(range(node.leftChild, l, mid), range(node.rightChild, mid, r));
        else
            throw new IllegalArgumentException("[l = " + l + ", r = " + r + ") out of range.");
    }

    public void update(int i, T v) {
        if (v.equals(vals[i]))
            return;
        vals[i] = v;
        updateRange(root, i, v);
    }

    private void updateRange(Node<T> node, int i, T v) {
        if (node.r == node.l + 1) {
            node.val = v;
            return;
        }
        if (i < ((node.l + node.r) >> 1))
            updateRange(node.leftChild, i, v);
        else
            updateRange(node.rightChild, i, v);
        node.val = func.f(node.leftChild.val, node.rightChild.val);
    }

    public static void main(String[] args) {
        Integer[] vals = { 7, 4, 9, 1, 3 };
        SegmentTree2<Integer> tree = new SegmentTree2<>(vals, (v1, v2) -> {
            return v1 + v2;
        });
        Integer res = tree.rangeQuery(1, 3);
        System.out.println(res);
        tree.update(2, 7);
        Integer res2 = tree.rangeQuery(1, 3);
        System.out.println(res2);
    }
}