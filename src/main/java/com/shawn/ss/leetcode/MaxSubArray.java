package com.shawn.ss.leetcode;

public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{-1, -5, -3, -4, -7, -2, -10, -5, -4}));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = nums[0];
        int ret=dp[0];
        for (int i = 1; i < n; ++i) {
            dp[i] = dp[i - 1] > 0 ? nums[i] + dp[i - 1] : nums[i];
            ret = Math.max(dp[i], ret);
        }
        return ret;
    }
}
