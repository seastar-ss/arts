package com.shawn.ss.leetcode;

import java.util.*;

/*
https://leetcode-cn.com/problems/find-common-characters/
 */
public class CommonChars {

    public static void main(String[] args) {
        System.out.println(new CommonChars().commonChars(new String[]{"acabcddd", "bcbdbcbd", "baddbadb", "cbdddcac", "aacbcccd", "ccccddda", "cababaab", "addcaccd"}));
    }



    public List<String> commonChars(String[] a) {
        Map<Character, Integer> curSeq = new HashMap<>();
        //    Map<Character, Integer> preSeq = new HashMap<>();
        Map<Character, Integer> finalSeq = new HashMap<>();
        for (int i = 0, n = a.length; i < n; ++i) {
            if (finalSeq.size() == 0 && i != 0) break;
            String s = a[i];
            for (int j = 0, nn = s.length(); j < nn; ++j) {
                char key = s.charAt(j);
                Integer ch = curSeq.computeIfPresent(key, (k, e) -> e + 1);
                if (ch == null) curSeq.put(key, 1);
            }
            if (i == 0) {
                finalSeq.putAll(curSeq);
            } else {
                Iterator<Map.Entry<Character, Integer>> iterator = finalSeq.entrySet().iterator();
                while (iterator.hasNext()) {
                    Character key = iterator.next().getKey();
                    if (!curSeq.containsKey(key)) iterator.remove();
                }
                Set<Map.Entry<Character, Integer>> entries = curSeq.entrySet();
                for (Map.Entry<Character, Integer> entry : entries) {
                    Character key = entry.getKey();
                    Integer value = entry.getValue();
                    if (finalSeq.containsKey(key)) {
                        if (finalSeq.get(key) > value) {
                            finalSeq.put(key, value);
                        }
                    } else {
                    }
                }
            }
            curSeq.clear();
        }
        List<String> ret = new ArrayList<>();
        if (finalSeq.size() > 0)
            finalSeq.forEach((k, v) -> {
                if (v == 1) ret.add(k.toString());
                else for (int i = 0; i < v; ++i) ret.add(k.toString());
            });
        return ret;
    }
}
