package com.shawn.ss.leetcode;
/*
https://leetcode-cn.com/problems/longest-mountain-in-array/
 */
import java.util.HashMap;
import java.util.Map;

public class LongestsMountainSearch {

    public static void main(String[] args){
        new LongestsMountainSearch().longestMountain(new int[]{2,2,2,1,2,1});
    }

    public int longestMountain(int[] a) {
        if (a == null || a.length < 3) return 0;
        int ret = 0;
        int state = 0;
        int curLen = 0;
        int end = a.length;
        //        Map<Integer,Integer> up=new HashMap<>();
        //        Map<Integer,Integer> down=new HashMap<>();
        for (int i = 1; i < end; ++i) {
            if (state == 0) {
                if (a[i - 1] > a[i]) {
                    curLen = 0;
                } else if (a[i - 1] < a[i]) {
                    state = 1;
                    curLen++;
                } else {
                    curLen = 0;
                }
            } else if (state == 1) {
                if (a[i - 1] > a[i]) {
                    state = 2;
                    curLen++;
                } else if (a[i - 1] < a[i]) {
                    //                    state = 1;
                    curLen++;
                } else {
                    curLen = 0;
                    state = 0;
                }
            } else if (state == 2) {
                if (a[i - 1] > a[i]) {
                    //                    state = 2;
                    curLen++;
                } else if (a[i - 1] < a[i]) {
                    if (ret <= curLen) ret = curLen + 1;
                    curLen = 1;
                    state = 1;
                } else {
                    if (ret <= curLen) ret = curLen + 1;
                    curLen = 0;
                    state = 0;
                }
            }
        }
        if(curLen>0 && state==2){
            if (ret <= curLen) ret = curLen + 1;
        }
        return ret;
    }

}
