package leetCode;

/**
 * @className P33_SearchRotateArray.java
 * @author AT
 * @version Create Time：2019年7月9日 上午11:23:11
 * @question leetcode.p33:搜索旋转排序数组
 * @describe 假设按照升序排序的数组在预先未知的某个点上进行了旋转。搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1。
 */
public class P33_SearchRotateArray {
	// 思路：二分查找，找到旋转的点end；判断target是否小于nums[0]，
	// 若小于，则在[end+1,len-1]二分查找，若大于则在[0,end]二分查找，适当剪枝
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
			} else {
				// 问：如果mid+1或mid-1正好排除了要查找的点，是后（前）一个点，会不会导致无法得出正确结果？
				// 答：不会，如果mid正好是要查找的点会在第一个if判断中被选定并返回。
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

	public static int search(int[] nums, int target) {
		int rst = -1;
		if (nums.length == 0) {
			return -1;
		}
		if (target == nums[0]) {
			return 0;
		}
		int end = searchEnd(nums);
		System.out.println("end = " + end);
		if (target > nums[end]) {
			return -1;
		}
		if (target > nums[0]) {
			rst = searchTarget(nums, 0, end, target);
		} else {
			rst = searchTarget(nums, end + 1, nums.length - 1, target);
		}
		return rst;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 5, 6, 7, 8, 9, 11, 15 };
		int target = 10;
		int rst = search(nums, target);
		System.out.println("target index = " + rst);
	}

}
