package leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @className CheckSubarraySum.java
 * @author SakrA
 * @version 创建时间：2019年3月26日 下午8:42:48
 */
public class CheckSubarraySum {
	public static boolean checkSubarraySum2(int[] nums, int k) {
		int i = 0, j = 0, len = nums.length;
		if (len == 1) {
			return false;
		}
		if (k == 0) {
			for (; i < len; i++) {
				if (nums[i] == 0 && i + 1 < len && nums[i + 1] == 0) {
					return true;
				}
			}
			return false;
		}
		int[][] sumArr = new int[len][len];
		for (i = 0; i < len; i++) {
			sumArr[i][i] = nums[i];
		}
		for (i = len - 2; i >= 0; i--) {
			for (j = i + 1; j < len; j++) {
				sumArr[i][j] = sumArr[i + 1][j] + nums[i];
				if (sumArr[i][j] == 0) {
					return true;
				} else if (sumArr[i][j] % k == 0) {
					return true;
				}
			}
		}
		return false;
	}

	// 优秀代码示例
	/**
	 * 在每个索引位置i, 计算当前和对k的mod值, 假设在索引x处, sum[0~x] = m*k + mod_x; 在索引y处, sum[0~y] =
	 * n*k + mod_y; 如果mod_x == mod_y且y-x > 1说明sum[x+1~y] 即为一个符合要求的连续子数组,
	 * 用map来保存每个mod值对应的索引, 一旦出现新的mod值出现 在map中, 判断索引差是否大于1. 注意特殊情况: 1)
	 * 当nums中有连续0,无论k为何值都是正确的; 2) 除情况1之外出现k为0都是错误的; 3) k为负数也是可能的, 但是要将其变为对应正数来求mod.
	 * 此外需要在map中初始化<0,-1>, 其原因在于解决到达某个i时sum恰好可以整除k的情况, 选择-1 的原因是要求连续子数组长度大于等于2,
	 * 这样可以避免第一个数字为0的情况
	 **/
	public static boolean checkSubarraySum(int[] nums, int k) {
		int i = 0, j = 0, len = nums.length, remain = 0;
		if (len < 2) {
			return false;
		}
		for (; i < len - 1; i++) {
			if (nums[i] == 0 && nums[i + 1] == 0) {
				return true;
			}
		}
		if (k == 0)
			return false;
		Map<Integer, Integer> remainMap = new HashMap<>();
		remainMap.put(0, -1);
		int sum = 0;
		for (; j < len; j++) {
			sum += nums[j];
			remain = sum % k;
			if (remainMap.containsKey(remain)) {
				if (j - remainMap.get(remain) > 1) {
					return true;
				}
			} else {
				remainMap.put(remain, j);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 0 };
		int k = -1;
		if (checkSubarraySum(nums, k)) {
			System.out.println(
					"There is have subarray in " + Arrays.toString(nums) + " that the summary equal to " + k + ".");
		} else {
			System.out.println(
					"There is haven't subarray in " + Arrays.toString(nums) + " that the summary equal to " + k + ".");
		}
	}

}
