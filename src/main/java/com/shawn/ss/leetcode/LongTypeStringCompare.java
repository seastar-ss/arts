package com.shawn.ss.leetcode;

public class LongTypeStringCompare {

    public static void main(String[] args) {
        boolean name = new LongTypeStringCompare().isLongPressedName(
                "pyplrz",
                "ppyypllr"
        );
        System.out.println(name);
    }

    public boolean isLongPressedName(String name, String typed) {
        if (typed == null || typed.length() == 0) return false;
        if (name == null || name.length() == 0) return true;
        if (typed.length() < name.length()) return false;
        int index = 0;
        int n = typed.length();
        int m = name.length();
        int repeatTyped = 0;
        //        boolean firstMatched = false;
        char cur = name.charAt(index);
        for (int i = 0; i < n; ++i) {
            char c = typed.charAt(i);
            if (c == cur) {
                //                firstMatched = true;
                ++repeatTyped;
            } else {
                if (i > 0) {
                    int repeatName = 0;
                    //                    boolean end = true;
                    while (cur == name.charAt(index++)) {
                        if (index >= m) return false;
                        ++repeatName;
                    }
                    System.out.println(repeatName + " " + repeatTyped + " " + c + " " + cur);
                    index--;
                    cur = name.charAt(index);

                    if (repeatName > repeatTyped) {
                        return false;
                    }
                    if (c == cur) {
                        repeatTyped = 1;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        int repeatName = 0;
        if (index < m - 1) {
            while (index < m) {
                if (cur == name.charAt(index++))
                    ++repeatName;
                else
                    return false;
            }
            return index >= m && repeatName <= repeatTyped;
        } else {
            return true;
        }
    }
}
