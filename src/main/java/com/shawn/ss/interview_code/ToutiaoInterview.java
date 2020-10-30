package com.shawn.ss.interview_code;

/**
 * 定数组，每个元素代表一个木头的长度，木头可以任意截断，从这堆木头中截出至少k个相同长度为m的木块，已知k，求max(m)
 * [9, 4, 3, 10, 7]
 * k = 5 max(m) = ？
 */
public class ToutiaoInterview {

    public static class Main {
        public static void main(String[] args) {
            //Scanner in = new Scanner(System.in);
            //int a = in.nextInt();
            //System.out.println(a);
            int m=getMaxM(new int[]{9,4,3,15,7},5);
            System.out.println(m);
        }

        public static int getMaxM(int[] args,int k){
            int ret=0;
            int max=0;
            for(int i:args){
                if(i>max) max=i;
            }
            int start=1;
            int cur=(int)Math.floor(max/2);
            int end=max;
            while(true){
                int test=test(args,cur);
                if(test>k){
                    start=cur;
                    cur=(int)Math.floor((cur+end)/2);
                    if(cur==end || cur==end-1) break;
                }else{
                    end=cur;
                    cur=(int)Math.floor((cur+start)/2);
                    if(cur==start || cur==start+1) break;
                }
            }
            if(test(args,cur)>=k && test(args,cur+1)<k)
                return cur;
            if(test(args,cur)<k && test(args,cur-1)>=k)
                return cur-1;
            //System.out.println(cur);
            if(test(args,cur)>k && test(args,cur+1)>=k)
                return cur+1;
            return 0;
        }

        public static int test(int[] args,int m){
            int ret=0;
            for(int i:args){
                ret+=(int)Math.floor(i/m);
            }
            return ret;
        }
    }
}
