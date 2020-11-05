package com.shawn.ss.leetcode;
/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class StrTransfer {

    public static void main(String[] args) {
        int i = new StrTransfer().ladderLength(
                "hit",
                "cig",
                Arrays.asList(
                        "hot", "dot", "dog", "lot", "log", "cig", "lit", "lig"
                )
        );
        System.out.println(i);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null || wordList.size() == 0)
            return 0;
        if (beginWord.equals(endWord)) return 0;
        int ret = 1;
        int n = wordList.size();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        //        visited.add(beginWord);
        queue.add(beginWord);
        boolean reached = false;
        while (queue.size() > 0 && visited.size() < n && !reached) {

            for (int i = 0, total = queue.size(); i < total; ++i) {
                String q = queue.poll();
                for (String s : words) {
                    if (!s.equals(q) && canJump(s, q)) {
                        if (s.equals(endWord)) {
                            reached = true;
                            System.out.println("reach end word:" + ret);
                            break;
                        } else {
                            if (!visited.contains(s)) {
                                queue.offer(s);
                                visited.add(s);
                            }
                        }
                    }
                }
            }
            ++ret;
            System.out.println("after " + ret + " steps got " + queue);
        }
        return reached ? ret : 0;
    }

    private boolean canJump(String s, String q) {
        if (s.length() == q.length()) {
            int dif = 0;
            for (int i = 0, n = s.length(); i < n; ++i) {
                if (s.charAt(i) != q.charAt(i)) {
                    dif++;
                    if (dif > 1) return false;
                }
            }
            return dif == 1;
        }
        throw new IllegalArgumentException(s + " has different length from " + q);
    }
}
