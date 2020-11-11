package com.shawn.ss.leetcode;

import java.util.Arrays;

public class SortNumByDictionaryOrder {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4};
        SortNumByDictionaryOrder sortNumByDictionaryOrder = new SortNumByDictionaryOrder();
        for (int i = 0; i < 100; i++) {
            sortNumByDictionaryOrder.nextPermutation(a);
            System.out.println(Arrays.toString(a));
        }
    }

    /*
     * 1 2 3 4
     * 1 2 4 3
     * 1 3 2 4
     * 1 3 4 2
     * 1 4 2 3
     * 1 4 3 2
     * 2 1 3 4
     * 2 1 4 3
     *
     * ....
     */
    public void nextPermutation(int[] nums) {

        if (nums == null || nums.length < 2) return;
        boolean shifted = next(nums, 0);
        if (!shifted) {
            Arrays.sort(nums);
        }
    }

    //    int maxIndex = -1;
    //    int minIndex = -1;

    private boolean next(int[] nums, int i) {
        if (i == nums.length - 2) {
            boolean flag = nums[i] < nums[i + 1];
            if (flag) {
                swap(nums, i, i + 1);
            } else {
                //                min=nums[i+1];
                //                minIndex=i+1;
            }
            //            maxIndex = i;
            //            minIndex = i + 1;
            return flag;
        } else {
            if (!next(nums, i + 1)) {
                int index = findSmallestNumLargerThanI(nums, i);
                if (index > 0) {
                    swap(nums, i, index);
                    Arrays.sort(nums, i + 1, nums.length );
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        //        return false;
    }

    private int findSmallestNumLargerThanI(int[] nums, int idx) {
        int ret = -1, num = -1;
        for (int i = idx + 1, n = nums.length; i < n; ++i) {
            if (nums[i] > nums[idx]) {
                if (num < 0 || num > nums[i]) {
                    ret = i;
                    num = nums[i];
                }
            }
        }
        return ret;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
