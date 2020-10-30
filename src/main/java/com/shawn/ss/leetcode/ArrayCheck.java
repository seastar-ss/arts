package com.shawn.ss.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode-cn.com/problems/unique-number-of-occurrences/
 */
public class ArrayCheck {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) return true;
        Map<Integer, Integer> count = new HashMap<>();
        //
        for (int i : arr) {
            Integer present = count.computeIfPresent(i, (k, v) -> v + 1);
            if (present == null) {
                present = 1;
                count.put(i, present);
            }
        }
        Set<Integer> occurence = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer k = entry.getKey();
            Integer v = entry.getValue();
            if (occurence.contains(v)) return false;
            occurence.add(v);
        }
        return true;
    }
}
