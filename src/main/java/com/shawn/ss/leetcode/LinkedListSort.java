package com.shawn.ss.leetcode;

public class LinkedListSort {

    public static void main(String[] args) {
        ListNode node = ListNode.buildListNode(new int[]{
                10, 1, 20, 34, 5, 8, 2, 99, 5, 6, 99, 34, 9, 11, 6, 7, 7, 7, 88, 45
        });
        node = qsortLinkedList(node, ListNode.getTail(node));
        System.out.println(ListNode.toList(node));
//        node = qsortLinkedListV2(node, ListNode.getTail(node));
//        System.out.println(ListNode.toList(node));
    }

//    private static ListNode qsortLinkedListV2(ListNode head, ListNode tail) {
//        if (head != null && tail != null && head != tail) {
//            int cur = (head.val + tail.val) / 2;
//            ListNode start = head;
//            ListNode end = tail;
//            while (start != end) {
//                while (start.next!=null && start.val < cur) {
//                    start = start.next;
//                }
//                while (end.prev!=null && end.val >= cur) {
//                    end = end.prev;
//                }
//                swap(start,end);
//            }
//            if (start.prev != null && start.prev != head && start != head)
//                qsortLinkedList(head, start.prev);
//            if (start.next != null && start.next != tail && start != tail)
//                qsortLinkedList(start.next, tail);
//        }
//        return head;
//    }

    private ListNode getTail(ListNode n) {
        if (n == null) return null;
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    private static ListNode qsortLinkedList(ListNode head, ListNode tail) {
        if (head != null && tail != null && head != tail) {
            ListNode cur = head;
            ListNode start = head;
            ListNode end = tail;
            int state = 0;
            while ((cur != start || cur != end)) {
                if (state == 0) {
                    while (cur.next != null && cur.val > cur.next.val && cur != end) {
                        swap(cur, cur.next);
                        cur = cur.next;
                    }
                    start = cur;
                    state = 2;
                } else if (state == 1) {
                    while (cur.prev != null && cur.val < cur.prev.val && cur != start) {
                        swap(cur, cur.prev);
                        cur = cur.prev;
                    }
                    end = cur;
                    state = 3;
                } else if (state == 2) {
                    while (end != null && end != cur && end.val >= cur.val) {
                        end = end.prev;
                    }
                    if (end != null) {
                        if (end.val < cur.val)
                            swap(cur, end);
                        cur = end;
                        state = 1;
                    }
                } else if (state == 3) {
                    while (start != null && start != cur && start.val <= cur.val) {
                        start = start.next;
                    }
                    if (start != null) {
                        if (start.val > cur.val)
                            swap(cur, start);
                        cur = start;
                        state = 0;
                    }
                }
            }
            System.out.println(ListNode.toList(head, tail));
            if (cur.prev != null && cur.prev != head && cur != head)
                qsortLinkedList(head, cur.prev);
            if (cur.next != null && cur.next != tail && cur != tail)
                qsortLinkedList(cur.next, tail);
//            return head;
        }
        return head;
    }

    private static void swap(ListNode n, ListNode m) {
        if (n != null && m != null) {
            System.out.println("swap " + n.index + ":" + n.val + "->" + m.index + ":" + m.val);
            int tmp = n.val;
            n.val = m.val;
            m.val = tmp;
        }

    }

}
