package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @fileName: P90_SubsetsWithDup.java
 * @author: AT
 * @version: 2019年7月26日 下午8:36:47
 * @question: leetcode.p90:子集 II
 * @describe: 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 */

public class P90_SubsetsWithDup {
	// 思路：对P78做微改即可。
	// 先将数组排序，遍历，当遇到与前一个元素相同的元素时，从当前pre_size的位置往后遍历，至cur_size处，前面的就不用遍历了，否则重复

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> rstList = new ArrayList<>();
		if (nums.length == 0) {
			return rstList;
		}
		Arrays.sort(nums);
		rstList.add(new ArrayList<>());
		int pre_size = 0, cur_size = 0;
		for (int i = 0; i < nums.length; i++) {
			pre_size = cur_size;
			cur_size = rstList.size();
			/*** 所以只是添加了以下这段代码、排序、变量 pre_size ***/
			int beginIndex = 0;
			if (i > 0 && nums[i] == nums[i - 1]) {
				beginIndex = pre_size;
			}
			/***/
			for (int j = beginIndex; j < cur_size; j++) {
				List<Integer> tempList = new ArrayList<>();
				tempList.addAll(rstList.get(j));
				tempList.add(nums[i]);
				rstList.add(tempList);
			}
		}

		return rstList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 9, 1, 9 };
		List<List<Integer>> rstList = subsetsWithDup(nums);
		for (List<Integer> list : rstList) {
			System.out.print("[ ");
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println("]");
		}
	}

}
