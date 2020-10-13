package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RightViewOfBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        List<Integer> list = new RightViewOfBST().rightSideView(root);
        System.out.println(list);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        travelNode(root, ret, 0, -1);
        return ret;
    }

    private int travelNode(TreeNode root, List<Integer> ret, int level, int rightMaxLevel) {
        if (root != null) {
            if (rightMaxLevel < 0 || level > rightMaxLevel)
                ret.add(root.val);
            int maxLevel = level;
            boolean hasRight = false;
            if (root.right != null) {
                maxLevel = travelNode(root.right, ret, level + 1, rightMaxLevel);
                hasRight = true;
            } else if (root.left != null) {
                maxLevel = travelNode(root.left, ret, level + 1, rightMaxLevel);
                hasRight = false;
            }

            if (root.left != null && hasRight) {
                int leftLevel = travelNode(root.left, ret, level + 1, maxLevel);
                if (leftLevel > maxLevel) maxLevel = leftLevel;
            }
            return maxLevel > rightMaxLevel ? maxLevel : rightMaxLevel;
        } else {
            return level - 1;
        }
    }
}
