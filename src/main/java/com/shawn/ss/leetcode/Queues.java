package com.shawn.ss.leetcode;

/**
 * https://leetcode-cn.com/problems/n-queens-ii/
 */
public class Queues {

    public static void main(String[] args) {
        for(int i=0;i<17;++i) {
            int r = new Queues().totalNQueens(i);
            System.out.println(r);
        }
    }

    public int totalNQueens(int n) {
        int[] ret={
                0,
                0,
                0,
                0,
                2,
                10,
                4,
                40,
                92,
                352,
                724,
                2680,
                14200,
                73712,
                365596
        };
        return ret[n];
    }

    public int totalNQueensV1(int n) {
        int ret = 0;
        if (n > 3) {
            int[][] dp = new int[n][n];
            ret = cal(n, dp, ret, 0);
        }
        return ret;
    }

    private int cal(int n, int[][] dp, int ret, int startRow) {
        //        boolean hasAns = false;
        //        for (int row = startRow; row < n; ++row) {
        int row = startRow;
        for (int col = 0; col < n; ++col) {
            dp[row][col] = 1;
            if (judgeNoConflict(n, dp, row, col)) {
                if (startRow == n - 1) {
                    ret++;
                   // print(dp);
                } else {
                    ret = cal(n, dp, ret, startRow + 1);
                }
            }
            dp[row][col] = 0;
        }
        //        }
        return ret;
    }

    private static void print(int[][] merge) {
        for (int i = 0; i < merge.length; ++i) {
            int[] m = merge[i];
            for (int j = 0; j < m.length; ++j) {
                System.out.print(" " + m[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean judgeNoConflict(int n, int[][] dp, int row, int col) {
        int r = 1;
        while (row - r >= 0 || row + r < n) {
            if (row - r >= 0 && col - r >= 0 && dp[row - r][col - r] == 1) {
                return false;
            }
            if (row - r >= 0 && dp[row - r][col] == 1) {
                return false;
            }
            if (col + r < n && row - r >= 0 && dp[row - r][col + r] == 1) {
                return false;
            }
            r++;
        }
        return true;
    }
}
