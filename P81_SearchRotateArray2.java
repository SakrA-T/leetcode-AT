package leetCode;

/**
 * @className P81_SearchRotateArray2.java
 * @author AT
 * @version Create Time：2019年7月9日 下午12:16:55
 * @question leetcode.p81:搜索旋转排序数组 II（在p33的基础上添加nums可能包含重复元素。）
 */
public class P81_SearchRotateArray2 {
	// 算法复杂度O(n)但其实是O(n/2)+O(logn)比直接遍历稍微好一些些
	public static int searchEnd(int[] nums) {
		int len = nums.length;
		int left = 0, right = len - 1;
		// 若为偶数个数，mid为中间两数的前一个数
		int mid = 0;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (mid + 1 >= len || nums[mid] > nums[mid + 1]) {
				return mid;
			} else if (nums[mid] < nums[left]) {
				right = mid - 1;
			} else if (nums[mid] > nums[left]) {
				left = mid + 1;
			} else {
				// nums[mid]==nums[left]说明nums[left]==nums[mid,right]或nums[left,mid]
				// 但并不能确定是哪个，而且可能存在nums[mid]==nums[left]==nums[right]且end可能出现在任意一边
				// 遍历其中一边(左边)
				for (int j = left; j < mid; j++) {
					if (nums[j] > nums[left] && nums[j] > nums[j + 1]) {
						return j;
					}
				}
				left = mid + 1;
			}
		}
		// 实际上正常数组并不会到达这里
		return -1;
	}

	public static int searchTarget(int[] nums, int left, int right, int target) {
		int mid = 0;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (target == nums[mid]) {
				return mid;
			} else if (target > nums[mid]) {
				left = mid + 1;
			} else {
				// 此处不可置为right = mid,left也一样，因为如果在这个循环中已经是right=mid的话，会出现死循环
				right = mid - 1;
			}
		}
		return -1;
	}

	public static boolean search(int[] nums, int target) {
		int rst = -1;
		if (nums.length == 0) {
			return false;
		}
		if (target == nums[0]) {
			return true;
		}
		int end = searchEnd(nums);
		System.out.println("end = " + end);
		if (target > nums[end]) {
			return false;
		}
		if (target > nums[0]) {
			rst = searchTarget(nums, 0, end, target);
		} else {
			rst = searchTarget(nums, end + 1, nums.length - 1, target);
		}
		System.out.println("index = " + rst);
		return rst == -1 ? false : true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 1, 1, 3, 1 };
		int target = 3;
		boolean isExist = search(nums, target);
		System.out.println("target ? " + isExist);
	}

}
