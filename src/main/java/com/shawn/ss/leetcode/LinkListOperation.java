package com.shawn.ss.leetcode;


// Definition for singly-linked list.

/**
 * https://leetcode-cn.com/problems/reorder-list/
 */
public class LinkListOperation {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 1->2->3->4->...->n-2->n-1->n
     * 转换为
     * 1->n->2->n-1->3->n-2->4->....
     * @param args
     */

    public static void main(String args[]) {
        ListNode root = new ListNode(0);
        ListNode cur = root;
        root.next = new ListNode(1);
        root = root.next;
        root.next = new ListNode(2);
        root = root.next;
        root.next = new ListNode(3);
        root = root.next;
        root.next = new ListNode(4);
        new LinkListOperation().reorderList(cur);
    }

    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode cur = head;
        ListNode cur2 = head;
        int i = 0;
        while (true) {
//            if (cur.next != null)
//
//            else
//                break;
            if (cur2.next != null && cur2.next.next != null) {
                cur2 = cur2.next.next;
            } else {
                //                cur2 = cur2.next;
                break;
            }
            cur = cur.next;
            ++i;
        }
        System.out.println("head :" + cur + " " + i);
        if (cur == cur2) {
            return;
        }

        cur = reverseListNode(cur);
        System.out.println("reverved :" + cur);
        //        int index = 0;
        mergeListNode(head, cur);
    }

    private void mergeListNode(ListNode head, ListNode cur) {
        while (cur != null && head != null) {
            ListNode tmpCur = cur.next;
            ListNode tmpHead = head.next;
            head.next = cur;
            cur.next = tmpHead;
            cur = tmpCur;
            head = tmpHead;
        }

    }

    private ListNode reverseListNode(ListNode cur2) {
        ListNode cur = cur2;
        ListNode pre = null;

        /**
         * cur-> cur.next
         * cur =cur.next
         * cur.next -> cur
         */
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
