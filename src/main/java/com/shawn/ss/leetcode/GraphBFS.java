package com.shawn.ss.leetcode;
/**
 * https://leetcode-cn.com/problems/chuan-di-xin-xi/
 */

import java.util.*;

public class GraphBFS {


    public int numWays(int n, int[][] relation, int k) {
        if (n == 0 || relation == null || relation.length == 0) return 0;
        //        int[][] edges = new int[n][n];
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        for (int[] rel : relation) {
            Set<Integer> set = edges.computeIfAbsent(rel[0], e -> new HashSet<>());
            set.add(rel[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int ret = 0;
        int step = 0;
        queue.offer(0);
        //        Integer startPoint;
        while (step < k) {
            int size = queue.size();
            if (size == 0) {
                ret = 0;
                break;
            }
            while (size-- > 0) {
                Integer st = queue.poll();
                Set<Integer> set = edges.get(st);
                if(set!=null && set.size()>0) {
                    queue.addAll(set);
                    if (step == k - 1) {
                        if (set.contains(n - 1)) {
                            ret++;
                        }
                    }
                }
            }
            ++step;
        }
        return ret;
    }
}
