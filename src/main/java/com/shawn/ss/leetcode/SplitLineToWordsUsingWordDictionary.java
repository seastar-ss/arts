package com.shawn.ss.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-break-ii/
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class SplitLineToWordsUsingWordDictionary {

    public static void main(String[] args) {
        /**
         * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
         * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
         */
        List<String> list;
        List<String> dic = new ArrayList<>(
                Arrays.asList(
                        //                        "a",
                        //                        "aa",
                        "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"
                )
        );
        //        dic.add("apple");
        //        dic.add("pen");
        //        dic.add("applepen");
        //        dic.add("pine");
        //        dic.add("pineapple");
        //        dic.add("");
        //        dic.add("");
        list = new SplitLineToWordsUsingWordDictionary().wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                //                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                dic
        );
        System.out.println(list.size());
    }

    public class TreeNode {
        char val;
        boolean isWord;
        TreeNode[] children;

        TreeNode(char x) {
            val = x;
            isWord = false;
            children = new TreeNode[26];
        }
        //        TreeNode(char x, boolean isWord) {
        //            val = x;
        //            this.isWord = isWord;
        //            children = new TreeNode[26];
        //        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return Collections.emptyList();
        if (wordDict == null || wordDict.size() == 0) return Collections.emptyList();
        TreeNode root = new TreeNode('\0');
        boolean[] stopChar = new boolean[26];
        for (String word : wordDict)
            buildWordTree(root, word, stopChar);
        List<String> ret = new ArrayList<>();
        Map<String, Set<String>> workMap = new HashMap<>();
        Set<String> notWorkSet = new HashSet<>();
        checkSentence(ret, root, root, s, 0, new StringBuilder(), stopChar, workMap, notWorkSet);
        return ret;
    }

    private int checkSentence(
            List<String> ret,
            TreeNode root,
            TreeNode cur,
            String s,
            int index,
            StringBuilder curS,
            boolean[] stopChar,
            Map<String, Set<String>> workMap,
            Set<String> notWorkSet
    ) {
        boolean meet = false;
        if (notWorkSet.contains(s)) {
            return 1;
        } else {
            String s1 = s.substring(index);
            Set<String> set = workMap.get(s1);
            if (set != null) {
                if (index + s1.length() == s.length()) {
                    for (String part : set)
                        ret.add(curS + part);
                } else {

                }
                return 0;
            }
        }
        for (int i = index, n = s.length(); i < n; ++i) {
            char c = s.charAt(i);
            int idx = c - 'a';
            if (!stopChar[idx]) return -1;
            TreeNode child = cur.children[idx];
            if (child == null) {
                meet = false;
                //System.out.println("not work : " + curS.toString());
                break;
            } else {
                if (child.isWord) {
                    int preLen = curS.length();
                    if (curS.length() > 0)
                        curS.append(" ");
                    curS.append(s, index, i + 1);
                    if (i + 1 == n) {
                        meet = true;
                        ret.add(curS.toString());
                    } else {
                        int status = checkSentence(ret, root, root, s, i + 1, curS, stopChar, workMap, notWorkSet);
                        if (status == -1) {
                            return -1;
                        }
                        meet = status == 0;
                    }
                    String key = s.substring(i + 1);
                    if (meet) {
                        String substring = curS.substring(preLen);
                        Set<String> set = workMap.computeIfAbsent(key, e -> new HashSet<>());
                        set.add(substring);
                    } else {
                        notWorkSet.add(key);
                    }
                    int len = curS.length();
                    curS.delete(preLen, len);
                }
                cur = child;
                //                if (!meet) break;
            }
        }
        return meet ? 0 : 1;
    }

    private void buildWordTree(TreeNode root, String word, boolean[] stopChar) {
        for (int i = 0, n = word.length(); i < n; ++i) {
            char c = word.charAt(i);
            int idx = c - 'a';
            stopChar[idx] = true;
            TreeNode child = root.children[idx];
            if (child == null) {
                child = new TreeNode(c);
                root.children[idx] = child;
            }
            root = child;
        }
        root.isWord = true;
    }
}
