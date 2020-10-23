package com.shawn.ss.leetcode;

import java.util.*;

public class DevideLongLetters {

    public static void main(String[] args) {
        List<Integer> integers = new DevideLongLetters().partitionLabels("acdabbbbbbccef");
        System.out.println(integers);
    }

    public List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) return Collections.emptyList();
        int length = s.length();
        Map<Character, SortedSet<Integer>> charPos = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        int start = 0, end = length - 1;

        for (int i = 0; i <= end; ++i) {
            char c = s.charAt(i);
            SortedSet<Integer> posSet = charPos.computeIfAbsent(c, e -> new TreeSet<>());
            posSet.add(i);
        }
        while (start < length) {
            int index = start;
            int lastPos = start;
            while (index < end) {
                char c = s.charAt(index);
                SortedSet<Integer> posSet = charPos.get(c);
                int newLsPos = posSet.last();
                if (lastPos < newLsPos) lastPos = newLsPos;
                end = lastPos;
                ++index;
            }
            ans.add(lastPos - start + 1);
            start = lastPos + 1;
            end = length - 1;
        }

        return ans;
    }
}
