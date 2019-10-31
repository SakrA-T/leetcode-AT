package leetCode;

import java.util.Arrays;

/**
 * @className MoveZeroes.java
 * @author AT
 * @version Create Time：2019年7月4日 下午6:28:29
 */
public class MoveZeroes {

	public static void moveZeroes(int[] nums) {
		int k = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[k++] = nums[i];
			}
		}
		while (k < nums.length) {
			nums[k++] = 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 1, 0, 3, 12 };
		moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
	}

}
