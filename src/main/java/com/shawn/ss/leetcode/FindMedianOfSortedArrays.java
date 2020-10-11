package com.shawn.ss.leetcode;


import java.util.Arrays;

public class FindMedianOfSortedArrays {

    public static void main(String[] args) {
        System.out.println(new FindMedianOfSortedArrays().findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{3, 4, 5, 6}
        ));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double ret = 0;
        if (nums1 == null || nums1.length == 0) {
            if (nums2 == null || nums2.length == 0) {
                throw new IllegalStateException("both arrays are empty");
            } else {
                ret = findMid(nums2, 0, nums2.length);
            }
        } else if (nums2 == null || nums2.length == 0) {
            ret = findMid(nums1, 0, nums1.length);
        } else {
            //            ret = getMidByMerge(nums1, nums2);
            ret = getMidByBS(nums1, nums2);
        }
        return ret;
    }

    private double getMidByBS(int[] nums1, int[] nums2) {
        double ret = 0.0;
        int n = nums1.length;
        int m = nums2.length;
        boolean f = (n + m) % 2 == 1;
        int k = f ? (n + m - 1) / 2 : (n + m) / 2;

        int i = n / 2;
        int j = m / 2;
        int pos1 = 0, pos2 = 0;

        do {
            pos1 = bSearch(nums1[i], nums2);
            pos2 = i - 1;
            if (pos1 + pos2 < k) {

            } else if (pos1 + pos2 > k) {

            }
        } while (pos1 + pos2 == k);

        return ret;
    }

    private int bSearch(int target, int[] nums) {
        return Math.abs(Arrays.binarySearch(nums, target));
    }

    private double getMidByMerge(int[] nums1, int[] nums2) {
        double ret;
        int n = nums1.length;
        int m = nums2.length;
        int[] merged = new int[m + n];
        int i = 0;
        int j = 0;
        while (i < n || j < m) {

            if (i < n && j < m && nums1[i] < nums2[j]) {
                merged[i + j] = nums1[i];
                ++i;
            } else if (i < n && j < m && nums1[i] >= nums2[j]) {
                merged[i + j] = nums2[j];
                ++j;
            } else if (i == n) {
                merged[i + j] = nums2[j];
                ++j;
            } else if (j == m) {
                merged[i + j] = nums1[i];
                ++i;
            }
        }
        ret = findMid(merged, 0, n + m);
        return ret;
    }

    private double findMid(int[] nums, int start, int length) {
        if ((length - start) % 2 == 0) {
            int i = (length - start) / 2;
            return (nums[i] + nums[i - 1]) / 2.0;
        } else {
            int i = (length - start - 1) / 2;
            return nums[i];
        }
        //        return 0;
    }
}
