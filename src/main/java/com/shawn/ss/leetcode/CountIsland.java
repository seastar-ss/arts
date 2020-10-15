package com.shawn.ss.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/number-of-islands/
 * https://leetcode-cn.com/problems/max-area-of-island/
 */
public class CountIsland {

    public static void main(String[] args) {
        int ret;
       /* int ret = new CountIsland().numIslands(new char[][]{
                {'1', '1'}
                //                {'1', '0', '0', '0'},
                //                {'0', '1', '1', '0'},
                //                {'1', '0', '1', '1'},
                //                {'1', '1', '1', '1'}
        });
        System.out.println(ret);*/
        ret = new CountIsland().maxAreaOfIsland(new int[][]{
                //                {1, 1}
                {1, 1, 1, 0,1},
                {0, 1, 1, 1,1},
                {1, 0, 1, 1,0},
                {1, 1, 1, 1,0}
        });
        System.out.println(ret);
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;
        if (grid[0] == null || grid[0].length == 0) return 0;
        int m = grid[0].length;
        int[][] marks = new int[n][m];

        int ret = 0;
        for (int i = 0; i < n; ++i) {
            int[] intsI = grid[i];
            for (int j = 0; j < m; ++j) {
                if (intsI[j] == 1) {
                    if (marks[i][j] == 0) {
                        marks[i][j] = 1;
                        int re = searchNeighborArea(grid, i, j, marks, n, m) + 1;
                        if (re > ret) {
                            ret = re;
                        }
                    } else {

                    }

                }
            }
        }
        return ret;
    }

    private int searchNeighborArea(int[][] grid, int i, int j, int[][] marks, int n, int m) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int ret = 0;
        for (int[] d : direction) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && grid[ni][nj] == 1) {
                if (putToMap(ni, nj, 1, marks)) {
                    ret += searchNeighborArea(grid, ni, nj, marks, n, m) + 1;
                } else {
                    //                    ret += 1;
                }
            } else {

            }
        }
        return ret;
    }

    private static boolean putToMap(int ni, int nj, int ret, int[][] marks) {
        if (marks[ni][nj] == 0) {
            marks[ni][nj] = ret;
            return true;
        } else
            return false;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;
        if (grid[0] == null || grid[0].length == 0) return 0;
        int m = grid[0].length;
        int[][] marks = new int[n][m];

        int ret = 1;
        for (int i = 0; i < n; ++i) {
            char[] intsI = grid[i];
            for (int j = 0; j < m; ++j) {
                if (intsI[j] == '1') {
                    if (marks[i][j] == 0) {
                        marks[i][j] = ret++;
                        searchNeighbor(grid, i, j, marks, ret, n, m);
                    } else {

                    }

                }
            }
        }
        return ret - 1;
    }

    private static void searchNeighbor(char[][] ints, int i, int j, int[][] marks, int ret, int n, int m) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : direction) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && ints[ni][nj] == '1') {
                if (putToMap(ni, nj, ret, marks)) {
                    searchNeighbor(ints, ni, nj, marks, ret, n, m);
                } else {
                }
            } else {

            }
        }
    }


}
