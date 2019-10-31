package leetCode;

/**
 * @className P35_SearchInsert.java
 * @author AT
 * @version Create Time：2019年7月8日 下午5:31:26
 * @question leetcode.p35:给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。(你可以假设数组中无重复元素。)
 */
public class P35_SearchInsert {
	public static int searchInsert(int[] nums, int target) {
		int i = 0;
		for (i = 0; i < nums.length && target > nums[i]; i++)
			;
		return i;
	}

	// 优秀代码示例1
	/**
	 ** 思路：二分查找.如果找到值,就返回值的位置。 如果循环结束也没有找到,那就在最后根据要插入的
	 ** target的值和nums[mid]的比较来判断值应该插入在哪里，不是在 mid 前面就是在 mid 后面
	 **/
	public static int searchInsert2(int[] nums, int target) {
		int min = 0, max = nums.length - 1, mid = (min + max) / 2;
		while (min <= max) {
			if (nums[mid] == target)
				return mid;
			else if (target < nums[mid]) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
			mid = (min + max) / 2;
		}

		// 遍历结束之后，根据 target的值来判断应该插入的位置
		if (target < nums[mid])
			return mid;
		else
			return mid + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 3, 5, 6 };
		int target = 5;
		int index = searchInsert(nums, target);
		System.out.println(index);
	}

}
