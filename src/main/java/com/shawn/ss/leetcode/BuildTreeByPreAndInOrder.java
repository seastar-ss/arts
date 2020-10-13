package com.shawn.ss.leetcode;

import java.util.TreeMap;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class BuildTreeByPreAndInOrder {

    public static void main(String[] args) {
        TreeNode treeNode = new BuildTreeByPreAndInOrder().buildTree(
                new int[]{1,2,3},
                new int[]{1,3,2}
        );
        System.out.println(treeNode);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    "\n\t left=" + left +
                    "\n\t right=" + right +
                    '}';
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length)
            return null;
        if (preorder.length == 1 && inorder.length == 1) return new TreeNode(preorder[0]);
        TreeNode root = new TreeNode(preorder[0]);
        int index = findIndex(inorder, preorder[0], 0, inorder.length-1);
        //        root.left=buildTreeImpl(preorder, inorder,  0, 0,index-1);
        //        root.right=buildTreeImpl(preorder,inorder,0,index+1,inorder.length-
        buildTreeImpl(preorder, inorder, root, 0, index, 0, preorder.length - 1);
        return root;
    }

    private TreeNode buildTreeImpl(int[] preorder, int[] inorder, TreeNode root, int rootInPre, int indexInIn, int leftInIn, int rightInIN) {
        if (indexInIn == leftInIn) {
            root.left = null;
        } else if (indexInIn - 1 == leftInIn) {
            root.left = new TreeNode(inorder[leftInIn]);
        } else {
            int leftRootInPre = rootInPre + 1;
            int val = preorder[leftRootInPre];
            TreeNode leftRoot = new TreeNode(val);
            int leftIndex = findIndex(inorder, val, leftInIn, indexInIn);
            buildTreeImpl(preorder, inorder, leftRoot, leftRootInPre, leftIndex, leftInIn, indexInIn - 1);
            root.left = leftRoot;
        }
        if (indexInIn == rightInIN) {
            root.right = null;
        } else if (indexInIn + 1 == rightInIN) {
            root.right = new TreeNode(inorder[rightInIN]);
        } else {
            int rightRootInPre = rootInPre + (indexInIn - leftInIn)+1;
            int val = preorder[rightRootInPre];
            TreeNode rightRoot = new TreeNode(val);
            int rightIndex = findIndex(inorder, val, indexInIn, rightInIN);
            buildTreeImpl(preorder, inorder, rightRoot, rightRootInPre, rightIndex, indexInIn + 1, rightInIN);
            root.right = rightRoot;
        }
        return root;
    }

    public int findIndex(int[] nums, int target, int start, int end) {
        //        int ret = -1;
        for (int i = start; i <= end; ++i) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
