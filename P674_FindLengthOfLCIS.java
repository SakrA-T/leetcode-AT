package leetCode;

/**
 * @className P674_FindLengthOfLCIS.java
 * @author AT
 * @version Create Time：2019年7月17日 下午3:21:50
 * @question leetcode.p674:最长连续递增序列
 * @describe 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 */
public class P674_FindLengthOfLCIS {

	public static int findLengthOfLCIS(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return len;
		}
		int max_len = 1, temp_len = 1;
		for (int i = 1; i <= len; i++) {
			if (i < len && nums[i] > nums[i - 1]) {
				temp_len++;
			} else {
				if (temp_len > max_len) {
					max_len = temp_len;
				}
				temp_len = 1;
			}
		}
		return max_len;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 3, 5, 7 };
		System.out.println(findLengthOfLCIS(nums));
	}

}
