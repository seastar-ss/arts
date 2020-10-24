package com.shawn.ss.leetcode;
/**
 * https://leetcode-cn.com/problems/video-stitching/submissions/
 */

import java.util.*;

public class VideoStitching {

    public static void main(String[] args) {
        int i = new VideoStitching().videoStitching(
                new int[][]{
                        //                        {0,2},{4,6},{8,10},{1,9},{1,5},{5,9}
                        //                        {0,2},{8,10},{5,9}
                        {0, 1000}, {6000, 8000}, {0, 2000}, {5000, 6000}, {0, 4000}, {0, 3000}, {6000, 7000}, {1000, 3000},
                        {4000, 7000}, {1000, 4000}, {2000, 5000}, {2000, 6000}, {3000, 4000}, {4000, 5000}, {5000, 7000}, {6000, 9000}
                },
                9000
        );
        System.out.println(i);
    }

    public int videoStitching(int[][] clips, int t) {
        //        int ret = 0;
        if (clips == null || clips.length == 0) return -1;
        if (t <= 0) return t < 0 ? -1 : 0;
        int n = clips.length;

        //        int[] dp = new int[t + 1];
        int maxEnd = 0;
        TreeMap<Integer, Integer> startPos = new TreeMap<>();
        TreeMap<Integer, Integer> endPos = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            int[] clip = clips[i];
            addPos(clips, startPos, i, true);
            addPos(clips, endPos, i, false);
            if (maxEnd < clip[1]) {
                maxEnd = clip[1];
            }
            //            Set<Integer> set1 = endPos.computeIfAbsent(clip[1], e -> new TreeSet<>());
            //            set1.add(len);
        }
        if (!startPos.containsKey(0) || t > maxEnd) return -1;
        int start = 0;
        int end = t;
        Set<Integer> selectedClip = new HashSet<>();
        while (start < end) {
            NavigableMap<Integer, Integer> headMap = startPos.headMap(start, true);
            if (headMap.size() == 0) return -1;
            start = searchMaxLen(headMap, clips, true, selectedClip);
            if (start >= end) break;
            NavigableMap<Integer, Integer> tailMap = endPos.tailMap(end, true);
            if (tailMap.size() == 0) return -1;
            end = searchMaxLen(tailMap, clips, false, selectedClip);
        }

        return selectedClip.size();
    }

    private void addPos(int[][] clips, TreeMap<Integer, Integer> map, int i, boolean start) {
        int pos = start ? 0 : 1;
        int[] clip = clips[i];
        int len = clip[1] - clip[0];
        //            Set<Integer> set = startPos.computeIfAbsent(clip[0], e -> new TreeSet<>());
        //            set.add(len);
        int key = clip[pos];
        if (map.containsKey(key)) {
            Integer oId = map.get(key);
            int oLen = clips[oId][1] - clips[oId][0];
            if (len > oLen) map.put(key, i);
        } else {
            map.put(key, i);
        }
    }

    private int searchMaxLen(NavigableMap<Integer, Integer> headMap, int[][] clips, boolean start, Set<Integer> selectedClip) {
        Set<Map.Entry<Integer, Integer>> entries = headMap.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        int pos = start ? 1 : 0;
        int ret = start ? 0 : Integer.MAX_VALUE;
        Integer selectedId = null;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            //            Integer pos = entry.getKey();
            Integer id = entry.getValue();
            int end = clips[id][pos];
            if (start) {
                if (ret < end) {
                    ret = end;
                    //                    key = entry.getKey();
                    selectedId = id;
                }
            } else {
                if (ret > end) {
                    ret = end;
                    //                    key = entry.getKey();
                    selectedId = id;
                }
            }
            iterator.remove();
        }
        selectedClip.add(selectedId);
        //        headMap.remove(key);
        return ret;
    }
}
