package com.shawn.ss.leetcode;

public class ArrayCheckV3 {

    public static void main(String[] args) {
        boolean b = new ArrayCheckV3().validMountainArray(new int[]{0, 1, 2, 4, 5, 6});
        System.out.println(b);
    }

    public boolean validMountainArray(int[] a) {
        if (a == null || a.length < 3) return false;
        int inUp = 0;
        for (int i = 1, n = a.length; i < n; ++i) {
            if (inUp == 0) {
                if (a[i - 1] < a[i]) inUp = 1;
                else return false;
            } else if (inUp == 1) {
                if (a[i - 1] < a[i]) inUp = 1;
                else if (a[i - 1] > a[i]) inUp = 2;
                else return false;
            } else if (inUp == 2) {
                if (a[i - 1] > a[i]) inUp = 2;
                else return false;
            }
        }
        return inUp==2;
    }
}
