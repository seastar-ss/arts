package com.shawn.ss.leetcode;

import java.util.*;

public class CharSequence {
    public static void main(String args[]) {
//        System.out.println(new SolutionV2().isInterleaveV2(
//                new String[]{
//                        "aabcc",
//                        "dbbca"
//                },
//                "aadbbcbcac"
//        ));
//        System.out.println(new SolutionV2().isInterleave(
//                "aabc",
//                "abad",
//                "aabadabc"
//        ));
//
//        System.out.println(new SolutionV2().isInterleave(
//                "",
//                "",
//                ""
//        ));

        System.out.println(new SolutionV2().isInterleave(
                "aaaaaa",
                "aaaaa",
                "aaaaaaaaaaa"
        ));

//        System.out.println(new SolutionV2().isInterleave(
//                "abbbbbbcabbacaacccababaabcccabcacbcaabbbacccaaaaaababbbacbb",
//                "ccaacabbacaccacababbbbabbcacccacccccaabaababacbbacabbbbabc",
//                "cacbabbacbbbabcbaacbbaccacaacaacccabababbbababcccbabcabbaccabcccacccaabbcbcaccccaaaaabaaaaababbbbacbbabacbbacabbbbabc"
//        ));
//
//
//        System.out.println(new SolutionV2().isInterleaveV2(
//                new String[]{
//                        "",
//                        ""
//                },
//                "a"
//        ));
    }

    static class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            return check(s1, s2, s3, s1.length(), s2.length(), s3.length());
        }

        boolean check(String s1, String s2, String s3, int r1, int r2, int r3) {
            if (r1 <= 0 && r2 <= 0 && r3 <= 0) {
                return true;
            }
            if (r3 <= 0) {
                return false;
            }
            if (r1 <= 0) {
                return s3.substring(0, r3).equals(s2.substring(0, r2));
            }
            if (r2 <= 0) {
                return s3.substring(0, r3).equals(s1.substring(0, r1));
            }
            return (s1.charAt(r1 - 1) == (s3.charAt(r3 - 1)) && check(s1, s2, s3, r1 - 1, r2, r3 - 1))
                    ||
                    (s2.charAt(r2 - 1) == (s3.charAt(r3 - 1)) && check(s1, s2, s3, r1, r2 - 1, r3 - 1));
        }
    }

    static class SolutionV2 {

        static class Status {
            int[] index;

            public Status(int demession) {
                this.index = new int[demession];
            }

            public Status(int[] index) {
                this.index = index;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Status status = (Status) o;

                return Arrays.equals(index, status.index);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(index);
            }
        }

        public boolean isInterleave(String s1,String s2,String s3){
            return isInterleaveV2(new String[]{s1,s2},s3);
        }

        public boolean isInterleaveV2(String s1[], String s3) {
            if(s3==null){
                return false;
            }
            boolean ret = true;
//            int len1=s1.length();
//            int len2=s2.length();
            int index = 0;
            int demession = s1.length;
            int lenSub=0;
            int n = s3.length();
            for(int i=0;i<demession;++i){
                if(s1[i]==null){
                    return false;
                }
                lenSub+=s1[i].length();
            }
            if(lenSub!=n){
                return false;
            }
            if(lenSub==0 ){
                return true;
            }
            Set<Status> status = new HashSet<>();
            Set<Status> nextStatus = new HashSet<>();
            status.add(new Status(demession));

            Status cur;
            do {
                char c = s3.charAt(index);
                Iterator<Status> iterator = status.iterator();
                while (iterator.hasNext()) {
                    cur = iterator.next();
                    for (int i = 0; i < demession; ++i) {
                        if (cur.index[i] < s1[i].length()) {
                            if (c == s1[i].charAt(cur.index[i])) {
                                int[] indexes = Arrays.copyOf(cur.index, cur.index.length);
                                indexes[i] = indexes[i] + 1;
                                nextStatus.add(new Status(indexes));
                            }
                        }
                    }
                }
                if(nextStatus.isEmpty()){
                    ret=false;
                    break;
                }else{
                    Set<Status> tmp=status;
                    status=nextStatus;
                    nextStatus=tmp;
                    nextStatus.clear();
                    ++index;

                }
            } while (index < n );
            System.out.println(status.size());
            return ret;
        }


    }

}
