package com.shawn.ss.leetcode;

import java.util.Collections;

public class CompareString {

    public static void main(String[] args) {
        boolean b = new CompareString().backspaceCompare("##cc", "ab##c#d##cc");

        System.out.println(b);
    }

    public boolean backspaceCompare(String s, String t) {
        if (s == null || t == null) return s == null && t == null;
        //        if (s.length()==0 && t.length()==0) return false;
        //        int lenS = s.length();
        //        int lenT = t.length();
        StringBuilder ss = new StringBuilder();
        StringBuilder st = new StringBuilder();
        //        int si = 0, ti = 0;
        buildStr(ss, s);
        buildStr(st, t);
        return st.toString().equals(ss.toString());
    }

    private void buildStr(StringBuilder ss, String s) {
        for (int i = 0, lenS = s.length(); i < lenS; ++i) {
            char c = s.charAt(i);
            if (c == '#') {
                if (ss.length() > 0) {
                    ss.deleteCharAt(ss.length() - 1);
                }
            } else {
                ss.append(c);
            }
        }
    }

    public boolean backspaceCompareWrong(String s, String t) {
        if (s == null || t == null) return s == null && t == null;
        if (s.length() != t.length()) return false;
        int len = s.length();
        int[] idxS = new int[]{0, -1};
        int[] idxT = new int[]{0, -1};
        boolean sc = false, st = false;
        while (idxS[0] < len || idxT[0] < len) {
            char ss = s.charAt(idxS[0]);
            char ts = t.charAt(idxT[0]);
            if (ss == '#') {
                idxS[1] = idxS[1] > 0 ? idxS[1] - 1 : idxS[1];
                sc = false;
            } else {
                sc = true;
            }
            if (ts == '#') {
                idxT[1] = idxT[1] > 0 ? idxT[1] - 1 : idxT[1];
                st = false;
            } else {
                st = true;
            }
            if (st && sc && idxS[1] >= 0 && idxT[1] >= 0 && s.charAt(idxS[1]) != t.charAt(idxT[1])) return false;
            if (sc) {
                idxS[1] = idxS[0];
            }
            if (st) {
                idxT[1] = idxT[0];
            }
        }
        return true;
    }
}
