package com.shawn.ss.leetcode;

public class StockBuy {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int ret = 0;
        for (int i = 1, n = prices.length; i < n; ++i) {
            if (prices[i - 1] < prices[i]) {
                ret += prices[i] - prices[i - 1];
            }
        }
        return ret;
    }
}
