package com.shawn.ss.interview_code;

import java.util.HashMap;
import java.util.Map;

public class WYSolution {
    /**
     * input: int[][]
     * 0 nothing
     * 1 something
     * <p>
     * 1 1 1
     * 0 0 0
     * 0 1 0
     *
     * @param args
     */

    public static void main(String[] args) {
        int ret = countArea(new int[][]{
                {1, 0, 0, 0},
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 1, 1}
        });
        System.out.println(ret);
    }

//    static int ret = 0;

    private static int countArea(int[][] ints) {
        Map<Integer, Map<Integer, Integer>> marks = new HashMap<>();

        int ret = 0;
        for (int i = 0, n = ints.length; i < n; ++i) {
            int[] intsI = ints[i];
            for (int j = 0, m = ints.length; j < m; ++j) {
                if (intsI[j] == 1) {
                    Map<Integer, Integer> map = marks.computeIfAbsent(i, e -> new HashMap<>());
                    //                    if (map.size() != 0) {
                    if (!map.containsKey(j)) {
                        map.put(j, ret++);
                        searchNeighbor(ints, i, j, marks, ret, n, m);
                    } else {

                    }
                    //                    } else {

                    //                    }
                }
            }
        }
        return ret;
    }

    private static void searchNeighbor(int[][] ints, int i, int j, Map<Integer, Map<Integer, Integer>> marks, int ret, int n, int m) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : direction) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && ints[ni][nj] == 1) {
                if (putToMap(ni, nj, ret, marks)) {
                    searchNeighbor(ints, ni, nj, marks, ret, n, m);
                } else {
                }
            } else {

            }
        }
    }

    private static boolean putToMap(int ni, int nj, int ret, Map<Integer, Map<Integer, Integer>> marks) {
        Map<Integer, Integer> map = marks.computeIfAbsent(ni, e -> new HashMap<>());
        if (!map.containsKey(nj)) {
            map.put(nj, ret);
            return true;
        } else
            return false;
    }
}
