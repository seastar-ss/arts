package com.shawn.ss.leetcode;

import java.util.*;

/**
 * Runtime: 8 ms, faster than 89.40% of Java online submissions for Longest Palindromic Substring.
 * Memory Usage: 37.4 MB, less than 92.88% of Java online submissions for Longest Palindromic Substring.
 */
public class MergeNSortedSet {
    public static void main(String[] args) {

        ListNode[] test = new ListNode[]{
                ListNode.buildListNode(new int[]{1, 5, 7, 9,10,16,17}),
                ListNode.buildListNode(new int[]{3, 8,9, 12, 44}),
                ListNode.buildListNode(new int[]{1,23, 34, 66}),
        };
//        for(String s:test) {
        System.out.println(ListNode.toList(new Solution().mergeKLists(
                test
        )));
//        }
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists==null ){
                return null;
            }
            if(lists.length==0){
                return null;
            }else if(lists.length==1){
                return lists[0];
            }
            ListNode ret = null, curRet = null;
//            int[] cur=new int[lists.length];
            Map<Integer,List<Integer>> prq= new TreeMap<Integer,List<Integer>>();
//            boolean[] finish=new boolean[lists.length];
//            boolean done = false;
//            int mergedIndex = -1;
//            int minValue = Integer.MIN_VALUE;
//            int lstMinValue = Integer.MIN_VALUE;
            for (int i = 0; i < lists.length; ++i) {
//                if(!finish[i]) {
                ListNode node = lists[i];
                if (node != null) {
                    putToMapList(prq,node.val,i);
                    lists[i]=node.next;
                }
//                }
            }
            Iterator<Map.Entry<Integer, List<Integer>>> iterator;
            do  {
                iterator = prq.entrySet().iterator();
                if(!(iterator.hasNext())){
                    break;
                }
                Map.Entry<Integer, List<Integer>> next = iterator.next();
                Integer key = next.getKey();
                List<Integer> values=next.getValue();
                iterator.remove();
                for(Integer value:values) {
                    if (ret == null) {
                        curRet = new ListNode(key);
                        ret = curRet;
                    } else {
                        curRet.next = new ListNode(key);
                        curRet = curRet.next;
                    }

                    ListNode node = lists[value];
                    if (node != null) {
                        putToMapList(prq,node.val, value);
                        lists[value] = node.next;
                    }
                }
            }while(true);
            return ret;
        }

        public void putToMapList(Map<Integer,List<Integer>> prq,Integer key,Integer value){
            List<Integer> values;
            if(prq.containsKey(key)){
                values=prq.get(key);
            }else {
                values = new ArrayList<>();
            }
            values.add(value);
            prq.put(key,values);
        }
    }
}
