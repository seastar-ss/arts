package com.shawn.ss.interview_code;

import java.util.Scanner;

public class NewSolution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long m = in.nextLong();
//        System.out.println(n + " " + m);
//        long i = removeNumber(3, 345271 );
        long i = removeNumber(n, m );
        System.out.println(i);
    }

    private static long removeNumber(int n, long num) {
        if (n == 0) return num;
        String m = String.valueOf(num);
        if (m.length() <= n) return 0;
        long ret = Integer.MIN_VALUE;
        long newInt = 0;
        for (int i = 0, len = m.length(); i < len; ++i) {
            newInt = Long.parseLong(m.substring(0, i) + (i < len - 1 ? m.substring(i + 1, len) : ""));
            if (newInt > ret) ret = newInt;
        }
        return removeNumber(n - 1, ret);
    }

}
