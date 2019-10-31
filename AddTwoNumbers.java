package leetCode;

import java.util.Arrays;

/**
 * @className AddTwoNumbers.java
 * @author SakrA
 * @version 创建时间：2019年3月19日 上午9:20:41
 */
public class AddTwoNumbers {

	public static int[] twoSum(int[] nums, int target) {
		Arrays.sort(nums);
		int i = 0, j = 0;
		int[] rst = new int[] { -1, -1 };
		for (i = 0; i < nums.length - 1; i++) {

			for (j = i + 1; j < nums.length - 1 && (nums[i] + nums[j] < target); j++)
				;
			if (nums[i] + nums[j] == target) {
				rst[0] = i;
				rst[1] = j;
			}

			else
				continue;
		}
		return rst;
	}

	public static void main(String[] args) {
		int[] rst = new int[2];
		rst = twoSum(new int[] { 3, 2, 4 }, 6);
		System.out.println(Arrays.toString(rst));
	}
}
