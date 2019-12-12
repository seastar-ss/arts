package com.shawn.ss.leetcode;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String pattern = "aaaa";
        String string = "aaa";

        final boolean match = new TransferMachine(pattern).match(string);
        System.out.println(match);
    }

    public static class TransferMachine {
        public static final int next = 0;
        public static final int keep = 1;
        public static final int fail = -1;

        Deque<State> sequence;
        final String pattern;


        public static class State {
            static final char ANY = '.';
            boolean many;
            boolean one;
            final char c;

            State(char c) {
                this.c = c;
            }

            int[] transfer(char t) {
                one=true;
                if (c == t || c == ANY) {
                    return many ? new int[]{keep, next} : new int[]{next, next};
                } else {
                    return many ? new int[]{next, keep} : new int[]{fail, fail};
                }
            }
        }

        public TransferMachine(String pattern) {
            this.pattern = pattern;
            buildTrasferMachine();
        }

        final void buildTrasferMachine() {
            final int length = pattern.length();
            sequence = new ArrayDeque<>(length);
//            int index=0;
            for (int i = 0; i < length; ++i) {
                final char c = pattern.charAt(i);
                if (c != '*') {
                    sequence.add(new State(c));
                } else {
                    sequence.peekLast().many = true;
                }
            }
        }

        public boolean match(String str) {
            final int length = str.length();
            int i = 0;
            State poll = sequence.poll();
            System.out.println("pull "+poll.c+" "+poll.many);
            while (i < length) {
                if(poll==null){
                    return false;
                }
                final char c = str.charAt(i);
                final int[] transfer = poll.transfer(c);
                System.out.println("state "+i+" "+transfer[0]+" "+transfer[1] );
                if(transfer==null){
                    return false;
                }
                if (transfer[0] == keep) {

                } else if (transfer[0] == next) {
                    poll = sequence.poll();

                    if (poll != null) {
                        System.out.println("pull "+poll.c+" "+poll.many);
                    }

                } else {
                    return false;
                }
                if (transfer[1] == keep) {

                } else if (transfer[1] == next) {
                    ++i;

                }else{
                    return false;
                }
            }
            return sequence.size()==0 && poll.one;
        }


    }
}
