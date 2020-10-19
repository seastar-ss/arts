package com.shawn.ss.leetcode;

/*
https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class DeleteLinkedListLastNNode {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int index = 0;
        if (head == null) return null;
        ListNode tail = head;
        ListNode cur = head;
        while (tail != null && index < n) {
            tail = tail.next;
            ++index;
        }
        System.out.println((tail == null ? "null" : tail.val) + " " + index);
        if (index < n) return null;
        if (tail==null) {
            head = cur.next;
        } else  {
            tail = tail.next;
            System.out.println(cur.val);
            while (tail != null) {
                tail = tail.next;
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}
