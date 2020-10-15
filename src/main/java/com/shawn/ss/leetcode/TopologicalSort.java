package com.shawn.ss.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode-cn.com/problems/parallel-courses/
 */
public class TopologicalSort {

    public static void main(String[] args) {
        int i = new TopologicalSort().minimumSemesters(3, new int[][]{
                {3, 1}, {2, 3}, {1, 2}
        });
        System.out.println(i);
    }

    public int minimumSemesters(int n, int[][] relations) {
        int ret = 0;
        Map<Integer, Set<Integer>> dependencies = new HashMap<>();
        Set<Integer> done = new HashSet<>();
        for (int[] relation : relations) {
            Set<Integer> set = dependencies.computeIfAbsent(relation[1], e -> new HashSet<>());
            set.add(relation[0]);
        }
        //        int preDone = -1;
        while (done.size() != n) {
            Set<Integer> cur = new HashSet<>();
            for (int i = 1; i <= n; ++i) {
                if(!done.contains(i)) {
                    Set<Integer> set = dependencies.get(i);
                    if (set != null && set.size() > 0) {
                        if (done.containsAll(set)) {
                            cur.add(i);
                        }
                    } else {
                        cur.add(i);
                    }
                }
            }
            if (cur.size() > 0) {
                done.addAll(cur);
                cur.clear();
                ret++;
            } else {
                ret = -1;
                break;
            }
            //            preDone = done.size();
        }
        return ret;
    }
}
