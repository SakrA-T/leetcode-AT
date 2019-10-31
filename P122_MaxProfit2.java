package leetCode;

/**
 * @className P122_MaxProfit2.java
 * @author AT
 * @version Create Time：2019年7月14日 下午4:35:53
 * @question leetcode.p122:买卖股票的最佳时机 II
 * @describe 设计一个算法来计算所能获取的最大利润。尽可能地完成更多的交易（多次买卖一支股票）。注：不能同时参与多笔交易（必须在再次购买前出售掉之前的股票）。
 */
public class P122_MaxProfit2 {
	// 思路：动态规划
	public static int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int[] max_profit = new int[prices.length];
//		int buy_price = Integer.MAX_VALUE;
		int buy_index = 0;
		int pre_profit = 0;
		int cur_profit = Integer.MIN_VALUE;
		max_profit[0] = 0;
		for (int i = 1; i < prices.length; i++) {
			cur_profit = prices[i] - prices[buy_index];
			// 现在卖
			if (cur_profit > pre_profit) {
				pre_profit = cur_profit;
				max_profit[i] = max_profit[buy_index] + prices[i] - prices[buy_index];
			} else {
				// 之前卖，且现在买入
				buy_index = i;
				pre_profit = 0;
				max_profit[i] = max_profit[i - 1];
			}
		}
		return max_profit[prices.length - 1];
	}

	// 优秀代码示例1———贪心算法
	public static int maxProfit1(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;
		int money = 0;// 最大利润
		// prices[i]>prices[i-1] 则交易，否则不交易。
		// 连续交易可以视作当天卖出并买入，并可视作当天卖出后，买入了之后最近的最低价格股票。
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				money += prices[i] - prices[i - 1];
			}
		}
		return money;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = { 7, 1, 5, 3, 6, 4, 9, 8, 2, 1, 2, 7, 6, 7, 1, 9, 7, 9, 6, 5, 7, 2, 1, 4, 3, 4, 6 };
		System.out.println(maxProfit1(prices));
	}

}
