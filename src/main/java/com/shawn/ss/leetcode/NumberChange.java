package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 */
public class NumberChange {

    public static void main(String[] args) {
        int[] ints = new NumberChange().sortedSquares(new int[]{-7, -3, 2, 3, 11});
        //        int[] ints = new NumberChange().sortedSquares(new int[]{ -3,-2,-1,0,2,4});
        System.out.println(Arrays.toString(ints));
    }

    public int[] sortedSquares(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        if (a.length == 1) return new int[]{a[0] * a[0]};
        int left = 0;
        int right = a.length - 1;
        int pos = right;
        int[] ret = new int[a.length];
        int lv = a[left] * a[left];
        int rv = a[right] * a[right];
        while (left < right) {
            if (lv > rv) {
                ret[pos--] = lv;
                left++;
                lv = a[left] * a[left];
            } else {
                ret[pos--] = rv;
                right--;
                rv = a[right] * a[right];
            }
            if (left == right && ret[0] != lv) {
                ret[0] = lv;
            }
        }
        return ret;
    }

    public int[] sortedSquaresV2(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        int len = a.length;
        int index = Arrays.binarySearch(a, 0);
        //        List<Integer> ret = new ArrayList<>(len);
        int[] ret = new int[len];
        int left;

        int right;
        int pos = 0;
        boolean hasZero = index >= 0;
        if (hasZero) {
            ret[0] = 0;
            left = index - 1;
            right = index + 1;
            pos = 1;
        } else {
            index = -index - 1;
            left = index - 1;
            right = index;
        }

        int lv = -1;
        int rv = -1;

        boolean needLeftCalculate = true, needRightCalculate = true;
        while (left >= 0 || (right < len && right >= 0)) {
            if (needLeftCalculate) {
                if (left >= 0)
                    lv = a[left] * a[left];
                else
                    lv = -1;
            }
            if (needRightCalculate) {
                if (right < len)
                    rv = a[right] * a[right];
                else
                    rv = -1;
            }

            if (lv >= 0 && rv >= 0 && lv < rv) {
                left--;
                ret[pos++] = lv;
                needLeftCalculate = true;
                needRightCalculate = false;
            } else if (lv >= 0 && rv >= 0) {
                right++;
                //                ret.add(rv);
                ret[pos++] = rv;
                needRightCalculate = true;
                needLeftCalculate = false;
            } else if (lv < 0 && rv >= 0) {
                right++;
                //                ret.add(rv);
                ret[pos++] = rv;
                needRightCalculate = true;
                needLeftCalculate = false;
            } else if (lv >= 0 && rv < 0) {
                left--;
                //                ret.add(lv);
                ret[pos++] = lv;
                needLeftCalculate = true;
                needRightCalculate = false;
            } else {
                break;
            }
            //            ret.add(lv>rv?lv:rv);
        }
        return ret;
    }
}
