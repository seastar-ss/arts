package com.shawn.ss.leetcode;

import java.util.Arrays;

/*
https://leetcode-cn.com/problems/2vYnGI/
 */
public class BreakfastCombo {

    public static void main(String[] args) {
        int count = new BreakfastCombo().breakfastNumber(
                new int[]{3, 10, 6, 6, 9, 2, 5},
                new int[]{5, 2, 2, 8, 6, 9, 6, 8, 6, 6},
                13
        );
        System.out.println(count);
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        if (staple == null || drinks == null || staple.length == 0 || drinks.length == 0) return 0;
        long ret = 0;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        boolean flag = staple.length > drinks.length;
        int[] check = flag ? drinks : staple;
        int[] checkOther = flag ? staple : drinks;
        int index = checkOther.length;
        for (int i = 0, n = check.length; i < n; ++i) {
            int p1 = check[i];
            int remain = x - p1;

            index = Arrays.binarySearch(checkOther, 0, index, remain);
            if (index < -1) {
                index = -index - 1;
                ret += index;
            } else if (index == -1) {
                break;
            } else {
                while (index < checkOther.length && checkOther[index] == remain) index++;
                ret += index;
            }
        }
        return (int) (ret % 1000000007);
    }

}
