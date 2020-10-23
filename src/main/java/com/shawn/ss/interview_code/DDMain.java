package com.shawn.ss.interview_code;

import java.util.Arrays;

public class DDMain {
    public static void main(String[] args) {
        int a = 2, b = 3;
        a = a ^ b;
        b = b ^ a;
        System.out.println(a + " " + b);
    }

//    public static final long len=2M;
//
//    public void sortByFile(File af){
//        int index=0;
//        int total=af.length()/len;
//        File fs=new File[total];
//        int idx=0;
//        while(index<af.length()) {
//            int[] arr=read(af,index,index+len);
//            Arrays.sort(arr);
//            fs[idx++]=write(arr,af.getName+index);
//        }
//        int[] arr=new int[total];
//        for(){
//            File f=fs[]
//            arr[i]=read(f,0,1);
//        }
//        while(true){
//
//        }
//    }
}
