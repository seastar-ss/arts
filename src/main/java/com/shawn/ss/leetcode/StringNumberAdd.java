package com.shawn.ss.leetcode;

public class StringNumberAdd {

    public static void main(String[] args) {
        //        for (int i = 0; i < 30; ++i) {
        //            System.out.println(i / 10 + " " + i);
        //            //            System.out.println(i/7+" "+i);
        //        }
        String s = new StringNumberAdd().addStrings("1234", "123456");
        System.out.println(s);
    }

    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        if (num1.length() > num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        int base = '0';
        int n1 = num1.length();
        int n2 = num2.length();
        //        StringBuilder ret = new StringBuilder();
        char[] ret = new char[n2];
        int carry = 0;
        for (int i = 1; i <= n2; ++i) {
            int d1 = 0;
            if (i <= n1) {
                d1 = num1.charAt(n1 - i) - base;
            }
            int d = d1 + (num2.charAt(n2 - i) - base) + carry;
            ret[n2 - i] = (char) (d % 10 + base);
            carry = d / 10;
        }
        //        for (int i = n1; i < n2; ++i) {
        //
        //        }
        String s = new String(ret);
        return carry > 0 ? carry + s : s;
    }
}
