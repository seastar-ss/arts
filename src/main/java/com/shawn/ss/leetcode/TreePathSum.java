package com.shawn.ss.leetcode;
/*
https://leetcode-cn.com/problems/path-sum/
 */
public class TreePathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return travelNode(root, sum, 0);
    }

    private boolean travelNode(TreeNode root, int sum, int cur) {
        int newVal = cur + root.val;
        if (root.left == null && root.right == null) {
            return newVal == sum;
        }
        if (root.left != null) {
            if (travelNode(root.left, sum, newVal)) return true;
        }
        if (root.right != null) {
            if (travelNode(root.right, sum, newVal)) return true;
        }
        return false;
    }
}
