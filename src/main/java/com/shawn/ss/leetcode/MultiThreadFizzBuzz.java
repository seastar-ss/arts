package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class MultiThreadFizzBuzz {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(16);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("\t\tbuzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("\t\tfizz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("\t\tfizzbuzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.number(
                            //                            System.out::println
                            e -> {
                                System.out.println("\t\t" + e);
                            }
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 假设有这么一个类：
 * <p>
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <p>
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class FizzBuzz {
    private int n;

    private ReentrantLock lock;
    private Condition condition/*, condition3, condition5, condition35*/;
    private AtomicInteger seq;

    public FizzBuzz(int n) {
        this.n = n;
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
        //        condition3 = lock.newCondition();
        //        condition5 = lock.newCondition();
        //        condition35 = lock.newCondition();
        seq = new AtomicInteger(1);
    }

    private void runTask(Runnable print, IntConsumer consumer, Predicate<Integer> test) throws InterruptedException {
        try {
            while (!lock.tryLock(100, TimeUnit.MILLISECONDS)) ;
            int i = 0;
            while ((i = seq.get()) <= n) {
                System.out.println("fizz:" + i);
                //                boolean run = false;
                while (test.test(i) && i <= n) {
                    if (print != null)
                        print.run();
                    else if (consumer != null)
                        consumer.accept(i);
                    i = seq.incrementAndGet();
                    //                    run = true;
                }
                condition.signalAll();
                if (i <= n) {
                    condition.await();
                } else {
                    break;
                }
            }
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        runTask(printFizz, null, i -> {
            return i % 3 == 0 && i % 5 != 0;
        });
    }


    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        runTask(printBuzz, null, i -> {
            return i % 3 != 0 && i % 5 == 0;
        });
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        runTask(printFizzBuzz, null, i -> {
            return i % 3 == 0 && i % 5 == 0;
        });
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        runTask(null, printNumber, i -> {
            return i % 3 != 0 && i % 5 != 0;
        });
    }
}
