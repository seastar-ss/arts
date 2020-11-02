package com.shawn.ss.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUnion {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        Set<Integer> ret = new HashSet<>();
        Set<Integer> test = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        for (int i : nums2) {
            if (test.contains(i)) ret.add(i);
        }
        return ret.stream().mapToInt(e -> e).toArray();
    }
}
