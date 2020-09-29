package com.shawn.ss.leetcode;

import javax.swing.tree.TreeNode;
import java.util.*;

public class FindBiggestNumInTreeLevel {


    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //    class Solution {
    //        public List<Integer> largestValues(TreeNode root) {
    //
    //        }
    //    }

    //    Queue<TreeNode> nodeQueue = new ArrayDeque<>();

    //    Map<TreeNode,Integer> ret = new HashMap<>();
    //    TreeNode curRoot;
    public List<Integer> largestValues(TreeNode root) {
        Map<Integer, Integer> ret = new TreeMap<>();
        if (root == null) return Collections.emptyList();
        find(root, 0, ret);
        return new ArrayList<>(ret.values());
    }

    private void find(TreeNode root, int i, Map<Integer, Integer> ret) {

        Integer val = ret.computeIfAbsent(i, e -> root.val);
        if (val < root.val) {
            ret.put(i, root.val);
        }

        if (root.left != null)
            find(root.left, i + 1, ret);
        if (root.right != null)
            find(root.right, i + 1, ret);
    }
}
