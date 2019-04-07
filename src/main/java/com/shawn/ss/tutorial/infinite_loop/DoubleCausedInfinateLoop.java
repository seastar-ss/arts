package com.shawn.ss.tutorial.infinite_loop;

public class DoubleCausedInfinateLoop {

    public static void testLoop(){
        Double d=0.02;
        while(d!=1.2){
            d+=0.01;
            System.out.println("d is now "+d);
        }
    }

}
