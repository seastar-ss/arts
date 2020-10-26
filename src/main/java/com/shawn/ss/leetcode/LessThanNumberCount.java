package com.shawn.ss.leetcode;
/**
 * https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */

import java.util.*;

public class LessThanNumberCount {

    public static void main(String[] args) {
        int[] ints = new LessThanNumberCount().smallerNumbersThanCurrent(new int[]{1, 2, 5, 3, 4, 7, 7, 7, 7, 9});
        System.out.println(Arrays.toString(ints));
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        if (nums.length == 1) return new int[]{0};
        int[] result = new int[nums.length];
        int[] mark = new int[101];
        int[] link = new int[101];
        int[] newNum = new int[nums.length];
        System.arraycopy(nums, 0, newNum, 0, nums.length);
        Arrays.sort(newNum);
        int lastNum = -1;
        for (int i = 0, n = newNum.length; i < n; ++i) {
            int num = newNum[i];
            mark[num] = mark[num] == 0 ? (lastNum >= 0 ? mark[lastNum] + 1 : 1) : (mark[num] + 1);
            if (num != lastNum) {
                link[num] = lastNum;
                lastNum = num;
            } else {

            }
        }
        for (int i = 0, n = nums.length; i < n; ++i) {
            int num = nums[i];
            if (link[num] >= 0) result[i] = mark[link[num]];
            else result[i] = 0;
        }
        return result;
    }

    public int[] smallerNumbersThanCurrentV2(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        if (nums.length == 1) return new int[]{0};
        int[] result = new int[nums.length];
        int[] newNum = new int[nums.length];
        System.arraycopy(nums, 0, newNum, 0, nums.length);
        Arrays.sort(newNum);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : newNum) {
            //            Integer present = map.computeIfPresent(i, (k, v) -> v + 1);
            //            if (present == null) map.put(i, 1);
            //            int cur = map.getOrDefault(i, 0) + 1;
            int cur = 1;
            if (map.containsKey(i)) {
                cur = map.get(i) + 1;
            } else {
                NavigableMap<Integer, Integer> headMap = map.headMap(i, false);
                if (headMap.size() > 0) {
                    Map.Entry<Integer, Integer> entry = headMap.lastEntry();
                    cur += entry.getValue();
                }
            }
            map.put(i, cur);
        }
        for (int i = 0, n = nums.length; i < n; ++i) {
            NavigableMap<Integer, Integer> headMap = map.headMap(nums[i], false);
            if (headMap.size() == 0) result[i] = 0;
            else result[i] = headMap.lastEntry().getValue();
        }
        return result;
    }
}
