package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SplitToFibonacci {

    public static void main(String[] args) {
        SplitToFibonacci s = new SplitToFibonacci();
        System.out.println(s.splitIntoFibonacci("214748364214748364429496728"));
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
        if (len1 > 10 || len2 > 10) return false;
        int r[] = null;
        int length = s.length();
        System.out.println(ret);
        r = testInteger(s, ret, st, st + len1, st + len1 + len2);
        int preSt = st;
        if (r[0] > 0) {
            st = r[0];
            if (r[1] < length)
                if (search(s, ret, st, len2, r[2])) return true;
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
            return search2;
        }
        return false;

        //        return false;
    }

    private int[] testInteger(String s, List<Integer> ret, int s0, int s1, int s2) {
        if (s.charAt(s0) == '0' && s1 - s0 > 1) {
            return new int[]{-1, 0, 0};
        }
        if (s.charAt(s1) == '0' && s2 - s1 > 1) {
            return new int[]{-1, 0, 0};
        }
        try {
            int a1 = getInt(s, s0, s1);
            int a2 = getInt(s, s1, s2);
            if (a1 < 0 || a2 < 0) return new int[]{-1, 0, 0};
            if (a1 > Integer.MAX_VALUE - a2) return new int[]{-1, 0, 0};
            int a3 = a1 + a2;
            String s3 = String.valueOf(a3);
            int length = s3.length();
            int i = s2 + length;
            String s3_ = null;
            if (i <= s.length() && (s.charAt(s2) != '0' || length == 1)) {

                s3_ = s.substring(s2, i);
                if (s3.equals(s3_)) {
                    if (ret.size() == 0) {
                        ret.add(a1);
                        ret.add(a2);
                    }
                    ret.add(a3);
                    System.out.println(s.substring(s0) + " " + a1 + " " + a2 + " " + a3 + " " + s3_);
                    return new int[]{s1, i, length};
                }
            }
            System.out.println(s.substring(s0) + " " + a1 + " " + a2 + " " + a3 + " " + s3_);
            return new int[]{-1, 0, 0};
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return new int[]{-1, 0, 0};
        }
    }

    private int getInt(String s, int s0, int s1) {
        long l = Long.parseLong(s.substring(s0, s1));
        return l > Integer.MAX_VALUE ? -1 : (int) l;
    }
}
