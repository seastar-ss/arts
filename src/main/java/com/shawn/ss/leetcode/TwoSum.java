package com.shawn.ss.leetcode;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if(nums==null || nums.length<2) return null;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0, n = nums.length; i < n; ++i) {
            map.put(nums[i], i);
        }
        for (int i = 0, n = nums.length; i < n; ++i) {
            Integer k = map.get(target - nums[i]);
            if (k != null && k!=i) {
                return new int[]{i, k};
            }
        }
        return null;
    }

}
