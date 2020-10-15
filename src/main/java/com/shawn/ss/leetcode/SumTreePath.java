package com.shawn.ss.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/sum-of-distances-in-tree/
 */
public class SumTreePath {

    public static void main(String[] args) {
        int[] distancesInTree = new SumTreePath().sumOfDistancesInTree(6, new int[][]{
                {0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}
        });
        System.out.println(Arrays.toString(distancesInTree));
    }

    private static void print(int[][] array) {
        for (int i = 0; i < array.length; ++i) {
            int[] m = array[i];
            for (int j = 0; j < m.length; ++j) {
                System.out.print(" " + m[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[][] dis = new int[n][n];
        for (int[] path : edges) {
            int start = path[0];
            int end = path[1];
            dis[start][end] = 1;
            dis[end][start] = 1;
        }
        print(dis);
        int[] ret = new int[n];
        for (int i = 0; i < n; ++i) {

        }
        return ret;
    }

    public int[] sumOfDistancesInTreeWrong(int n, int[][] edges) {
        int[][] dis = new int[n][n];
        for (int[] path : edges) {
            int start = path[0];
            int end = path[1];
            dis[start][end] = 1;
            dis[end][start] = 1;
            //            int[] di = dis[end];
            //            for(int j=0;j<n;++j){
            //                if(di[j]>0) dis[start][j]=di[j]+1;
            //            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && dis[i][j] != 0) {
                    //                    int[] disI = dis[i];
                    //                    int[] disJ = dis[j];
                    for (int k = 0; k < n; ++k) {
                        if (dis[i][k] == 0 && k != i && k != j && dis[j][k] > 0) {
                            dis[i][k] = dis[i][j] + dis[j][k];
                            dis[k][i] = dis[i][j] + dis[j][k];
                        }
                    }
                    for (int k = 0; k < n; ++k) {
                        if (dis[j][k] == 0 && k != j && k != i && dis[i][k] > 0) {
                            dis[j][k] = dis[i][j] + dis[i][k];
                            dis[k][j] = dis[i][j] + dis[i][k];
                        }
                    }
                }
                print(dis);
            }
        }
        print(dis);
        int[] ret = new int[n];
        for (int i = 0; i < n; ++i) {
            int td = 0;
            for (int j : dis[i]) {td += j;}
            ret[i] = td;
        }
        return ret;
    }

    private void searchPath(int[][] dis, int i, int j, int n) {
        int[] disI = dis[i];
        for (int k = 0; k < n; ++k) {
            if (disI[k] != 0) {

            }
        }
    }
}
