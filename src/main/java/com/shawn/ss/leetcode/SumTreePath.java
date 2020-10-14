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

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
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
                if (i != j) {

                }
            }
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; ++i) {
            int td = 0;
            for (int j : dis[i]) {td += j;}
            ret[i] = td;
        }
        return ret;
    }
}
