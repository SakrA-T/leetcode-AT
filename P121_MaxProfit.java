package leetCode;

/**
 * @className P121_MaxProfit.java
 * @author AT
 * @version Create Time：2019年7月14日 下午2:59:39
 * @question leetcode.p121:买卖股票的最佳时机
 * @describe 给定一个数组，它的第i个元素是一支给定股票第i天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。注：不能在买入股票前卖出股票；无法完成交易时，返回0。
 * @describe 121/122/123/188/309/502
 */
public class P121_MaxProfit {
	// 思路：可以视作在i<j的情况下，求prices[j]-prices[i]的最大差值。
	// 最大差值最多只有两种情况：1. 最小值与其后最大值产生；2.最大值与其前最小值产生。（实际上并不是，如：[7,2,4,1]）
	public static int maxProfit(int[] prices) {
		if (prices.length <= 1) {
			return 0;
		}
		// min_price和max分别对应迄今为止所得到的最小的谷值和最大的利润
		// 当最小谷值发生改变时，最大利润不一定改变
		int min_price = Integer.MAX_VALUE;
		int max_profit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min_price) {
				min_price = prices[i];
			} else {
				max_profit = Math.max(max_profit, prices[i] - min_price);
			}
		}
		return max_profit;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = { 7, 2, 4, 1 };
		System.out.println(maxProfit(prices));
	}

}
