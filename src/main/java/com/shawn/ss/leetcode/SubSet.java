package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubSet {

    public static void main(String[] args){
        System.out.println(new SubSet().subsets(new int[]{1,2,3,4}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        List<List<Integer>> ret = new ArrayList<>();
        int n = nums.length;
        int count = 1 << n;
        for (int i = 0; i < count; ++i) {
            List<Integer> item=new ArrayList<>(n);
            for(int j=0;j<n;++j){
                if((i & (1<<j))!=0){
                    item.add(nums[j]);
                }
            }
            ret.add(item);
        }
        return ret;
    }
}
