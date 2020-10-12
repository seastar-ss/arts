package com.shawn.ss.leetcode;

/**
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 */
public class MininumDifferenceOfBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);


    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int ret = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        travelTree(root);
        return ret;
    }

    private void travelTreeV2(TreeNode root) {

    }

    private int[] travelTree(TreeNode root) {
//        if (root.left != null) {
//            int val = root.val - root.left.val;
//            if (val < ret) ret = val;
//        }
//        if (root.right != null) {
//            int val = root.right.val - root.val;
//            if (val < ret) ret = val;
//        }
        int[] nodeVal = {-1, -1};
        int[] nodeValLeft = null, nodeValRight = null;
        if (root.left != null) {
            nodeValLeft = travelTree(root.left);
            int val = root.val - nodeValLeft[1];
            if (val < ret) ret = val;
            nodeVal[0] = nodeValLeft[0];
        }
        if (root.right != null) {
            nodeValRight = travelTree(root.right);
            int val = nodeValRight[0] - root.val;
            if (val < ret) ret = val;
            nodeVal[1] = nodeValRight[1];
        }
        if (nodeVal[0] < 0) nodeVal[0] = root.val;
        if (nodeVal[1] < 0) nodeVal[1] = root.val;
        return nodeVal;
    }
}
