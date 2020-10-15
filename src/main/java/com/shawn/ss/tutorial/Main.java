package com.shawn.ss.tutorial;

import com.shawn.ss.tutorial.gc_about.FinalizerProblem;
import com.shawn.ss.tutorial.infinite_loop.DoubleCausedInfinateLoop;
import com.shawn.ss.tutorial.infinite_loop.IntergerCausedInfinateLoop;
import com.shawn.ss.tutorial.string_about.StringConcatProblem;
import com.shawn.ss.tutorial.string_about.StringInternProblem;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {
    static List<Object> gcRoot = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String command = "";
        //        if(args!=null && args.length>0){
        //            command=args[0];
        //        }
        int read = 0;
        byte[] buf = new byte[256];
        while ((read = System.in.read(buf)) > 0) {
            command = new String(buf, 0, read).replace("\n", "");
            System.out.println("start "+command+" "+ (System.currentTimeMillis() - start));
            if (command.startsWith("intern")) {

                for (int i = 0; i < 10; ++i) {
                    List<String> strings = StringInternProblem.generateStringIntern();
                    gcRoot.add(strings);
                }
                System.out.println("done" + (System.currentTimeMillis() - start));
            }
            if (command.startsWith("HeapOverFlowInThread")) {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; ++i) {
                            List<String> strings = StringInternProblem.generateStringNoIntern();
                            gcRoot.add(strings);
                        }
                        System.out.println("done" + (System.currentTimeMillis() - start));
                    }
                });
            }

            if (command.startsWith("StackOverflowInThread")) {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        int a = 1;
                        String s = "sss";
                        call(a, s);
                    }

                    private void call(int a, String s) {
                        call(a + 1, s + "a");
                    }
                });
            }

            if (command.startsWith("noIntern")) {

                for (int i = 0; i < 10; ++i) {
                    List<String> strings = StringInternProblem.generateStringNoIntern();
                    gcRoot.add(strings);
                }
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("stringConcat")) {

                String s = StringConcatProblem.testStringConcat();
                gcRoot.add(s);
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("stringBuilderConcat")) {

                String s = StringConcatProblem.testStringBuilderConcat();
                gcRoot.add(s);
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("finalizer")) {

                FinalizerProblem.testFinalizer();
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("noFinalizer")) {

                FinalizerProblem.testFinalizerSafe();
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("intergerLoop")) {
                IntergerCausedInfinateLoop.testInfinateLoop(false);
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("intergerInfinateLoop")) {
                IntergerCausedInfinateLoop.testInfinateLoop(true);
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            if (command.startsWith("doubleInfinateLoop")) {
                DoubleCausedInfinateLoop.testLoop();
                System.out.println("done" + (System.currentTimeMillis() - start));
            }

            System.out.println("finish handle");

        }
    }
}
