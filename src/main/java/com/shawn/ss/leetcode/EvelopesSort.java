package com.shawn.ss.leetcode;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/*
https://leetcode-cn.com/problems/russian-doll-envelopes/

给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

说明:
不允许旋转信封。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/russian-doll-envelopes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class EvelopesSort {

    public static void main(String[] args) {

    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int ret = 1;
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        SortedMap<Integer, Integer> hIndex = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            int[] ev = envelopes[i];
            hIndex.put(ev[1], i);
        }
        int[] dp = new int[n];
        int[] last = envelopes[0];
        for (int i = 1; i < n; ++i) {
            if (envelopes[i][0] > last[0] && envelopes[i][1] > last[1]) {
                ret++;
                last = envelopes[i];
            }
        }

        return ret;
    }

}
