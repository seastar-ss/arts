package com.shawn.ss.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/get-kth-magic-number-lcci/
 */
public class KthNumOnlyContainCertainFactorV2 {

    public static void main(String[] args) {
        KthNumOnlyContainCertainFactorV2 factor = new KthNumOnlyContainCertainFactorV2();
        for (int i = 1; i < 50; ++i)
            System.out.println(factor.getKthMagicNumber(i));

    }

    public int getKthMagicNumber(int k) {
        if (k == 1) return 1;
        return getKthMagicNumber(new int[]{2,3, 5, 7,11,17}, k);
    }

    public int getKthMagicNumber(int[] factor, int k) {

        int n = factor.length;
        //        int sn = n + 1;
        int[] dp = new int[k];
        dp[0] = 1;
        if (k > 1) {
            int[] lastItem = new int[n];
            int index = 1;
            while (index < k) {
                int cur = Integer.MAX_VALUE;
                int seedChange = -1;
                for (int j = 0; j < n; ++j) {
                    int re = dp[lastItem[j]] * factor[j];
                    if (re < cur) {
                        cur = re;
                        seedChange = j;
                    }
                }
                if (cur > dp[index - 1]) {
                    dp[index] = cur;
                    ++index;
                }
                lastItem[seedChange]++;
            }

        }
        return dp[k - 1];
    }
}
