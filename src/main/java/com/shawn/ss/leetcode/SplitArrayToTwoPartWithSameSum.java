package com.shawn.ss.leetcode;

import java.util.Arrays;

public class SplitArrayToTwoPartWithSameSum {

    public static void main(String[] args) {
        //        testIndex();
        testSplit();
    }

    private static void testSplit() {
        //[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97]
        //        int[] index = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97, 99, 97};
        int[] index = {1, 2, 5};
        boolean b = new SplitArrayToTwoPartWithSameSum().canPartition(index);
        System.out.println(b);
    }

    private static void testIndex() {
        int[] index = {6, 5, 4, 3};
        for (int i = 0; i < 40; ++i) {
            boolean b = new SplitArrayToTwoPartWithSameSum().reduceIndex(index);
            System.out.println("ret:" + b + " " + Arrays.toString(index));
        }
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        Arrays.sort(nums);
        int length = nums.length;
        int sumAll = getSumAll(nums, 0, length);
        //        int end = length / 2 + 1;
        if (sumAll % 2 != 0) return false;

        int sumTarget = sumAll / 2;
        if (nums[length - 1] > sumTarget) return false;
        boolean[][] dp = new boolean[length][sumTarget + 1];
        for (int i = 0; i < length; ++i) {
            dp[i][0] = true;
        }
        for (int i = 0; i < length; ++i) {
            int num = nums[i];
            if (i > 0) {
                for (int j = 0; j <= sumTarget; ++j) {
                    if (j < num) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                    }
                }
            } else {
                dp[i][num] = true;
            }
        }
        return dp[length - 1][sumTarget];
    }

    public boolean canPartitionBrute(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        Arrays.sort(nums);
        int length = nums.length;
        int sumAll = getSumAll(nums, 0, length);
        int end = length / 2 + 1;
        if (sumAll % 2 != 0) return false;
        int sumTarget = sumAll / 2;
        int start = sumTarget / nums[length - 1];
        for (int i = start; i < end; ++i) {

            if (testSumMeet(nums, i, sumTarget)) {
                return true;
            }
        }
        return false;
    }

    private boolean testSumMeet(int[] nums, int size, int sumAll) {
        int sum = 0;
        int[] index = new int[size];
        for (int i = 0; i < size; ++i) {
            index[i] = nums.length - 1 - i;
        }
        //        int[] n = new int[size];
        boolean flag = true;
        do {
            sum = getSumAll(nums, index);
            if (sum == sumAll) {
                //                System.out.println(Arrays.toString(nums));
                //                System.out.println(Arrays.toString(index));
                return true;
            } //else if (sum*2 < sumAll) return false;
            flag = reduceIndex(index);
        } while (flag);
        return false;
    }

    private boolean reduceIndex(int[] index) {
        int nn = index.length - 1;
        int i = nn;
        int step = 0;
        //        boolean flag=false;
        while (i >= 0) {
            index[i] = index[i] - 1;
            if (index[i] >= (nn - i)) {
                if (step > 0) {
                    for (int j = i + 1; j <= nn; ++j) {
                        index[j] = index[i] - (j - i);
                    }
                }
                return true;
            } else {
                step = 1;
                i--;
            }
        }
        return false;
    }

    private int getSumAll(int[] nums, int[] index) {
        int sum = 0;
        for (int i : index) {
            sum += nums[i];
        }
        return sum;
    }

    private int getSumAll(int[] nums, int start, int length) {
        int sum = 0;
        for (int i = start; i < length; ++i) {
            sum += nums[i];
        }
        return sum;
    }
}
