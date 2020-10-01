package com.shawn.ss.leetcode;

import java.util.*;

public class Permute {

    public static void main(String[] args) {
        List<List<Integer>> permute = new Permute().permute(new int[]{1, 3, 5, 7});
        System.out.println(permute);
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        if (nums.length == 1) return Collections.singletonList(Collections.singletonList(nums[0]));
        int n = nums.length;
        List<List<Integer>> ret = new ArrayList<>(100 * n * (n - 1));
        for (int i = 0; i < n; ++i) {
            int[] visit = new int[n];
            //            visit[i]=1;
            doPermute(nums, 0, i, ret, new Integer[n], visit);
        }
        return ret;
    }

    private void doPermute(int[] nums, int i, int item, List<List<Integer>> ret, Integer[] cur, int[] visit) {
        //        if (i == 0) {
        //            cur = new HashSet<>(nums.length);
        //        }
        int num = nums[item];
        cur[i] = num;
        visit[item] = 1;
        if (i >= nums.length - 1) {
            ret.add(new ArrayList<Integer>(Arrays.asList(cur)));
        } else {
            for (int j = 0; j < nums.length; ++j) {
                //                int num1 = nums[j];
                if (visit[j] == 0) {
                    doPermute(nums, i + 1, j, ret, cur, visit);
                    //                    cur.remove(cur.size() - 1);
                    visit[j] = 0;
                }
            }
        }
    }
}
