package com.shawn.ss.interview_code;

import com.shawn.ss.leetcode.LinkListOperation;

public class DDInterview2 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head = buildList(10);
        ListNode lastN = findLastNNode(head, 5);
        System.out.println(lastN.val);
    }

    private static ListNode findLastNNode(ListNode head, int i) {
        if (head == null) throw new IllegalArgumentException("null list head");
        ListNode cur = head;
        ListNode ret = head;
        while (i-- > 0 && cur != null) {
            cur = cur.next;
        }
        if (cur == null) throw new IllegalArgumentException("linked list length should be great than " + i);
        while (cur != null) {
            cur = cur.next;
            ret = ret.next;
        }
        return ret;
    }

    private static ListNode buildList(int i) {
        return null;
    }
}
