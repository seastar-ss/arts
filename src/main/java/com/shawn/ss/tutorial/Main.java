package com.shawn.ss.tutorial;

import com.shawn.ss.tutorial.gc_about.FinalizerProblem;
import com.shawn.ss.tutorial.infinite_loop.DoubleCausedInfinateLoop;
import com.shawn.ss.tutorial.infinite_loop.IntergerCausedInfinateLoop;
import com.shawn.ss.tutorial.string_about.StringConcatProblem;
import com.shawn.ss.tutorial.string_about.StringInternProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Object> gcRoot=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        long start=System.currentTimeMillis();
        String command="";
//        if(args!=null && args.length>0){
//            command=args[0];
//        }
        int read=0;
        byte[] buf=new byte[256];
        while((read=System.in.read(buf))>0) {
            command=new String(buf,0,read).replace("\n","");
            System.out.println("start"+(System.currentTimeMillis()-start));
            if (command.equals("intern")) {

                for (int i = 0; i < 10; ++i) {
                    List<String> strings = StringInternProblem.generateStringIntern();
                    gcRoot.add(strings);
                }
                System.out.println("done"+(System.currentTimeMillis()-start));
            }

            if (command.equals("noIntern")) {

                for (int i = 0; i < 10; ++i) {
                    List<String> strings = StringInternProblem.generateStringNoIntern();
                    gcRoot.add(strings);
                }
                System.out.println("done"+(System.currentTimeMillis()-start));
            }

            if (command.equals("stringConcat")) {

                String s = StringConcatProblem.testStringConcat();
                gcRoot.add(s);
                System.out.println("done"+(System.currentTimeMillis()-start));
            }

            if (command.equals("stringBuilderConcat")) {

                String s = StringConcatProblem.testStringBuilderConcat();
                gcRoot.add(s);
                System.out.println("done"+(System.currentTimeMillis()-start));
            }

            if (command.equals("finalizer")) {

                FinalizerProblem.testFinalizer();
            }

            if (command.equals("noFinalizer")) {

                FinalizerProblem.testFinalizerSafe();
            }

            if(command.equals("intergerLoop")){
                IntergerCausedInfinateLoop.testInfinateLoop(false);
            }

            if(command.equals("intergerInfinateLoop")){
                IntergerCausedInfinateLoop.testInfinateLoop(true);
            }

            if(command.equals("doubleInfinateLoop")){
                DoubleCausedInfinateLoop.testLoop();
            }

            System.out.println("finish handle");
        }
    }
}
