package com.shawn.ss.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
public class PutNextToBinaryTreeLevel {


    //    Definition for a binary tree node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Map<Integer, Node> ret = new HashMap<>();
        if (root != null)
            connectImpl(root, 0, ret);
        return root;
    }


    private void connectImpl(Node root, int i, Map<Integer, Node> ret) {
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
            connectNextNode(root.left, i, ret);
            ret.put(i, root.right);
        } else if (root.left == null && root.right != null) {
            connectNextNode(root.right, i, ret);
            ret.put(i, root.right);
        } else if (root.left != null && root.right == null) {
            connectNextNode(root.left, i, ret);
            ret.put(i, root.left);
        } else {

        }
        if (root.left != null) {
            connectImpl(root.left, i + 1, ret);
        }
        if (root.right != null) {
            connectImpl(root.right, i + 1, ret);
        }

    }

    private void connectNextNode(Node root, int i, Map<Integer, Node> ret) {
        Node node = ret.get(i);
        if (node != null) {
            node.next = root;
        }
    }

    public Node connectWrong(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        Node cur = null, pre = null;
        while ((cur = queue.poll()) != null) {
            if (pre != null) {
                pre.next = cur;
            }
            pre = cur;
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);

        }
        return root;
    }
}
