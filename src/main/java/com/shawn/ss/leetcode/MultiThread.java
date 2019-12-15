package com.shawn.ss.leetcode;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThread {
    static Random rnd=new Random();
    static BlockingQueue<Boolean> a=new ArrayBlockingQueue<Boolean>(1);
    static BlockingQueue<Boolean> b=new ArrayBlockingQueue<Boolean>(1);
    static BlockingQueue<Boolean> c=new ArrayBlockingQueue<Boolean>(1);

    static volatile boolean stop=false;

    static class ThreadA extends Thread{
        @Override
        public void run() {
            while(!stop) {

                try {
                    while (c.poll(100, TimeUnit.MILLISECONDS)==null){

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println(System.currentTimeMillis()+":A");
                try {
                    Thread.sleep(10+rnd.nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.offer(true);
            }
        }
    }
    static class ThreadB extends Thread{
        @Override
        public void run() {
            while(!stop) {
                try {
                    while (a.poll(100, TimeUnit.MILLISECONDS)==null){

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println(System.currentTimeMillis()+":B");
                try {
                    Thread.sleep(20+rnd.nextInt(30));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b.offer(true);
            }
        }
    }
    static class ThreadC extends Thread{
        @Override
        public void run() {
            while(!stop) {
                try {
                    while (b.poll(100, TimeUnit.MILLISECONDS)==null){

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println(System.currentTimeMillis()+":C");
                try {
                    Thread.sleep(10+rnd.nextInt(40));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.offer(true);
            }
        }
    }

    public static void main(String[] args){

        new ThreadA().start();

        new ThreadB().start();
        new ThreadC().start();
        c.offer(true);
    }
}
