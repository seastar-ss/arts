package com.shawn.ss.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class ArrayCheckV2 {

    public static void main(String[] args) {
        int i = new ArrayCheckV2().longestConsecutive(new int[]{
                -6, 6, -9, -7, 0, 3, 4, -2, 2, -1, 9, -9, 5, -3, 6, 1, 5, -1, -2, 9, -9, -4, -6, -5, 6, -1, 3
        });
        System.out.println(i);
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer[]> map = new HashMap<>();
        int maxVal = 0;
        for (int i : nums) {
            if (map.containsKey(i)) continue;
            Integer[] ints = null;
            Integer[] high = i < Integer.MAX_VALUE ? map.get(i + 1) : null;
            Integer[] low = i > Integer.MIN_VALUE ? map.get(i - 1) : null;
            if (high != null && low != null) {
                ints = new Integer[]{
                        low[0], high[1]
                };
            } else if (high == null && low != null) {
                ints = low;
                ints[1] = i;
            } else if (high != null && low == null) {
                ints = high;
                ints[0] = i;
            } else if (high == null && low == null) {
                ints = new Integer[]{i, i};
            }
            int len = ints[1] - ints[0] + 1;
            //System.out.println("arr:" + ints[0] + " " + ints[1] + " len " + len);
            if (maxVal < len) {
                maxVal = len;
            }
            map.put(ints[0], ints);
            if (!ints[1].equals(ints[0])) map.put(ints[1], ints);
            if (!map.containsKey(i)) map.put(i, null);
        }
        return maxVal;
    }
}
