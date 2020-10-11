package com.shawn.ss.leetcode;

import java.util.HashSet;
import java.util.Set;

public class CycleLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode cur1 = head;
        ListNode cur2 = head;
        while (cur2 != null) {
            if (cur2.next != null && cur2.next.next != null) {
                cur2 = cur2.next.next;
            } else {
                break;
            }
            cur1 = cur1.next;
            if (cur1 == cur2) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode cur = head;
        Set<ListNode> nodes = new HashSet<>();
        while (cur != null) {
            if (nodes.contains(cur)) {
                return cur;
            }
            nodes.add(cur);
            cur = cur.next;
        }
        return null;
    }

}
