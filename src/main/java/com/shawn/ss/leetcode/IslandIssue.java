package com.shawn.ss.leetcode;

/**
 * https://leetcode-cn.com/problems/island-perimeter/
 */
public class IslandIssue {

    public static void main(String[] args){
        int i = new IslandIssue().islandPerimeter(
                new int[][]{
                        {0,1,0,0}, {1,1,1,0},{0,1,0,0},{1,1,0,0}
                }
        );
        System.out.println(i);
    }

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;
        int ret = 0;
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < n; i++) {
            int[] line = grid[i];
            if (line == null || line.length == 0) return 0;
            int m = line.length;
            for (int j = 0; j < m; ++j) {
                for (int[] d : direction) {
                    if (grid[i][j] == 1) {
                        int flag = getFromGrid(i + d[0], j + d[1], grid, n, m);
                        if (flag == 0) {
                            ret++;
                        }
                    }
                }
            }
        }
        return ret;
    }

    private int getFromGrid(int i, int j, int[][] grid, int n, int m) {
        if (i < 0 || i >= n) return 0;
        if (j < 0 || j >= m) return 0;
        return grid[i][j];
    }


}
