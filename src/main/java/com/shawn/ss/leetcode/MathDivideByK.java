package com.shawn.ss.leetcode;
/**
 * https://leetcode-cn.com/problems/smallest-integer-divisible-by-k/
 */

import java.util.HashSet;
import java.util.Set;

/*

 */
public class MathDivideByK {

    public static void main(String[] args) {
//        int ii = new MathDivideByK().smallestRepunitDivByKBrute(21);
        int i = new MathDivideByK().smallestRepunitDivByK(111113333);

        System.out.println(i + " " );
    }

    public int smallestRepunitDivByKBrute(int k) {
        int ret = 1;
        long num = 1;
        boolean hasResult = false;
        while (ret < 19) {
            long l = num % k;
            if (l != 0) {
                num = num * 10 + 1;
                ret++;
                System.out.println(num + " " + l);
            } else {
                hasResult = true;
                break;
            }
        }
        return hasResult ? ret : -1;
    }

    //    int[][] stategy=new int[][]{
    //            {-1,-1},
    //            {1,0},
    //            {-1,-1},
    //            {7,8},
    //            {}
    //    }

    public int smallestRepunitDivByK(int k) {
        /**
         *
         *
         * (10rm+dm)*(10rk+dk)=100rm*rk+10(rm*dk+dm*rk)+dk*dm=11111111...1, 最后一位 dk*dm ， 可知
         * K 尾数dk的乘积中需要出现 1 ， 尾数是0，2，4，5，6，8 均无法达成条件
         * K 尾数dk为 1 时 m尾数dm为1, 上位进位0
         * K 尾数dk为 3 时 m尾数dm为7, 上位进位2
         * K 尾数dk为 7 时 m尾数dm为3, 上位进位2
         * K 尾数dk为 9 时 m尾数dm为9, 上位进位8
         *
         * 最后一位 9*rm*dk+9*dm*rk+[1,9,3]
         */
        int d = k % 10;
        if (d == 0) return -1;
        if (d == 2) return -1;
        if (d == 5) return -1;
        if (d == 4) return -1;
        if (d == 6) return -1;
        if (d == 8) return -1;
        //        int remain = (k - d) / 10;
        /**
         *  10^(n-1)+10^(n-2)+……+10+1 = (10^n-1)/9
         *
         *  m*K=(10^n-1)/9
         *  故 9*m*K+1=10^n
         */
        int digitCount = (int) Math.floor(Math.log10(9 * k + 1));
        int num = (int) (Math.pow(10, digitCount) - 1) / 9;
        int re = num % k;
//        Set<Integer> remainSet = new HashSet<>();
        /**
         * r0= num % k
         * r1= (num*10+1) % k = (num*10)%k + 1 = (10*(k*m+r0)) % k +1 = (10*r0)%k+1
         * 因为余数递推公式只与k有关，故余数可能出现循环的情况，只要出现循环即可终止探测
         */
        while (re != 0) {
            digitCount++;
            re = ((re * 10) % k + 1) % k;
//            if (remainSet.contains(re)) {
//                System.out.println(remainSet+" contains  "+re);
//                return -1;
//            }
//            remainSet.add(re);
        }

        return digitCount;

    }
}
