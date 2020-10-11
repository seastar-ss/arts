package com.shawn.ss.leetcode;

//import com.shawn.ss.interview_code.Solution;

public class ReverseListByGroup {

    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        ListNode cur = root;
        root.next = new ListNode(1);
        root = root.next;
        root.next = new ListNode(2);
        root = root.next;
        root.next = new ListNode(3);
        root = root.next;
        root.next = new ListNode(4);
        //        root = root.next;
        //        root.next = new ListNode(5);
        //        root = root.next;
        //        root.next = new ListNode(6);
        ListNode listNode = new ReverseListByGroup().reverseKGroup(cur, 3);
        printList(listNode);
    }

    private static void printList(ListNode listNode) {
        ListNode cur = listNode;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println();
    }

    public static class ListNode {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 2) return head;
        ListNode ret = head;
        ListNode cur = head;
        ListNode gpTail = null, gpHead = cur;
        ListNode preGPTail = null, preGPHead = null;
        int index = 0;
        ListNode tmp = null;
        while (cur != null) {
            if (index < k - 1) {
                index++;
                cur = cur.next;
            } else if (index == k - 1) {
                gpTail = cur;
                tmp = cur.next;
                reserveLinkList(gpHead, gpTail, preGPTail);
                preGPHead = gpTail;
                preGPTail = gpHead;
                cur = tmp;
                gpHead = cur;
                index = 0;
                if (ret == head) {
                    ret = preGPHead;
                }
            }
        }
        if (index > 0) {
            if (preGPTail != null)
                preGPTail.next = gpHead;
        }
        return ret;
    }

    private void reserveLinkList(ListNode gpHead, ListNode gpTail, ListNode preGPTail) {
        //        ListNode[] ret = new ListNode[]{};
        ListNode cur = gpHead, pre = null;
        while (cur != null) {
            if (cur != gpTail) {
                ListNode tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            } else {
                if (preGPTail != null) {
                    preGPTail.next = cur;
                }
                cur.next = pre;
                break;
            }
        }
        //        return ret;
    }
}
