package com.shawn.ss.leetcode;

public class RemoveKDigitsMakeMinNumber {
    public String removeKdigits(String num, int n) {
        if (n == 0) return num;
//        String m = num;
        if (num.length() <= n) return "0";
        long ret = Integer.MAX_VALUE;
        long newInt = 0;
        int i = 1, len = num.length();
        for (; i < len; ++i) {
            if(num.charAt(i)<num.charAt(i-1)){
                break;
            }
//            newInt = Long.parseLong(m.substring(0, i) + (i < len - 1 ? m.substring(i + 1, len) : ""));
//            if (newInt < ret) ret = newInt;
        }
        return removeKdigits( String.valueOf(ret),n - 1);
    }
}
