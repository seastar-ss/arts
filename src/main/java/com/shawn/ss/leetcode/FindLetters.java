package com.shawn.ss.leetcode;

public class FindLetters {
    public int numJewelsInStones(String J, String S) {
        boolean[] flags = new boolean[127];
        int ret = 0;
        for (int i = 0, n = J.length(); i < n; ++i) {
            flags[J.charAt(i)] = true;
        }
        for (int i = 0, n = S.length(); i < n; ++i) {
            if (flags[S.charAt(i)]) ret++;
        }
        return ret;
    }
}
