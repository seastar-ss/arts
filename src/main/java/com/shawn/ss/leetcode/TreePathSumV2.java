package com.shawn.ss.leetcode;

/*
https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 */
public class TreePathSumV2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return travelNode(root, 0, new StringBuilder());
    }

    private int travelNode(TreeNode root, int sum, StringBuilder cur) {
        cur.append(root.val);
        if (root.left == null && root.right == null) {
            sum += Integer.parseInt(cur.toString());
        } else {
            if (root.left != null) {
                sum = travelNode(root.left, sum, cur);
            }
            if (root.right != null) {
                sum = travelNode(root.right, sum, cur);
            }
        }
        cur.deleteCharAt(cur.length() - 1);
        return sum;
    }
}
