package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className P300_LengthOfLIS.java
 * @author AT
 * @version Create Time：2019年7月16日 下午8:08:04
 * @question leetcode.p300:最长上升子序列
 * @describe 给定一个无序的整数数组，找到其中最长上升子序列的长度。（可不连续）
 */
public class P300_LengthOfLIS {
	static List<Integer> list = new ArrayList<>();

	// 思路1：动态规划
	public static int lengthOfLIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int len = 0;
		int[] lenArray = new int[nums.length];
		// lenArray视作dp数组
		for (int i = 0; i < nums.length; i++) {
			lenArray[i] = 1;
			// 往回遍历，查找可以组成当前最长子序列
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i] && lenArray[j] + 1 > lenArray[i]) {
					lenArray[i] = lenArray[j] + 1;
				}
			}
			if (lenArray[i] > len) {
				len = lenArray[i];
			}
		}
		return len;
	}

	// 思路2：动态规划和二分搜索
	// 数组 dp ，存储比较小的元素。
	// 对原序列进行遍历。如果元素大于 dp 中最后一个元素，将它插到最后；否则，用二分搜索覆盖掉比它大的元素中最小的元素。
	// Tip:dp 未必是真实的最长上升子序列，但长度是一样的。
	// 比如[3,1,5,6,2,10,9]，最终 dp 数组保存结果：[1,2,6,9],实际应为[1,5,6,9]

	// 优秀代码示例1
	public static int lengthOfLIS1(int[] nums) {
		int dp[] = new int[nums.length];
		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			// 数组二分搜索有内置函数！
			// tip:(如果dp未排序，结果会是undefined)；如果范围包含多个具有指定值的元素，则不能保证找到哪个元素。
			int index = Arrays.binarySearch(dp, 0, len, nums[i]);
			// 由于可能不会找到，此时返回值会是index = -(insertion point)-1，需要转换得insertion point
			if (index < 0) {
				index = -index - 1;
			}
			dp[index] = nums[i];
			if (index == len) {
				len++;
			}
		}
		return len;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0 };
		System.out.println(lengthOfLIS(nums));
	}

}
