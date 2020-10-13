package com.shawn.ss.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 */
public class CoinChange {

    public static void main(String[] args) {
        int i = new CoinChange().coinChange(
                new int[]{2, 5, 10, 1}, 27
        );
        System.out.println(i);
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            int dpi = -1;
            for (int c : coins) {
                if (i < c) {

                } else if (dp[i - c] != -1) {
                    if (dpi == -1) {
                        dpi = dp[i - c] + 1;
                    } else if (dpi > dp[i - c] + 1) {
                        dpi = dp[i - c] + 1;
                    }

                }
            }
            dp[i] = dpi;
        }
        return dp[amount];
    }

}
