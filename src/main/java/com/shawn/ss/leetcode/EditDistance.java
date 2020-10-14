package com.shawn.ss.leetcode;

/*
https://leetcode-cn.com/problems/edit-distance/
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null  || word2 == null ) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; ++i) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; ++i) {
            char c = word2.charAt(i - 1);
            for (int j = 1; j <= n; ++j) {
                char b = word1.charAt(j - 1);
                if (c == b) {
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }
        //        int ret = 0;
        return dp[m][n];
    }

    public int min(int... args) {
        int ret = Integer.MAX_VALUE;
        for (int i : args) {
            if (ret > i) ret = i;
        }
        return ret;
    }
}
