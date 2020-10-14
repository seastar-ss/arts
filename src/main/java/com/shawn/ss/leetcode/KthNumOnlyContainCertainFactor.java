package com.shawn.ss.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/get-kth-magic-number-lcci/
 */
public class KthNumOnlyContainCertainFactor {

    public static void main(String[] args) {
        //        KthNumOnlyContainCertainFactor factor = new KthNumOnlyContainCertainFactor();
        //        for (int i = 1; i < 20; ++i)
        //            System.out.println(factor.getKthMagicNumber(i));
        Map<Integer, Integer> all = new TreeMap<>();
        for (int i = 1; i < 12; ++i) {
            SortedSet<Integer> set = testFactorCombo(new int[]{3, 5, 7}, i);
            System.out.println(set.size() + " " + set);
            for (Integer tt : set) {
                all.put(tt, i);
            }
        }
        System.out.println(all.size() + " " + all);
    }

    private static SortedSet<Integer> testFactorCombo(int[] ints, int k) {
        SortedSet<Integer> ret = new TreeSet<>();
        int n = ints.length;
        int[] factors = new int[k];
        Arrays.fill(factors, 1);
        buildNums(ints, factors, ret, 0, k);
        return ret;
    }

    private static void buildNums(int[] ints, int[] factors, SortedSet<Integer> ret, int index, int k) {
        if (index == k - 1) {
            int re = 1;
            for (int i : factors) {
                re *= i;
            }
            ret.add(re);
        } else {
            for (int i = 0, n = ints.length; i < n; ++i) {
                factors[index] = ints[i];
                buildNums(ints, factors, ret, index + 1, k);
            }
        }
    }


    public int getKthMagicNumber(int k) {
        if (k == 1) return 1;
        return getKthMagicNumber(new int[]{3, 5, 7}, k);
    }

    public int getKthMagicNumber(int[] factor, int k) {
        int n = factor.length;
        int ret = 1;
        /**
         * 0个因数 1
         * 1个因数 3
         * 2个因数 6=3！
         *
         * aa ab ac
         * ba bb bc
         * ca cb cc
         *
         * 3个因数 27=3^3
         * aaa aab aac aba abb abc aca acb acc
         * baa bab bac bba bbb bbc bca bcb bcc
         * caa cab cac cba cbb cbc cca ccb ccc
         *
         * aab aba baa  = 3
         * abb bab bba  = 3
         *
         * 取一个 3
         * 取两个 3*2
         * 取三个 1
         *
         * 4个因数
         * 取一个 3
         * 取两个 3*3
         * 取三个 3
         *
         * 5个因数
         * 取一个 3
         * 取两个 3*4
         * 取三个 6
         *
         * 6个因数
         * 取一个 3
         * 取两个 3*5
         * 取三个 10
         *
         * x个因数
         * 3+3*(x-1)+(x-1)(x-2)/2
         *
         * 序列  1 ； 3，5，7 ； 9，15，21，25，35，49； 27，45，63，75，105，125，147，175，247，343； 3*3*3*3 ， 3*3*3*5， 3*3*3*7， 3
         */
        int factorCount = (int) ((Math.log(2 * k + 1) / Math.log(3)) - 1);
        return ret;
    }
}
