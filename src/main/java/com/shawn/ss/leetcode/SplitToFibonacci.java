package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SplitToFibonacci {

    public static void main(String[] args) {
        SplitToFibonacci s = new SplitToFibonacci();
        System.out.println(s.splitIntoFibonacci("1235128"));
    }

    public List<Integer> splitIntoFibonacci(String s) {
        List<Integer> ret = new ArrayList<>(s.length());
        //        int start = 0;
        if (!search(s, ret, 0, 1, 1)) {
            ret.clear();
        }
        return ret;
    }

    private boolean search(String s, List<Integer> ret, int st, int len1, int len2) {
        int r[] = null;
        int length = s.length();
        System.out.println(ret);
        r = testInteger(s, ret, st, st + len1, st + len1 + len2);
        int preSt = st;
        if (r[0] > 0) {
            st = r[0];
            if (r[1] < length)
                if (search(s, ret, st, 1, 1)) return true;
                else {
                    ret.clear();
                    st = preSt;
                }
            else
                return true;
        }
        int i = st + len1 + len2 + Math.max(len2, len1) + 1;
        if (i <= length) {
            boolean search1 = search(s, ret, st, len1 + 1, len2);
            if (search1) return search1;
            boolean search2 = search(s, ret, st, len1, len2 + 1);
            if (search2) return search2;
            boolean search3 = i + 1 < length && search(s, ret, st, len1 + 1, len2 + 1);
            return search3;
            //                    search(s, ret, st, len1, len2, len3 + 1) ||

        }
        return false;

        //        return false;
    }

    //    private int seach(int s0, String s, List<Integer> ret) {
    //        //        int s1 = 1, s2 = s1 + 1;
    //        int length = s.length();
    //        int len = length - 2;
    //        int len1 = length - 1;
    //        for (int s1 = s0 + 1; s1 < len; ++s1) {
    //            for (int s2 = s1 + 1; s2 < len1; ++s2) {
    //                for (int s3 = s2 + 1; s3 < len; ++s3) {
    //                    //                    Integer s31 = testInteger(s0, s, ret, s1, s2, s3);
    //                    //                    if (s31 != null) return s31;
    //                }
    //            }
    //        }
    //        return -1;
    //    }

    private int[] testInteger(String s, List<Integer> ret, int s0, int s1, int s2) {
        int a1 = Integer.parseInt(s.substring(s0, s1));
        int a2 = Integer.parseInt(s.substring(s1, s2));
        int a3 = a1 + a2;
        String s3 = String.valueOf(a3);
        int i = s2 + s3.length();
        String s3_ = null;
        if (i <= s.length()) {

            s3_ = s.substring(s2, i);
            if (s3.equals(s3_)) {
                if (ret.size() == 0) {
                    ret.add(a1);
                    ret.add(a2);
                }
                ret.add(a3);

                return new int[]{s1, i};
            }
        }
        System.out.println(s.substring(s0) + " " + a1 + " " + a2 + " " + a3 + " " + s3_);
        return new int[]{-1, 0};
    }
}
