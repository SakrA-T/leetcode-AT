package leetCode;

import java.util.Arrays;

/**
 * @className P268_MissingNumber.java
 * @author AT
 * @version Create Time：2019年7月10日 下午9:58:14
 * @question leetcode.p268:缺失数字
 * @describe 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 */
public class P268_MissingNumber {
	// 排序
	public static int missingNumber(int[] nums) {
		Arrays.sort(nums);
		// 其实不用折半直接遍历，因为排序的时间复杂度已经为（至少）O(nlogn)了
		// 折半查找index和nums[index]不匹配但index-1和nums[index-1]匹配的元素
		int left = 0, right = nums.length - 1, mid = 0;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (mid - 1 >= 0 && nums[mid] > mid && nums[mid - 1] == mid - 1) {
				return mid;
			} else if (nums[mid] == mid) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println("left = " + left + " right = " + right + " mid = " + mid);
		if (mid == nums[mid]) {
			return mid + 1;
		}
		return mid;
	}

	// 优秀代码示例1————位运算
	// 有想法！！！
	// 我们知道缺失数组中有n个数，并且缺失的数在[0..n]中。因此我们可以先得到[0..n]的异或值，再将结果对数组中的每一个数进行一次异或运算。
	// 未缺失的数在[0..n]和数组中各出现一次，因此异或后得到0。
	// 而缺失的数字只在[0..n]中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。

	// 可以利用下标是[0,n-1]，将结果的初始值设为n，每次异或下标：res ^=nums[i]^i;
	// 或将结果初始值设为0，每次异或下标+1;两种方法都会在遍历结束时同时异或了缺失数组元素及[0...n]
	public static int missingNumber1(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			res = res ^ nums[i] ^ (i + 1);
		}
		return res;
	}

	// 优秀代码示例2————算术
	// 很秀！！！
	public static int missingNumber2(int[] nums) {
		int ans = (1 + nums.length) * nums.length / 2;
		for (int i : nums) {
			ans -= i;
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 4, 6, 9, 2, 3, 5, 7, 8, 1 };
		System.out.println(missingNumber(nums));
	}

}
