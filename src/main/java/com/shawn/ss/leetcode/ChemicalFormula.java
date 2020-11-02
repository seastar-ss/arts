package com.shawn.ss.leetcode;

import java.util.*;

public class ChemicalFormula {

    public static void main(String[] args) {
        String s = new ChemicalFormula().countOfAtoms("K4(ON(SO3)2)2");
        System.out.println(s);
    }

    public static final int IN_ATOM = 0, IN_CONTEXT = 1;

    class Context {
        Map<String, Integer> atomCount;
        int curNum;
        StringBuilder curAtom;
        int state;
        Context curContext;

        Context() {
            curAtom = new StringBuilder();
            curNum = 0;
            atomCount = new TreeMap<>();
            state = IN_ATOM;
        }
    }

    public String countOfAtoms(String formula) {
        //        Scanner s = new Scanner(formula);
        if (formula == null || formula.length() == 0) return "";
        int n = formula.length();
        //        Map<String, Integer> atomCount = new TreeMap<>();
        //        int state = 0;
        int index = 0;
        //        StringBuilder curAtom = new StringBuilder();
        //        int curNum = 0;
        Stack<Context> context = new Stack<>();
        context.push(new Context());
        while (index < n) {
            char c = formula.charAt(index);
            Context ctx = context.peek();
            if (c >= 'A' && c <= 'Z') {
                handleFinishTerm(ctx);
                ctx.curAtom.append(c);
                ctx.state = IN_ATOM;
            } else if (c >= 'a' && c <= 'z') {
                ctx.curAtom.append(c);
            } else if (c >= '0' && c <= '9') {
                ctx.curNum = ctx.curNum * 10 + (c - '0');
            } else if (c == '(') {
                handleFinishTerm(ctx);
                context.push(new Context());
            } else if (c == ')') {
                handleFinishTerm(ctx);
                Context pop = context.pop();
                ctx = context.peek();
                ctx.state = IN_CONTEXT;
                ctx.curContext = pop;
            }
            ++index;
        }
        handleFinishTerm(context.peek());
        StringBuilder ret = new StringBuilder();
        context.peek().atomCount.forEach((k, v) -> ret.append(k).append(v > 1 ? v : ""));
        return ret.toString();
    }

    private void handleFinishTerm(Context ctx) {
        if (ctx.curAtom.length() > 0 && ctx.state == IN_ATOM) {
            int value = ctx.curNum == 0 ? 1 : ctx.curNum;
            String key = ctx.curAtom.toString();
            Map<String, Integer> atomCount = ctx.atomCount;
            putAtom(value, key, atomCount);
            ctx.curAtom.delete(0, ctx.curAtom.length());
        }
        if (ctx.curContext != null && ctx.state == IN_CONTEXT) {
            Map<String, Integer> mapForHandle = ctx.curContext.atomCount;
            Map<String, Integer> atomCount = ctx.atomCount;
            int num = ctx.curNum == 0 ? 1 : ctx.curNum;
            Set<Map.Entry<String, Integer>> entries = mapForHandle.entrySet();
            for (Map.Entry<String, Integer> entry : entries) {
                String k = entry.getKey();
                int v = entry.getValue();
                putAtom(v * num, k, atomCount);
            }
        }
        ctx.curNum = 0;
    }

    private void putAtom(int value, String key, Map<String, Integer> atomCount) {
        if (atomCount.containsKey(key)) {
            atomCount.put(key, value + atomCount.get(key));
        } else {
            atomCount.put(key, value);
        }
    }
}
