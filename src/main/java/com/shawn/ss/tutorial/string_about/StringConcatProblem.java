package com.shawn.ss.tutorial.string_about;

public class StringConcatProblem {
    public static String testStringConcat(){
        String str="";
        for(int i=0;i<200000;++i){
            str=str+" "+(i%2==0?"s"+i:"d"+i);
        }
        return str;
    }

    public static String testStringBuilderConcat(){
        StringBuilder ret=new StringBuilder();
        for(int i=0;i<200000;++i){
            ret.append(" ").append((i%2==0?"s":"d")).append(i);
        }
        return ret.toString();
    }
}
