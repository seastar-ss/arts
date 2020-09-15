package com.shawn.ss.leetcode;

import java.util.Arrays;

public class QSort {
    public static void main(String[] args) {
        int[] array = {1, 4, 9, 10, 5, 3, 2, 7, 14, 5, 6};
        //        int[] array={7,5,4,5,6};
        //        int[] array = {6, 5, 4, 5, 7};
        System.out.println("start:" + Arrays.toString(array));
        qSort(array);
        System.out.println("result:" + Arrays.toString(array));
    }

    private static void qSort(int[] array) {
        qsort(array, 0, array.length - 1);

    }

    private static void qsort(int[] array, int start, int end) {
        if (end - start == 1) {
            boolean flag = compareAndSwap(array, start, end);
            return;
        } else if (start == end) {
            return;
        }
        int splitor = getMid(array, start, end);
        System.out.println(splitor + " : " + array[splitor] + " is used as splitor, range is " + start + " -- " + end);
        int s = start, e = end;
        while (start < end) {
            while (array[start] <= array[splitor] && start < splitor) {
                start++;
            }
            if (start != splitor) {
                if (compareAndSwap(array, start, splitor)) {
                    splitor = start;
                    //                start++;
                }
            }
            while (array[end] >= array[splitor] && end > splitor) {
                end--;
            }
            if (end != splitor) {
                if (compareAndSwap(array, splitor, end)) {
                    splitor = end;
                    //                end--;
                }
            }
        }
        System.out.println("result:" + Arrays.toString(array));
        if (start - 1 > s)
            qsort(array, s, start - 1);
        if (start + 1 < e)
            qsort(array, start + 1, e);

    }

    private static boolean compareAndSwap(int[] array, int start, int end) {
        if (array[start] > array[end]) {
            int tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            return true;
        }
        return false;
    }

    private static int getMid(int[] array, int start, int end) {
        int i0, i1, i2;
        i0 = array[start];
        i2 = array[end];
        int mid = (end + start) / 2;
        i1 = array[mid];
        if (i0 >= i1 && i0 <= i2) {
            return start;
        }
        if (i0 <= i1 && i0 >= i2) {
            return start;
        }
        if (i1 >= i0 && i1 <= i2) {
            return mid;
        }
        if (i1 <= i0 && i1 >= i2) {
            return mid;
        }
        if (i2 >= i0 && i2 <= i1) {
            return end;
        }
        if (i2 <= i0 && i2 >= i1) {
            return end;
        }
        return 0;
    }
}
