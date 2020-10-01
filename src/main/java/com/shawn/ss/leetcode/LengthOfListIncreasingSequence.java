package com.shawn.ss.leetcode;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class LengthOfListIncreasingSequence {

    public static void main(String[] args) {
        int i = new LengthOfListIncreasingSequence().lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
        System.out.println(i);
        i = new LengthOfListIncreasingSequence().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        System.out.println(i);
    }

    public int lengthOfLIS(int[] nums) {
        int max = 0;

        int length = nums.length;
        SortedMap<Integer, Integer> map = new TreeMap<>();
        int[] ret = new int[length];
        for (int i = 0, n = length; i < n; ++i) {
            int num = nums[i];
            if (i == 0) {
                ret[i] = 1;
            } else {
                SortedMap<Integer, Integer> headMap = map.headMap(num);
                if (headMap.size() == 0) {
                    ret[i] = 1;
                } else {
                    int maxStep = 0;
                    Set<Map.Entry<Integer, Integer>> entries = headMap.entrySet();
                    for (Map.Entry<Integer, Integer> el : entries) {
                        //                        Integer ml = el.getKey();
                        //                        if (ml < num) {
                        Integer index = el.getValue();
                        if (maxStep < ret[index]) {
                            maxStep = ret[index];
                        }
                        //                        }
                    }
                    ret[i] = maxStep + 1;
                }
            }
            if (max < ret[i]) {
                max = ret[i];
            }
            map.put(num, i);
        }
        return max;
    }
}
