package com.shawn.ss.leetcode;

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    int index;
    ListNode next;
    ListNode prev;

    static ListNode buildListNode(int[] args) {
        ListNode root = null, cur = null;
        if (args != null) {
            for (int index = 0, n = args.length; index < n; ++index) {
                int i = args[index];
                ListNode node = new ListNode(i);
                node.index = index;
                if (root == null) {
                    root = node;
                    cur = node;

                } else {
                    cur.next = node;
                    node.prev = cur;
                    cur = node;
                }
            }
        }
        return root;
    }

    static ListNode getTail(ListNode n) {
        if (n == null) return null;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    ListNode(int x) {
        val = x;
    }

    static String toList(ListNode n) {
        return toList(n, null);
    }

    public static String toList(ListNode n, ListNode tail) {
        if (n == null) return "";
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(n.index).append(":").append(n.val).append("->");
            n = n.next;
        } while (n != tail && n != null);
        return builder.toString();
    }
}
