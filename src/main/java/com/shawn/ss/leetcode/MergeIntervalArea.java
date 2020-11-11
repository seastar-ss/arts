package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervalArea {

    public static void main(String[] args) {
        int[][] insert = new MergeIntervalArea().insert(
                //                new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                new int[][]{{1, 3}},
                new int[]{4, 5}
        );
        print(insert);
    }

    private static void print(int[][] merge) {
        for (int i = 0; i < merge.length; ++i) {
            int[] m = merge[i];
            for (int j = 0; j < m.length; ++j) {
                System.out.print(" " + m[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) return intervals;
        if (intervals == null || intervals.length == 0) return new int[][]{newInterval};
        int len = intervals.length;
        int count = 0;
        int idx = Arrays.binarySearch(intervals, newInterval, (o1, o2) -> o1[0] - o2[0]);
        System.out.println(idx);
        List<int[]> list = new ArrayList<>();
        if (idx >= 0) {
            int[] inserted = intervals[idx];

            if (newInterval[1] <= inserted[1]) {
                return intervals;
            } else {
                //                int[][] ret = new int[len + 1][2];
                //                if (inserted[1] < newInterval[1]) {
                inserted[1] = newInterval[1];
                //                }
                for (int i = idx + 1; i < len; ++i) {
                    if (newInterval[1] >= intervals[i][0]) {
                        if (newInterval[1] <= intervals[i][1]) {
                            inserted[1] = intervals[i][1];
                            intervals[i][0] = Integer.MIN_VALUE;
                            intervals[i][1] = Integer.MAX_VALUE;
                            break;
                        } else {
                            intervals[i][0] = Integer.MIN_VALUE;
                            intervals[i][1] = Integer.MAX_VALUE;
                        }
                    } else {
                        inserted[1] = newInterval[1];
                        break;
                    }
                }

                for (int[] i : intervals) {
                    if (i[0] != Integer.MIN_VALUE || i[1] != Integer.MAX_VALUE) {
                        list.add(i);
                    }
                }

            }
        } else {
            //            int[][] ret = new int[len + 1][2];
            idx = -idx - 1;
            int i = idx;
            boolean flag = false;
            if (idx > 0) {
                //                System.arraycopy(intervals, 0, ret, 0, idx);
                list.addAll(Arrays.asList(intervals).subList(0, idx));
                if (newInterval[0] > intervals[idx - 1][1]) {
                    list.add(newInterval);
                    //                    flag = true;
                    //                    i = idx;
                } else {
                    //                    flag = false;
                    if (intervals[idx - 1][1] < newInterval[1])
                        list.get(list.size() - 1)[1] = newInterval[1];
                    else
                        flag = true;
                    //                    idx = idx - 1;
                }

            } else {
                //                flag = true;
                list.add(newInterval);
            }
            boolean contain = true;
            if (!flag) {
                for (; i < len; ++i) {
                    int i1 = list.size() - 1;
                    if (newInterval[1] < intervals[i][0]) {
                        list.get(i1)[1] = newInterval[1];
                        contain = true;
                        break;
                    } else {
                        if (newInterval[1] <= intervals[i][1]) {
                            list.get(i1)[1] = intervals[i][1];
                            contain = false;
                            break;
                        } else {

                        }
                    }
                }
            }
            int ii = i + (contain ? 0 : 1);
            if (ii < len) {
                list.addAll(Arrays.asList(intervals).subList(ii, len));
                //                System.arraycopy(intervals, ii, ret, idx + (flag ? 1 : 0), len - ii);
            }

        }
        return list.toArray(new int[list.size()][]);
    }
}
