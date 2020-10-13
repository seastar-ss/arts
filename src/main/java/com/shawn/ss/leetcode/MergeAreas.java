package com.shawn.ss.leetcode;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;
/**
 * https://leetcode-cn.com/problems/merge-intervals/submissions/
 */
import java.util.*;

public class MergeAreas {

    public static void main(String[] args) {
        int[][] merge = new MergeAreas().merge(
                new int[][]{
                        {1, 3}, {2, 6}, {8, 10}, {15, 18}
                }
        );
        print(merge);
    }

    private static void print(int[][] merge) {
        for (int i = 0; i < merge.length; ++i) {
            int[] m = merge[i];
            for (int j = 0; j < m.length; ++j) {
                System.out.print(" " + m[j]);
            }
            System.out.println();
        }
    }

    SortedMap<Integer, Set<Integer>> head = new TreeMap<>();
    SortedMap<Integer, Set<Integer>> tail = new TreeMap<>();
    int redo = 0;

    public int[][] merge(int[][] intervals) {
        if (intervals == null) return null;
        int n = intervals.length;
        if (n == 0) return new int[0][0];
        if (n == 1) return intervals;
        //        int[] effective = new int[intervals.length];
        //        int count = 0;
        //        int[] pre = intervals[0];

        Set<Integer> mergedArea = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            addItem(intervals[i], i);
        }
        for (int i = 0; i < n; ++i) {
            if (!mergedArea.contains(i)) {
                getAreaContainsPoint(intervals, i, mergedArea);
            }//                effective[i] = 1;
            //                ++count;
        }
        int[][] merged = new int[n - mergedArea.size()][2];
        int index = 0;
        for (int i = 0; i < n; ++i) {
            if (!mergedArea.contains(i)) {
                merged[index] = intervals[i];
                ++index;
            }
        }
        if (redo <= 2 && merged.length > 1) {
            head.clear();
            tail.clear();
            ++redo;
            merged = merge(merged);
        }
        Arrays.sort(merged, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        return merged;
    }

    private void addItem(int[] interval, int i) {
        Set<Integer> headSet = head.computeIfAbsent(interval[0], e -> new HashSet<>());
        headSet.add(i);
        Set<Integer> tailSet = tail.computeIfAbsent(interval[1], e -> new HashSet<>());
        tailSet.add(i);
    }

    private boolean getAreaContainsPoint(int[][] intervals, int index, Set<Integer> mergedArea) {
        int[] range = intervals[index], newRange = new int[]{range[0], range[1]};
        boolean handled = handle(intervals, mergedArea, index, 0, newRange) || handle(intervals, mergedArea, index, 1, newRange);
        if (handled) {
            addItem(newRange, index);
            intervals[index] = newRange;
        }
        return handled;
    }

    private boolean handle(int[][] intervals, Set<Integer> mergedArea, int index, int index2, int[] newRange) {
        int range = intervals[index][index2];
        SortedMap<Integer, Set<Integer>> headMap = head.headMap(range + 1);
        SortedMap<Integer, Set<Integer>> tailMap = tail.tailMap(range);
        boolean handled = false;
        if (headMap.size() > 0 && tailMap.size() > 0) {
            Set<Map.Entry<Integer, Set<Integer>>> entrySet;
            boolean flag = headMap.size() < tailMap.size();
            if (flag)
                entrySet = headMap.entrySet();
            else
                entrySet = tailMap.entrySet();
            Set<Integer> headRemoved = new HashSet<>();
            Set<Integer> tailRemoved = new HashSet<>();
            for (Map.Entry<Integer, Set<Integer>> entry : entrySet) {
                Set<Integer> value = entry.getValue();
                for (Integer i : value) {
                    if (i != index) {
                        if (intervals[i][0] <= range && range <= intervals[i][1]) {
                            headRemoved.add(intervals[i][0]);
                            tailRemoved.add(intervals[i][1]);
                            mergedArea.add(i);
                            if (newRange[0] > intervals[i][0]) newRange[0] = intervals[i][0];
                            if (newRange[1] < intervals[i][1]) newRange[1] = intervals[i][1];
                            handled = true;
                        }
                    }
                }
            }
            for (Integer i : headRemoved) {
                head.remove(i);
            }
            for (Integer i : tailRemoved) {
                tail.remove(i);
            }
        }
        return handled;
    }
}
