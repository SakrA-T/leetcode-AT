package leetCode;

import java.util.Arrays;

/**
 * @className RotateArray.java
 * @author AT
 * @version Create Time：2019年7月4日 下午6:34:29
 * @question 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 */
public class RotateArray {
	// 思路：三次翻转
	// 反思：明明可以写reverse函数代替三次重复for......o((>ω< ))o，见示例2
	public static void rotate(int[] nums, int k) {
		int i = 0, j = 0;
		int temp = 0;
		int left = nums.length - k;
		// 处理 k>nums.length 的情况
		k = k % nums.length;
		for (i = 0; i < left / 2; i++) {
			temp = nums[i];
			nums[i] = nums[left - 1 - i];
			nums[left - 1 - i] = temp;
		}
		for (j = 0; j < k / 2; j++) {
			temp = nums[j + left];
			nums[j + left] = nums[nums.length - 1 - j];
			nums[nums.length - 1 - j] = temp;
		}
		for (i = 0; i < (nums.length) / 2; i++) {
			temp = nums[i];
			nums[i] = nums[nums.length - 1 - i];
			nums[nums.length - 1 - i] = temp;
		}
	}

	// 优秀代码示例1
	public void rotate1(int[] nums, int k) {
		int num = k % nums.length;
		if (num == 0)
			return;
		else {
			// 空间复杂度
			int[] tempn = new int[nums.length * 2];
			// 函数：arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
			// 将数组从指定的源数组(从指定位置开始)复制到目标数组的指定位置。
			System.arraycopy(nums, 0, tempn, 0, nums.length);
			System.arraycopy(nums, 0, tempn, nums.length, nums.length);
			// 将tempn[] 填充为两个nums数组拼接，然后截取从 nums.length-num 开始，长度为nums.length的数组复制到nums中
			System.arraycopy(tempn, nums.length - num, nums, 0, nums.length);
		}
	}

	// 优秀代码示例2
	public void rotate2(int[] nums, int k) {
		int len = nums.length;
		k %= len;
		if (len == 0 || k == 0) {
			return;
		}
		reverse(nums, 0, len - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, len - 1);
	}

	public void reverse(int[] nums, int i, int j) {
		while (i < j) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
			i++;
			j--; // 这个很重要，不然的话会相当于翻转过来又翻转回去，结果会是原数组不变
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1 };
		int k = 3;
		rotate(nums, k);
		System.out.println(Arrays.toString(nums));
	}

}
