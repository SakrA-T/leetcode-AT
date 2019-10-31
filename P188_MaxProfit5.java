package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className P188_MaxProfit5.java
 * @author AT
 * @version Create Time：2019年7月16日 上午9:36:19
 * @question leetcode.p188:买卖股票的最佳时机 IV
 * @describe 在P123的基础上修改限制条件为：最多可以完成 k 笔交易。
 */
public class P188_MaxProfit5 {
	// 思路1：仿照P123
	// 超出内存限制:将 k>len/2 的情况视作允许无数次交易，避免内存爆炸
	public static int maxProfit_inf(int[] prices) {
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

	public static int maxProfit(int k, int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		// 一次交易最少需要两天，k>len/2就相当于可以交易无数次，P122的情况
		if (k > len / 2) {
			return maxProfit_inf(prices);
		}
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

	// 思路2：记录交易次数，并将每次交易收益记录到大小为k的list中，当交易次数>k时，以当前交易收益与list中最小收益对比，只要大于，就替换。
	// 最终结果为最终list中所以收益之和。
	// 时间复杂度O(nlogk)，空间复杂度O(k)
	// ERROR:实际上不是，不符合贪心算法，不应该每次替换最小收益，因为被替换的天里，可能在其中某一天卖出会获得更大收益。
	public static int binarySearch(List<Integer> list, int n) {
		int len = list.size();
		if (len == 0) {
			return 0;
		}
		int left = 0, right = len - 1, mid = 0;
		while (left < right) {
			mid = left + (right - left) / 2;
			// 这边存在等于的情况
			if (n == list.get(mid)) {
				return mid;
			} else if (n > list.get(mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		// 以left==right退出循环，插入点可能在left/right的任意一边，需判断
		// 或以right=left-1退出循环，插入点必在right和left之间
		if (right == -1 || n > list.get(right)) {
			return right + 1;
		} else
			return right;
	}

	public static int maxProfit1(int k, int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		System.out.println(Arrays.toString(prices));
		int max_profit = 0;
		List<Integer> profitList = new ArrayList<>();
		// buy_price 记录最近一次买入的价格；time 记录交易次数；
		// index 记录新收益值应该插入的位置
		// cur_profit 记录当前如果交易的话，可获得的收益（可为负）；
		// pre_profit 记录如果是之前交易的话，可获得的收益；
		int buy_price = prices[0], time = 0, index = 0;
		int cur_profit = 0, pre_profit = 0;
		for (int i = 1; i <= len; i++) {
			if (i < len) {
				cur_profit = prices[i] - buy_price;
			}
			if (i < len && cur_profit > pre_profit) {
				pre_profit = cur_profit;
//				buy_price = 0;
			} else {
				buy_price = (i < len) ? prices[i] : 0;
				if (pre_profit <= 0) {
					continue;
				}
				index = binarySearch(profitList, pre_profit);
				profitList.add(index, pre_profit);
				if (time == k) {
					profitList.remove(0);
				}
				time = (time == k) ? time : (time + 1);
				// 初始为0 ，则包含了当之后买入价格更低时（cur_profit为负<0），会以更低的价格买入
				pre_profit = 0;
			}
		}
		for (Integer item : profitList) {
			max_profit += item;
		}
		return max_profit;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = { 7, 1, 5, 3, 6, 4, 9, 8, 2, 1, 2, 7, 6, 7, 1, 9, 7, 9, 6, 5, 7, 2, 1, 4, 3, 4, 6 };
//		int[] prices = { 3, 2, 6, 5, 0, 3, 1, 4, 9 };
		int k = 4;
		System.out.println(maxProfit1(k, prices));
		System.out.println(maxProfit(k, prices));
	}

}