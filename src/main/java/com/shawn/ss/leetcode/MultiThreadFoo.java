package com.shawn.ss.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadFoo {

    public static void main(String[] args) {
        Foo foo = new Foo();
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            AtomicInteger seq = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "test-thread-" + seq.incrementAndGet());
                return t;
            }
        });
        int rnd = 0;//(int) (Math.random() * 3);
        for (int i = 0; i < 6; ++i) {
            System.out.println("seed rnd:" + rnd);
            executorService.execute(new MyRunnable((rnd + i) % 3, foo, rnd));
        }
        executorService.shutdown();
    }

    static class Foo {
        ReentrantLock lock;
        Condition firstCalled, secondCalled, thirdCalled;
        volatile int step;

        public Foo() {
            lock = new ReentrantLock();
            firstCalled = lock.newCondition();
            secondCalled = lock.newCondition();
            thirdCalled = lock.newCondition();
            step = 0;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            try {
                lock.lock();
                System.out.println("first locked");
                //                thirdCalled.await();
                printFirst.run();
                step++;
                firstCalled.signal();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            try {
                // printSecond.run() outputs "second". Do not change or remove this line.
                lock.lock();
                System.out.println("second locked");
                if (step < 1)
                    firstCalled.await();
                printSecond.run();
                step++;
                secondCalled.signal();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            try {
                lock.lock();
                System.out.println("third locked");
                // printThird.run() outputs "third". Do not change or remove this line.
                if (step < 2)
                    secondCalled.await();
                printThird.run();
                step = 0;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class MyRunnable implements Runnable {
        private final int rnd;
        private final Foo foo;
        //        private final int i;
        static String[] info = {"first", "second", "third"};

        public MyRunnable(int rnd, Foo foo, int i) {
            this.rnd = rnd;
            this.foo = foo;

            //            this.i = i;
        }

        @Override
        public void run() {
            //            System.out.println(info[i]);

            if (rnd == 0) {
                try {
                    foo.first(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("first");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (rnd == 1) {
                try {
                    foo.second(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("second");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (rnd == 2) {
                try {
                    foo.third(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("third");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
