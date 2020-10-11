package com.shawn.ss.leetcode;

import java.util.Arrays;

public class QuickFindMid_ {

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 9, 19, 23};
        int[] a2 = {4, 7, 8, 18, 22, 25};

        int[] i = findMid(a1, a2);
        System.out.println(Arrays.asList(i));
    }

    private static int[] findMid(int[] a1, int[] a2) {
        int[] mid = getInts(a2, a1);
        if (mid != null) return mid;
        mid = getInts(a1, a2);
        if (mid != null) return mid;

        int n1 = a1.length - 1;
        int n2 = a2.length - 1;
        /*
        find target indexes
         */
        int[] targetMid = findMid(0, a1.length + a2.length);

        /*
        exchange to ensure a1 has little first element
         */
        int[] tmp = null;
        if (a1[0] > a2[0]) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }


        int i = Math.abs(Arrays.binarySearch(a1, a2[0]));
        int j = Math.abs(Arrays.binarySearch(a1, a2[n2]));
        int k = Math.abs(Arrays.binarySearch(a2, a1[n1]));

        /*
        case 1:
        a1  :   |   ********    |   ******* |   ******  |
                                i           j
        a2  :                   |   ******* |
                                                        k
        case 2:
        a1  :   |   ********    |   ******* |
                                i                       j
        a2  :                   |   ******* |   ******* |
                                            k

        case 3:
        a1  :   |   ********    |
                                    i,j
        a2  :                       |   ******* |
                                k
         */

        if (i == j && i >= k) {
            if(a1.length>targetMid[0]){

            }
        }

        System.out.println("got point : " + i + " " + j + " " + k);
        return new int[]{};
    }

    private static int[] getInts(int[] a1, int[] a2) {
        if (a2 == null || a2.length == 0) {
            int[] mid;
            mid = findMid(0, a1.length);
            return mid.length == 1 ? new int[]{a1[mid[0]]} : new int[]{a1[mid[0]], a1[mid[1]]};
        }
        return null;
    }

    private static int[] findMid(int start, int end) {
        int index = 0;
        int num = end - start + 1;
        if (num % 2 == 1) {
            index = start + (num - 1) / 2;
            return new int[]{index};
        } else {
            index = start + num / 2 - 1;
            return new int[]{index, index + 1};
        }

    }
}
