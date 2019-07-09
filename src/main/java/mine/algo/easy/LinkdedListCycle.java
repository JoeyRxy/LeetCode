package mine.algo.easy;

import mine.algo.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LinkdedListCycle
 * <p>
 * Given a linked list, determine if it has a cycle in it.
 * </p>
 * <p>
 * To represent a cycle in the given linked list, we use an integer
 * <code>pos</code> which represents the position (0-indexed)&nbsp;in the linked
 * list where tail connects to. If <code>pos</code> is <code>-1</code>, then
 * there is no cycle in the linked list.
 * </p>
 */
public class LinkdedListCycle {

    // Too Slow
    // public boolean hasCycle(ListNode head) {
    // Map<ListNode, Boolean> marked = new HashMap<>();
    // ListNode node = head;
    // while (node != null) {
    // if(marked.containsKey(node))
    // return true;
    // marked.put(node, true);
    // node = node.next;
    // }
    // return false;
    // }

    public boolean hasCycle(ListNode head) {
        List<ListNode> marked = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
        }
        return false;
    }

    public boolean twoPointers(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}