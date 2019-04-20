package com.shawn.ss.leetcode;

/**
 * Runtime: 8 ms, faster than 95.47% of Java online submissions for Longest Palindromic Substring.
 Memory Usage: 37.4 MB, less than 92.88% of Java online submissions for Longest Palindromic Substring.
 */
public class Palindromic {
    public static void main(String[] args){

        String[] test=new String[]{
                "abbbcdeedcbbba",
                "bb",
                "cbbd",
                "sabcsbcbscbescieoos"
        };
        for(String s:test) {
            System.out.println(new Solution().longestPalindrome(
                s
            ));
        }
    }

    static class Solution {
        public String longestPalindrome(String s) {
            int n=s.length();
            if(n<=1){
                return s;
            }
            int max=-1;
//            int pos=1;
            int left=0;
            int right=1;
//            StringBuilder ret=null;
//            StringBuilder tmp=new StringBuilder(n);
            for(int i=0;i<n;++i){
                int len1=0;
                int len2=0;
                boolean flag=false;
                if(max>2*Math.min(i,n-i)+2){
                    break;
                }
                if(i+1<n && s.charAt(i)==s.charAt(i+1)){
                    len1 = compare(s,i,i+1,n);
                    flag=true;
                }
                len2=compare(s,i,i,n);

                flag = flag && (2*len1+2)>(2*len2+1);
                int len=flag?(2*len1+2):(2*len2+1);
                if(len>max){
                    max=len;
                    if(flag){
                        left=i-len1;
                        right=i+2+len1;
                    }else{
                        left=i-len2;
                        right=i+len2+1;
                    }
                }else {

                }
            }
            return s.substring(left,right);
        }

        private int compare(String s, int left, int right, int length) {
            int ret=0;
            left--;
            right++;
            while(left>=0 && right<length){
                if(s.charAt(left)==s.charAt(right)){
                    ret++;
                    left--;
                    right++;
                }else {
                    break;
                }
            }
            return ret;
        }
    }
}
