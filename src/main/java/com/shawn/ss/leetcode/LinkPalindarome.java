package com.shawn.ss.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/*
https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class LinkPalindarome {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) return true;
        ListNode fast = head;
        ListNode half = head;
        boolean noSkipOne = false;
        //        boolean reachEnd = false;
        Stack<Integer> vals = new Stack<>();
        while (fast.next != null) {
            if (fast.next.next != null) {
                fast = fast.next.next;
            } else {
                noSkipOne = true;
                fast = fast.next;
            }
            vals.push(half.val);
            half = half.next;
        }
        while (half != null) {
            if (!noSkipOne) {
                noSkipOne=true;
            }else{
                Integer pop = vals.pop();
                if(pop!=half.val) return false;
            }
            half = half.next;
        }
        return true;
    }
}
