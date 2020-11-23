package com.shawn.ss.leetcode;

import java.util.*;

/**
 * 电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * <p>
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * <p>
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * <p>
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * <p>
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * 示例：
 * <p>
 *  
 * <p>
 * <p>
 *  
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 * 提示：
 * <p>
 * ring 和 key 的字符串长度取值范围均为 1 至 100；
 * 两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 字符串 key 一定可以由字符串 ring 旋转拼出。
 * 通过次数11,109提交次数23,234
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StringLockDetech {

    public static void main(String[] args) {
        int rotateSteps = new StringLockDetech().findRotateSteps(
                "caotmcaataijjxi",
                "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"
        );
        System.out.println(rotateSteps);
    }

    /**
     * status : maxStep , curPosInKey , curPosInRing
     *
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) return 0;
        int ret = Integer.MAX_VALUE;
        int n = ring.length();
        int m = key.length();
        Map<Character, List<Integer>> posMap = buildPosMap(ring, m);
//        if (m == 1) {
//            List<Integer> list = posMap.get(key.charAt(0));
//            for (int i : list) {
//                int step = getStep(n, i, 0) + 1;
//                if (ret > step) {
//                    ret = step;
//                }
//            }
//            return ret;
//        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
//        int maxStep = 0;
        char cc = '\0';
        boolean first = true;
        int[] curStatus;
        int[] newStatus = null;//new int[3];
        List<Integer> list;
        do {
//            if (first)
            if (first) {
                curStatus = new int[3];
                cc = key.charAt(0);
            } else {
                curStatus = queue.poll();
                if (curStatus == null) break;
                cc = key.charAt(curStatus[1]);
            }
            list = posMap.get(cc);
//            int min = Integer.MAX_VALUE;
//            newStatus = new int[3];
//
            for (Integer i : list) {
                int step = getStep(n, curStatus[2], i);
//                if (first) {
//                    queue.offer(new int[]{step + 1, 1, i});
//                } else {
//                 if (min > step) {
//                      min = step;
//                      newStatus[0] = curStatus[0] + step + 1;
//                      newStatus[2] = i;
//                  }
//                }
                newStatus = new int[]{curStatus[0]+step+1, curStatus[1] + 1, i};
                if (newStatus[1] < m) {
                    if (newStatus[0] < ret)
                        queue.offer(newStatus);
                } else {
                    if (ret > newStatus[0]) {
                        ret = newStatus[0];
                    }
                }
                System.out.println(ring.charAt(newStatus[2]) + " " + Arrays.toString(newStatus));
            }

            newStatus[1] = curStatus[1] + 1;

            if (first) first = false;

//            maxStep = newStatus[1];
        } while (queue.size() > 0);
        return ret;
    }

    private int getStep(int n, int curStatus, Integer i) {
        int s1 = Math.abs(curStatus - i);
        return Math.min(s1, n - s1);
    }

    private Map<Character, List<Integer>> buildPosMap(String ring, int m) {
        int len = ring.length();
        int k = len / m + 1;
        Map<Character, List<Integer>> ret = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            char c = ring.charAt(i);
            List<Integer> list = ret.computeIfAbsent(c, e -> new ArrayList<>());
            list.add(i);
        }
        return ret;
    }
}
