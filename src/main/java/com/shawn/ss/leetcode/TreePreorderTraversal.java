package com.shawn.ss.leetcode;
/*
https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreePreorderTraversal {
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

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root==null) return Collections.emptyList();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = null;
        List<Integer> ret = new ArrayList<>();
        while (stack.size()>0) {
            cur = stack.pop();
            ret.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return ret;
    }


}
