package com.shawn.ss.leetcode;
/**
 * https://leetcode-cn.com/problems/greatest-sum-divisible-by-three/
 */

import java.util.PriorityQueue;

public class ArrayMaxSumCanBeDevideByN {

    public static void main(String[] args) {
        int i = new ArrayMaxSumCanBeDevideByN().maxSumDivThree(new int[]{
                3, 6, 5, 1, 8, 20, 1,1
        });
        System.out.println(i);
    }

    public int maxSumDivThree(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ret = maxSumDivN(nums, 3);

        return ret;
    }

    private int maxSumDivN(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k];
        for (int j = 0; j < k; ++j) {
            dp[0][j] = 0;
        }
        boolean[] flag = new boolean[k];
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            //            int r = num % k;
            for (int j = 0; j < k; ++j) {
                int sum = dp[i][j] + num;
                int ii = sum % k;
                flag[ii] = true;
                dp[i + 1][ii] = Math.max(sum, Math.max(dp[i + 1][ii], dp[i][ii]));
            }
            for (int j = 0; j < k; ++j) {
                if (flag[j]) flag[j] = false;
                else dp[i + 1][j] = dp[i][j];
            }
        }
        return dp[n][0];
    }
}
