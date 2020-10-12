package com.shawn.ss.leetcode;

import java.util.Stack;

public class TrapTheDrops {

    public static void main(String[] args) {
        int maxArea = new TrapTheDrops().maxArea(new int[]{1, 8, 9, 6, 2, 5, 4, 8, 3, 7, 5, 6});
        System.out.println(maxArea);
    }

    public int trap(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int n = height.length;
        int ret = 0;
        Stack<Integer> startIdx = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int h = height[i];

        }
        return ret;
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            throw new IllegalArgumentException("wrong array");
        }
        int n = height.length - 1;

        int start = 0;
        int end = n;
        int ret = 0;
        while (start <= end) {
            int startLen = height[start];
            int endLen = height[end];
            boolean side = startLen > endLen;
            int cur = (side ? endLen : startLen) * (end - start);
            if (cur > ret) {
                ret = cur;
            }
            if (side) {
                do {
                    end--;
                } while (start < end && height[end] < endLen);
            } else {
                do {
                    start++;
                } while (start < end && height[start] < startLen);
            }
        }
        return ret;
    }
}
