package com.shawn.ss.leetcode;

import java.util.*;

public class FourNumAdd {

    public static void main(String[] args) {
        List<List<Integer>> lists = new FourNumAdd().fourSum(new int[]{0, 0, 0, 0, 0, 0, 0}, 0);
        System.out.println(lists);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) return Collections.emptyList();
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        int last = nums[length - 1];
        int first = nums[0];
        if (target <= 4 * last && target >= 4 * first) {
            Map<Integer, Integer> index = new HashMap<Integer, Integer>((int) (length * 1.4));
            for (int i = 0; i < length; ++i) {
                index.put(nums[i], i);
            }
            for (int i = 0; i < length - 3; ++i) {
                if (i == 0 || nums[i] != nums[i - 1]) {
                    int remainV1 = target - nums[i];
                    System.out.println("\t " + remainV1);
                    if (remainV1 <= 3 * last && remainV1 >= 3 * first) {
                        for (int j = i + 1; j < length - 2; ++j) {
                            if (j == i + 1 || nums[j] != nums[j - 1]) {
                                int remainV2 = remainV1 - nums[j];
                                System.out.println("\t\t " + remainV2);
                                if (remainV2 <= 2 * last && remainV2 >= 2 * first) {
                                    for (int k = j + 1; k < length - 1; ++k) {
                                        if (k == j + 1 || nums[k] != nums[k - 1]) {
                                            int remainV3 = remainV2 - nums[k];
                                            System.out.println("\t\t\t " + remainV3);
                                            Integer item = index.get(remainV3);

                                            if (item != null && item > k) {
                                                //                                                SortedSet<Integer> set = item.tailSet(k + 1);
                                                //                                                if (!set.isEmpty()) {
                                                //                                    for(Integer it:set) {
                                                List<Integer> result = new ArrayList<>();
                                                result.add(nums[i]);
                                                result.add(nums[j]);
                                                result.add(nums[k]);
                                                result.add(remainV3);
                                                ret.add(result);
                                                //                                                }
                                                //                                    }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    public List<List<Integer>> fourSumV2(int[] nums, int target) {
        if (nums == null || nums.length < 4) return Collections.emptyList();
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        int last = nums[length - 1];
        int first = nums[0];
        if (target <= 4 * last && target >= 4 * first) {
            Map<Integer, SortedSet<Integer>> index = new HashMap<Integer, SortedSet<Integer>>((int) (length * 1.4));
            for (int i = 0; i < length; ++i) {
                SortedSet<Integer> set = index.computeIfAbsent(nums[i], e -> new TreeSet<>());
                set.add(i);
            }
            for (int i = 0; i < length - 3; ++i) {
                if (i == 0 || nums[i] != nums[i - 1]) {
                    int remainV1 = target - nums[i];
                    System.out.println("\t " + remainV1);
                    if (remainV1 <= 3 * last && remainV1 >= 3 * first) {
                        for (int j = i + 1; j < length - 2; ++j) {
                            if (j == i + 1 || nums[j] != nums[j - 1]) {
                                int remainV2 = remainV1 - nums[j];
                                System.out.println("\t\t " + remainV2);
                                if (remainV2 <= 2 * last && remainV2 >= 2 * first) {
                                    for (int k = j + 1; k < length - 1; ++k) {
                                        if (k == j + 1 || nums[k] != nums[k - 1]) {
                                            int remainV3 = remainV2 - nums[k];
                                            System.out.println("\t\t\t " + remainV3);
                                            SortedSet<Integer> item = index.get(remainV3);

                                            if (item != null) {
                                                SortedSet<Integer> set = item.tailSet(k + 1);
                                                if (!set.isEmpty()) {
                                                    //                                    for(Integer it:set) {
                                                    List<Integer> result = new ArrayList<>();
                                                    result.add(nums[i]);
                                                    result.add(nums[j]);
                                                    result.add(nums[k]);
                                                    result.add(remainV3);
                                                    ret.add(result);
                                                }
                                                //                                    }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
