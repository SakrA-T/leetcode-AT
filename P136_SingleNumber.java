package leetCode;

import java.util.Arrays;

/**
 * @className P136_SingleNumber.java
 * @author AT
 * @version Create Time：2019年7月10日 下午10:25:11
 * @question leetcode.p136:只出现一次的数字
 * @describe 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * @require 线性时间复杂度，不使用额外空间。
 */
public class P136_SingleNumber {
	// 思路1：排序，遍历查找(非线性时间复杂度，>=O(nlogn))
	public static int singleNumber(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] != nums[i + 1]) {
				return nums[i];
			}
			i++;
		}
		return nums[nums.length - 1];
	}

	// 思路2：位操作
	// 如果我们对 0 和二进制位做 XOR(异或) 运算，得到的仍然是这个二进制位
	// a^0 = a
	// 如果我们对相同的二进制位做 XOR 运算，返回的结果是 0
	// a^a = 0
	// XOR 满足交换律和结合律
	// a^b^a = (a^a)^b = 0^b = b
	// 所以我们只需要将所有的数进行 XOR 操作，得到那个唯一的数字，即为所求。

	// 优秀代码示例1
	public static int singleNumber1(int[] nums) {
		int single = 0;
		for (int num : nums) {
			single ^= num;
		}
		return single;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 4, 1, 2, 1, 2 };
		int single = singleNumber1(nums);
		System.out.println(single);
	}

}
