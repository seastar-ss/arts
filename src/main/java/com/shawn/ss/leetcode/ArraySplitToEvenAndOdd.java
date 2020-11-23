package com.shawn.ss.leetcode;

public class ArraySplitToEvenAndOdd {
    public int[] sortArrayByParityII(int[] a) {
        if(a==null || a.length==0){
            return a;
        }
        int[] ret=new int[a.length];
        int even=0;
        int odd=0;
        for(int i=0,n=a.length;i<n;++i){
            if(a[i]%2==0){
                ret[2*even]=a[i];
                even++;
            }else{
                ret[2*odd+1]=a[i];
                ++odd;
            }
        }
        return ret;
    }
}
