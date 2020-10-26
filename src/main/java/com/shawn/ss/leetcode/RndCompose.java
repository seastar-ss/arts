package com.shawn.ss.leetcode;
/**
 * https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/
 */

import java.util.Random;

public class RndCompose {
    Random rnd=new Random();
    public int rand7(){
        return rnd.nextInt(7)+1;
    }

    public int rand10() {
        int a=rand7();
        int b=rand7()-1;
        int x=a+b*7;
        if(x>40) return rand10();
        return x%10+1;
    }

}
