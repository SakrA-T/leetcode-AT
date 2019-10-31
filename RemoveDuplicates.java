package leetCode;

/**
 * @className RemoveDuplicates.java
 * @author AT
 * @version Create Time：2019年7月4日 下午5:35:25
 */
public class RemoveDuplicates {
	public static int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int rstLen = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[rstLen - 1]) {
				continue;
			} else {
				// rstLen 记录当前非重复元素的个数，故下一个非重复元素放置的下标为rstLen
				nums[rstLen++] = nums[i];
			}
		}
		return rstLen;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		int len = removeDuplicates(nums);
		for (int i = 0; i < len; i++) {
			System.out.print(nums[i]);
		}
		System.out.println();
	}

}
