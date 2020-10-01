package com.shawn.ss.leetcode;

public class IntersectionNodeOfList {


    //     * Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //    public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA, curB = headB;
        int lenA = 0, lenB = 0;
        boolean tailA = false, tailB = false;
        while (!tailA || !tailB) {
            tailA = curA.next == null;
            tailB = curB.next == null;
            curA = tailA ? curA : curA.next;
            curB = tailB ? curB : curB.next;
            lenA += tailA ? 0 : 1;
            lenB += tailB ? 0 : 1;
        }
        if (curA != curB) {
            return null;
        }
        curA = headA;
        curB = headB;
        if (lenA > lenB) {
            int index = 0;
            while (index < lenA - lenB) {
                curA = curA.next;
                ++index;
            }
        } else if (lenA < lenB) {
            int index = 0;
            while (index < lenB - lenA) {
                curB = curB.next;
                ++index;
            }
        }
        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA;
            } else {
                curA = curA.next;
                curB = curB.next;
            }
        }
        return null;
    }
    //    }
}
