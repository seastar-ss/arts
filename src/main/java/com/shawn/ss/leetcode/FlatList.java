package com.shawn.ss.leetcode;

public class FlatList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }


    public Node flatten(Node head) {
        if (head == null) return null;

        flattenChild(head);
        return head;
    }

    private Node[] flattenChild(Node head) {
        if (head.next == null && head.child == null) return new Node[]{head, head};
        Node cur = head, last = null;
        do {
            if (cur.child != null) {
                //                Node last=cur;
                Node[] subList = flattenChild(cur.child);
                Node tmp = cur.next;
                cur.next = subList[0];
                subList[0].prev = cur;
                subList[1].next = tmp;
                cur.child = null;
                if (tmp != null) {
                    tmp.prev = subList[1];
                    last = cur;
                    cur = tmp;
                } else {
                    last = subList[1];
                    break;
                }
            } else {
                last = cur;
                cur = cur.next;
            }
        } while (cur != null);
        return new Node[]{head, last};
    }
}
