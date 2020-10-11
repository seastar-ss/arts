package com.shawn.ss.leetcode;

import java.util.Arrays;

public class SortColor {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 2, 2, 1, 1, 0, 2, 0, 1, 0, 0, 0, 2};
        new SortColor().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        sortColorsV2(nums, 0, nums.length - 1);
    }

    private void sortColorsV2(int[] nums, int start, int end) {
        int os = start, oe = end;
        if (start == end) {
            return;
        } else if (end - start == 1) {
            if (nums[start] > nums[end]) {
                swap(nums, start, end);
            }
            return;
        }
        int splitor = findFirst(nums, 1, start, end);
        if (splitor < 0) {
            splitor = findFirst(nums, 2, start, end);
        }
        if (splitor < 0) {
            return;
        }
        while (start < end) {
            while (nums[start] <= nums[splitor] && start < splitor) start++;
            if (splitor != start) {
                swap(nums, splitor, start);
                splitor = start;
                //System.out.println("\t\t" + splitor);
            }
            while (nums[end] >= nums[splitor] && end > splitor) end--;
            if (end != splitor) {
                swap(nums, splitor, end);
                splitor = end;
                //System.out.println("\t\t" + splitor);
            }
        }
        //System.out.println("\t\t" + splitor);
        //System.out.println(Arrays.toString(nums));
        start = os;
        while (nums[start] == 0 && start < oe) start++;
        end = oe;
        while (nums[end] == 2 && end > os) end--;
        if (start < end && (start != os || end != oe)) {
            //System.out.println(" start-end :" + start + " " + end);
            sortColorsV2(nums, start, end);
        }
    }


    private void swap(int[] nums, int splitor, int end) {
        int tmp = nums[splitor];
        nums[splitor] = nums[end];
        nums[end] = tmp;
    }

    private int findFirst(int[] nums, int target, int s, int e) {
        for (int i = s, n = e; i <= n; ++i) {
            if (nums[i] == target) return i;
        }
        return -1;
    }

    private int findLast(int[] nums, int target, int s, int e) {
        for (int i = e, n = s; i >= n; --i) {
            if (nums[i] == target) return i;
        }
        return -1;
    }

    private void sortColors(int[] nums, int start, int end) {
        int os = start, oe = end;
        if (start == end) {
            return;
        } else if (end - start == 1) {
            if (nums[start] > nums[end]) {
                swap(nums, start, end);
            }
            return;
        }
        int splitor = findFirst(nums, 1, start, end);
        if (splitor < 0) {
            splitor = findFirst(nums, 0, start, end);
        }
        if (splitor < 0) {
            return;
        }
        while (start < end) {
            while (nums[start] <= nums[splitor] && start < splitor) start++;
            if (splitor != start) {
                swap(nums, splitor, start);
                splitor = start;
            }
            while (nums[end] >= nums[splitor] && end > splitor) end--;
            if (end != splitor) {
                swap(nums, splitor, end);
                splitor = end;
            }
        }
        System.out.println("\t\t" + start);
        System.out.println(Arrays.toString(nums));
        if (start - 1 > os) {
            sortColors(nums, os, start - 1);
        }
        if (end + 1 < oe) {
            sortColors(nums, end + 1, oe);
        }
    }
}
