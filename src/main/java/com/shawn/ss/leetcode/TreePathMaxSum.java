package com.shawn.ss.leetcode;

/*
https://leetcode-cn.com/problems/path-sum/
 */
public class TreePathMaxSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        travelNode(root);
        return max;
    }

    private int travelNode(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (root.val > max) max = root.val;
            return root.val;
        }
        int leftVal = 0, rightVal = 0;
        if (root.left != null) {
            leftVal = travelNode(root.left);
        }
        if (root.right != null) {
            rightVal = travelNode(root.right);
        }
        int curVal = leftVal > rightVal ? leftVal : rightVal;
        curVal = curVal > 0 ? root.val + curVal : root.val;
        int curMax = root.val + (leftVal > 0 ? leftVal : 0) + (rightVal > 0 ? rightVal : 0);
        if (curMax > max) max = curMax;
        return curVal;
    }
}
