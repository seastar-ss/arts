package com.shawn.ss.leetcode;

import java.util.*;

public class FindKLargestNum {

    public static void main(String[] args) {
        //        [3,2,3,1,2,4,5,5,6]
        //        4
        int kthLargest = new FindKLargestNum().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || nums.length < k) throw new IllegalArgumentException("wrong parameter");
        //        int[] array = new int[k];
        //        Arrays.fill(array, Integer.MIN_VALUE);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int total=0;
        for (int n = nums.length, i = 0; i < n; ++i) {
            int num = nums[i];
            if (total < k) {
                putIntToMap(map, num);
                total++;
            } else {
                NavigableMap<Integer, Integer> headSet = map.headMap(num, false);
                if (!headSet.isEmpty()) {
                    putIntToMap(map, num);
                    map.computeIfPresent(map.firstKey(), (key, v) -> (v - 1 > 0 ? v - 1 : null));
                }
            }
        }
        return map.firstKey();
    }

    private void putIntToMap(TreeMap<Integer, Integer> map, int num) {
        Integer val = map.computeIfPresent(num, (kk, e) -> e + 1);
        if (val == null) map.put(num, 1);
    }

    public int findKthLargestDistinct(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || nums.length < k) throw new IllegalArgumentException("wrong parameter");
        //        int[] array = new int[k];
        //        Arrays.fill(array, Integer.MIN_VALUE);
        TreeSet<Integer> set = new TreeSet<>();
        for (int n = nums.length, i = 0; i < n; ++i) {
            int num = nums[i];
            if (set.size() < k) {
                set.add(num);
            } else {
                NavigableSet<Integer> headSet = set.headSet(num, false);
                if (!headSet.isEmpty()) {
                    set.add(num);
                    set.remove(set.first());
                }
            }
        }
        return set.first();
    }
}
