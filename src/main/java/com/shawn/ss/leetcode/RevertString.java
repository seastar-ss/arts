package com.shawn.ss.leetcode;

import java.util.Arrays;

public class RevertString {

    public static void main(String[] args) {
        char[] s = {'a', 'b', 'c', 'd'};
        new RevertString().reverseString(s);
        System.out.println(Arrays.toString(s));
        s = new char[]{'a', 'b', 'c', 'd', 'e'};
        new RevertString().reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    public void reverseString(char[] s) {
        if (s == null || s.length == 0 || s.length == 1) return;
        boolean b = s.length % 2 == 1;
        int k = (s.length - (b ? 1 : 0)) / 2;
        for (int i = 0; i < k; ++i) {
            char tmp = s[i];
            int i1 = s.length - 1 - i;
            s[i] = s[i1];
            s[i1] = tmp;
        }

    }
}
