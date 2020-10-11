package com.shawn.ss.leetcode;

public class ValidBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        else {
            Integer[] range = isValidBSTImpl(root);
            return range != null && range.length == 2 && (range[0] == null || root.val > range[0]) && (range[1] == null || root.val < range[1]);
        }
    }

    private Integer[] isValidBSTImpl(TreeNode root) {
        Integer max = null;
        Integer min = null;
        if (root.left != null) {
            if (root.left.val < root.val) {
                Integer[] impl = isValidBSTImpl(root.left);
                if (impl != null && impl.length == 2) {
                    if (impl[0] != null && impl[0] < root.val) {
                        min = impl[0];
                    } else if (impl[0] == null) {
                        min = root.left.val;
                    } else {
                        return null;
                    }
                    if (impl[1] != null && impl[1] < root.val) {
                        //                        max = impl[1];
                    } else if (impl[1] == null) {
                        //                        max = root.right.val;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
        }
        if (root.right != null) {
            if (root.right.val > root.val) {
                Integer[] impl = isValidBSTImpl(root.right);
                if (impl != null && impl.length == 2) {
                    if (impl[0] != null && impl[0] > root.val) {

                    } else if (impl[0] == null) {

                    } else {
                        return null;
                    }
                    if (impl[1] != null && impl[1] > root.val) {
                        max = impl[1];
                    } else if (impl[1] == null) {
                        max = root.right.val;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
        }
        return new Integer[]{min, max};
    }


    private boolean isValidBSTWrong(TreeNode root, Integer parentVal, Boolean parentLeft) {
        //        if (root != null) {
        boolean leftValid = true;
        if (root.left != null) {
            if (parentLeft == null || parentLeft) {
                leftValid = root.val > root.left.val && isValidBSTWrong(root.left, root.val, true);
            } else {
                leftValid = root.val > root.left.val && root.left.val > parentVal && isValidBSTWrong(root.left, root.val, true);
            }
        }
        boolean rightValid = true;
        if (root.right != null && leftValid) {
            if (parentLeft == null || !parentLeft) {
                rightValid = root.val < root.right.val && isValidBSTWrong(root.right, root.val, false);
            } else {
                rightValid = root.val < root.right.val && root.right.val < parentVal && isValidBSTWrong(root.right, root.val, false);
            }
        }
        return leftValid && rightValid;
        //        }
        //        return true;
    }

}
