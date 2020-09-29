package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QSort {

    final static boolean debugPrint = false;
    final static int TOTAL_TEST_TIMES = 2000;
    final static int TEST_RANGE = 100000;
    final static List<Long> timeSpends = new ArrayList<>(TOTAL_TEST_TIMES);
    final static List<Long> timeSlowSpends = new ArrayList<>(TOTAL_TEST_TIMES);
    final static List<Long> timeComparedSpends = new ArrayList<>(TOTAL_TEST_TIMES);

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_TEST_TIMES; ++i) {
            doTest();
        }
        long totalGap = 0, bigTotalGap = 0;
        for (int k = 0; k < TOTAL_TEST_TIMES; ++k) {
            long bigGap = timeSlowSpends.get(k) - timeSpends.get(k);
            long gap = timeSpends.get(k) - timeComparedSpends.get(k);
            System.out.println("compare:" + gap);
            System.out.println("compareSlow:" + bigGap);
            totalGap += gap;
            bigTotalGap += bigGap;
        }
        System.out.println("total gap:" + totalGap);
        System.out.println("big gap:" + bigTotalGap);
    }

    private static void doTest() {
        int[] array = buildNewInt(TEST_RANGE);
        int[] anotherArray = Arrays.copyOf(array, array.length);
        int[] slowArray = Arrays.copyOf(array, array.length);

        //        int[] array={7,5,4,5,6};
        //        int[] array = {6, 5, 4, 5, 7};
        if (debugPrint)
            System.out.println("start:" + Arrays.toString(array));

        long timeSpend = testQSort(array, true);
        long timeSpendOther = testQSort(anotherArray, false);
        long slow = doSlowSort(slowArray);

        timeSpends.add(timeSpend);
        timeComparedSpends.add(timeSpendOther);
        timeSlowSpends.add(slow);
        if (debugPrint)
            System.out.println("result:" + Arrays.toString(array));
    }

    private static long doSlowSort(int[] slowArray) {
        long timeStart = System.currentTimeMillis();
        int len = slowArray.length;
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i; j < len; ++j) {
                compareAndSwap(slowArray, i, j);
            }
        }
        long timeSpend = System.currentTimeMillis() - timeStart;
        System.out.println("slow time spend:" + timeSpend);
        check(slowArray);
        return timeSpend;
    }

    private static long testQSort(int[] array, boolean b) {
        long timeStart = System.currentTimeMillis();
        if (b)
            qSort(array);
        else
            Arrays.sort(array);
        long timeSpend = System.currentTimeMillis() - timeStart;
        System.out.println("time spend:" + timeSpend);
        check(array);
        return timeSpend;
    }

    private static void check(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            if (array[i - 1] > array[i]) {
                throw new IllegalStateException("wrong order");
            }
        }
    }

    private static int[] buildNewInt(int count) {
        Random rnd = new Random(System.currentTimeMillis());
        int[] ret = new int[count];
        for (int i = 0; i < count; ++i) {
            ret[i] = rnd.nextInt(count * 1000);
        }
        return ret;
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
        if (debugPrint)
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
        if (debugPrint)
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
