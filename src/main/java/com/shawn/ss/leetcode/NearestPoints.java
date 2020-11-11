package com.shawn.ss.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NearestPoints {
    public int[][] kClosest(int[][] points, int K) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1]);
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(comparator);
        for (int[] point : points) {
            //            int[] peek = queue.peek();
            queue.offer(point);
            if (queue.size() > K) {
                queue.poll();
            }
        }
        return queue.toArray(new int[queue.size()][]);
    }
}
