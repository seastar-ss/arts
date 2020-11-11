package com.shawn.ss.leetcode;

public class ArraySegmentSum {
    public int countRangeSumV(int[] nums, int lower, int upper) {
        if (lower > upper) return 0;
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            long sum = 0L;
            for (int j = i; j < n; ++j) {
                sum += nums[j];
                if (sum >= lower && sum <= upper) {
                    ret++;
                }
            }
        }
        return ret;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (lower > upper) return 0;
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int ret = 0;

        return ret;
    }
}
