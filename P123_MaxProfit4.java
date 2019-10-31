package leetCode;

/**
 * @className P123_MaxProfit4.java
 * @author AT
 * @version Create Time：2019年7月15日 下午8:10:21
 * @question leetcode.p123:买卖股票的最佳时机 III
 * @describe 在II的基础上将不限交易次数改为最多可以完成两笔交易。
 */
public class P123_MaxProfit4 {
	public static int maxProfit(int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		int k = 2;
		int[][][] dp = new int[len][k + 1][2];
		for (int j = 1; j <= k; j++) {
			// 第0天，最多可交易j次，当天不持有股票，当前收益。
			dp[0][j][0] = 0;
			dp[0][j][1] = -prices[0];
		}
		for (int i = 0; i < len; i++) {
			// 第i天，不可交易，收益。
			dp[i][0][0] = 0;
			dp[i][0][1] = Integer.MIN_VALUE; // 不可交易不存在持有股票的情况
		}
		int temp = 0;
		for (int i = 1; i < len; i++) {
			for (int j = k; j >= 0; j--) {
				// 当前不持有股票，两种情况：
				// 1.前一天不持有股票，当前保持；
				// 2.前一天持有股票，当前卖出，获得当天价格收益，k-1；
				temp = (j + 1) <= k ? dp[i - 1][j + 1][1] : Integer.MIN_VALUE;
				dp[i][j][0] = Math.max(dp[i - 1][j][0], temp + prices[i]);
				// 当前持有股票，两种情况：
				// 1. 前一天持有股票，当前保持；
				// 2. 前一天不持有股票，当天买入，减去买入成本；
				dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
			}
		}
		// 第len-1天，用完所有交易次数，当天不持有股票，当前收益。
		return dp[len - 1][0][0];
	}

	// 优秀代码示例1
	public static int maxProfit1(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int firstBuy = Integer.MIN_VALUE, firstSell = 0;
		int secondBuy = Integer.MIN_VALUE, secondSell = 0;
		for (int curPrice : prices) {
			if (firstBuy < -curPrice) {
				firstBuy = -curPrice;
			}
			if (firstSell < curPrice + firstBuy) {
				firstSell = curPrice + firstBuy;
			}
			if (secondBuy < firstSell - curPrice) {
				secondBuy = firstSell - curPrice;
			}
			if (secondSell < curPrice + secondBuy) {
				secondSell = curPrice + secondBuy;
			}
		}
		return secondSell;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] prices = { 7, 1, 5, 3, 6, 4, 9, 8, 2, 1, 2, 7, 6, 7, 1, 9, 7, 9, 6, 5, 7, 2, 1, 4, 3, 4, 6 };
		int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
		System.out.println(maxProfit(prices));
	}

}
