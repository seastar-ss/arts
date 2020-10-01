package com.shawn.ss.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeavesOperation {

    public static void main(String[] args) {
        int ret = new LeavesOperation().minimumOperations("ryyryyyrryyyyyryyyrrryyyryryyyyryyrrryryyyryrryrrrryyyrrrrryryyrrrrryyyryyryrryryyryyyyryyrryrryryy");
        System.out.println(ret);
    }

    public int minimumOperations(String leaves) {
        int n = leaves.length();
        int index = 0;
        int areaIndex = 0, totalArea = 0;
        boolean firstIsR = leaves.charAt(0) == 'r';
        boolean lastIsR = leaves.charAt(n - 1) == 'r';
        int ret = 0;
        int count = 0;
        Map<Integer, Integer> areaCount = new HashMap<>();
        while (index < n) {
            ++count;
            if (index < n - 1 && leaves.charAt(index) != leaves.charAt(index + 1)) {
                areaCount.put(areaIndex, count);
                areaIndex++;
                count = 0;
            }
            ++index;
        }
        areaCount.put(areaIndex, count);

        System.out.println(areaCount);
        return ret;
    }

    public int minimumOperations1(String leaves) {
        int n = leaves.length();
        int index = 0;
        int rstart = -1;
        int ystart = -1;
        int yend = -1;
        int ret = 0;
        boolean firstIsR = leaves.charAt(0) == 'r';
        boolean lastIsR = leaves.charAt(n - 1) == 'r';
        boolean curAreaIsR = firstIsR;
        int areaIndex = 0, totalArea = 0;
        /*
        状态1 ： r y | r | y
        状态2 ： y | r | y
         */
        while (index < n - 1) {
            if (leaves.charAt(index) != leaves.charAt(index + 1)) totalArea++;
            ++index;
        }
        if (totalArea == 2 && firstIsR && lastIsR) return 0;
        index = 0;
        while (index < n) {
            char c = leaves.charAt(index);
            if (curAreaIsR) {
                if (c == 'r') {
                    if (areaIndex == 0 || areaIndex == totalArea) {
                        //do nothing
                    } else {
                        ret++;
                    }
                } else {
                    curAreaIsR = false;
                    areaIndex++;
                }
            } else {
                if (c == 'y') {
                    //do nothing
                } else {
                    curAreaIsR = true;
                    areaIndex++;
                    if (areaIndex != totalArea)
                        ret++;
                }
            }
            ++index;
        }
        if (!firstIsR) ret++;
        if (!lastIsR) ret++;
        return ret;
    }
}
