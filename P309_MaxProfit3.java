package leetCode;

/**
 * @className P309_MaxProfit3.java
 * @author AT
 * @version Create Time：2019年7月15日 上午11:42:19
 * @question leetcode.p309:最佳买卖股票时机含冷冻期
 * @describe (P122基础上添加限制条件):你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。卖出股票后，你无法在第二天买入股票（即冷冻期为1天）。
 */
public class P309_MaxProfit3 {

	public static int maxProfit(int[] prices) {
		int len = prices.length;
		if (len == 0) {
			return 0;
		}
		// dp[i][0] 表示当天结束后不持有股票的情况下，当前收益
		// dp[i][1] 表示当天结束后持有股票的情况下，当前收益
		int[][] dp = new int[len][2];
		dp[0][0] = 0;
		dp[0][1] = -prices[0];
		// 为防止i=1时，i-2越界，以此代替dp[i-2][0]
		int dp_pre_0 = 0;
		for (int i = 1; i < len; i++) {
			// 当前不持有股票，两种情况：
			// 1.前一天不持有股票，当前保持；
			// 2.前一天持有股票，当前卖出，获得当天价格收益；
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
			// 当前持有股票，两种情况：
			// 1. 前一天持有股票，当前保持；
			// 2. 两天前不持有股票，当天买入，减去买入成本；
			// tip:最早是在两天前卖出，当前才可买入；当天可买入的情况还包括前一天不持有股票，且由之前不持有股票的状态转换而来，这种情况等同于两天前不持有股票，故不需要重复考虑。
			dp[i][1] = Math.max(dp[i - 1][1], dp_pre_0 - prices[i]);
			dp_pre_0 = dp[i - 1][0];
		}
		return dp[len - 1][0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = { 7, 1, 5, 3, 6, 4, 9, 8, 2, 1, 2, 7, 6, 7, 1, 9, 7, 9, 6, 5, 7, 2, 1, 4, 3, 4, 6 };
//		int[] prices = { 1, 2, 3, 0, 2 };
		System.out.println(maxProfit(prices));
	}
}
