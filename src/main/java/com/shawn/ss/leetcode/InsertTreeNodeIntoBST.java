package com.shawn.ss.leetcode;

public class InsertTreeNodeIntoBST {


    //     Definition for a binary tree node.
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

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        doInsert(root, val);
        return root;
    }

    private void doInsert(TreeNode root, int val) {

        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                doInsert(root.left, val);
            }
        } else if (root.val < val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                doInsert(root.right, val);
            }
        } else {
            return;
        }
    }

}
