package com.shawn.ss.tutorial.infinite_loop;

public class IntergerCausedInfinateLoop {
    static  final Integer LIMIT_CUASE_ERROR = 256;
    static  final Integer LIMIT_SAFE = 127;

    public static void testInfinateLoop(boolean infinate) {
        Integer i = 0;
        Integer limit=infinate?LIMIT_CUASE_ERROR:LIMIT_SAFE;
        while (i != limit) {
            //do things
            System.out.println("handle "+i);
            i++;
        }
    }
}
