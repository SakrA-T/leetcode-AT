package leetCode;

import java.util.Arrays;

/**
 * @className P31_NextPermutation.java
 * @author AT
 * @version Create Time：2019年7月8日 下午7:09:03
 * @question leetcode.p31:实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 */
public class P31_NextPermutation {
	// 分析：如123 -> 132 -> 213 -> 231 -> 312 -> 321(字典序递增)
	public static void reverseArr(int[] nums, int left, int right) {
		if (left == right) {
			return;
		}
		int mid = left + (right - left) / 2, tag = 0;
		while (left <= mid) {
			tag = nums[left];
			nums[left++] = nums[right];
			nums[right--] = tag;
		}
	}

	public static void nextPermutation(int[] nums) {
		int len = nums.length;
		int tag = 0;
		int i = 0, j = 0;
		// 从后查找，找到满足nums[i] < nums[i+1]的最大索引（第一个）及其对应值
		for (i = len - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				tag = nums[i];
				break;
			}
		}
		// 若已经是最大排列了，返回其反转排列
		if (i == -1) {
			reverseArr(nums, 0, len - 1);
			return;
		}
		// 寻找 [i+1,len-1] 中，比tag1大的最小值（最大索引），与其交换。因为要查找的子数组是从大到小排序的，所以直接从后面遍历即可
		// 交换后，nums[i+1,j-1] > nums[j] >= nums[j+1,len-1]
		for (j = len - 1; j > i; j--) {
			if (nums[j] > tag) {
				nums[i] = nums[j];
				nums[j] = tag;
				break;
			}
		}
		// 将 [i+1,len-1] 反转
		reverseArr(nums, i + 1, len - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(nums));
		for (int i = 0; i < 8; i++) {
			nextPermutation(nums);
			System.out.println(Arrays.toString(nums));
		}
	}

}
