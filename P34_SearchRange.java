package leetCode;

import java.util.Arrays;

/**
 * @className P34_SearchRange.java
 * @author AT
 * @version Create Time：2019年7月10日 下午9:15:33
 * @question leetcode.p34:在排序数组中查找元素的第一个和最后一个位置
 * @describe 给定一个升序整数数组nums，和一个目标值target。找出给定目标值在数组中的开始位置和结束位置。若数组中不存在目标值，返回[-1,-1]。要求时间复杂度O(logn)
 */
public class P34_SearchRange {
	// 思路：折半查找target值是否在nums中，若在则返回index，往index的左右两边分别遍历查找边界
	public static int binarySearch(int[] nums, int target) {
		int left = 0, right = nums.length - 1, mid = 0;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	public static int[] searchRange(int[] nums, int target) {
		int[] range = { -1, -1 };
		int index = binarySearch(nums, target);
		System.out.println("index = " + index);
		if (index == -1) {
			return range;
		}
		int i = index, j = index;
//		while ((i >= 0 && nums[i] == target) || (j < nums.length && nums[j] == target)) {
		// 此处不需要一个大while包含，只允许逐个循环i和j即可，效果是一样的
		while (i >= 0 && nums[i] == target) {
			i--;
		}
		while (j < nums.length && nums[j] == target) {
			j++;
		}
//		}
		range[0] = i + 1;
		range[1] = j - 1;
		return range;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 5, 7, 7, 8, 8, 8, 8, 10 };
		int target = 10;
		int[] range = searchRange(nums, target);
		System.out.println(Arrays.toString(range));
	}

}
