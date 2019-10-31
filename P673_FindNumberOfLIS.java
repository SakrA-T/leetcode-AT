package leetCode;

import java.util.Arrays;

/**
 * @className P673_FindNumberOfLIS.java
 * @author AT
 * @version Create Time：2019年7月17日 上午10:59:44
 * @question leetcode.p673:最长递增子序列的个数
 * @describe (基于P300) 给定一个未排序的整数数组，找到最长递增子序列的个数。
 */
public class P673_FindNumberOfLIS {
	// 思路1：动态规划
	// dp[len] 每遍历到一个元素，记录当前最长子序列长度
	// ct[len] 记录当前元素最长子序列长度的个数
	// 对于某个元素i,遍历[0...i-1]的所有dp元素值，并记录当前最大长度，
	// 若当前长度大于记录的最大长度，将计数置为当前元素记录个数
	// 若等于计数加上当前记录个数；小于，不计。

	// 注：核心思想在于不管是dp[i]还是ct[i]，数组们所记录的最长子序列长度或个数，都以必须包含当前元素nums[i]为前提，就比较好分析
	public static int findNumberOfLIS(int[] nums) {
		int count = 0;
		int len = nums.length, max_len = 0;
		int[] dp = new int[len];
		int[] ct = new int[len];
		for (int i = 0; i < len; i++) {
			dp[i] = 1;
			ct[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] <= nums[j] || dp[j] + 1 < dp[i])
					continue;
				if (dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					ct[i] = ct[j];
				} else {
					// nums[i]>nums[j]&&dp[j]+1 == dp[i]的情况累加子序列个数
					ct[i] += ct[j];
				}
			}
			if (dp[i] == max_len) {
				count += ct[i];
			}
			if (dp[i] > max_len) {
				// 出现更长的子序列，更新计数值和最长子序列长度
				count = ct[i];
				max_len = dp[i];
			}
		}
		System.out.println(Arrays.toString(ct));
		System.out.println(Arrays.toString(dp));
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 3, 2, 5, 4, 6, 7, 7, 7, 7, 7, 7, 7 };
		System.out.println(findNumberOfLIS(nums));
	}

}
