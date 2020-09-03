package mine.knowledge.seg_tree_binary_indexed_tree;

public class Node<T> {
    int l, r;
    T val;
    Node<T> leftChild, rightChild;

    public Node(int l, int r) {
        this.l = l;
        this.r = r;
    }

    public Node(int l, int r, T val, Node<T> leftChild, Node<T> rightChild) {
        this.l = l;
        this.r = r;
        this.val = val;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}