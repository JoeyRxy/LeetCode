package mine.algo.linked_list_cycle_ii;

import mine.algo.ListNode;
import mine.algo.TestProcessor;

/**
 * LinkedListCycle
 */
public class CycleII {
    /**
     * Node
     */
    public class Node {

        ListNode node;
        Node next;
        boolean marked;

        Node(ListNode node, boolean marked) {
            this.node = node;
            this.marked = marked;
        }
    }

    public ListNode detectCycle(ListNode head) {
        Node Head = new Node(head, false);
        Node node = Head;
        while (node.node != null) {
            if (node.marked)
                return node.node;
            node.marked = true;
            node = new Node(node.node.next, false);
        }
        return null;
    }

    public static void main(String[] args) {
        String testStr = "[3,2,0,-4]";
        int pos = 1;
        ListNode head = TestProcessor.stringToListNode(testStr);
        ListNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        ListNode tail = tmp;
        tmp = head;
        while (pos != 0) {
            tmp = tmp.next;
            pos--;
        }
        tail.next = tmp;
        CycleII t = new CycleII();
        ListNode ret = t.detectCycle(head);
        System.out.println("hello");
    }
}