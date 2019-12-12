package com.shawn.ss.leetcode;

public class NRadixNumber {
    public static void main(String[] args) {
        for (int i = 1000; i < 1000000; ++i)
            System.out.println(testNRadixNumber(i));
    }

    static final char[] digits = new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', '1', '2', '3', '4', 's', 'S', 'z', 'Z'};

    private static String testNRadixNumber(int i) {
        StringBuilder ret = new StringBuilder();
        int r = i;
        do {
            int d = r % digits.length;
            r = (r - d) / digits.length;
            ret.append(digits[d]);
        } while (r > 0);
        ret.reverse();
        return ret.toString();
    }
}
