package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P78_Subsets.java
 * @author: AT
 * @version: 2019年7月25日 下午10:21:02
 * @question: leetcode.p78:子集
 * @describe: 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的不同子集（幂集）。
 */

public class P78_Subsets {
	// 循环枚举，（空集也算子集），对于每个元素，将新元素添加到之前的所有列表中构成 新列表，并保存自身为一个新列表，时间复杂度O(n^2)
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> rstList = new ArrayList<>();
		// 加入空集
		rstList.add(new ArrayList<>());
		for (int i = 0; i < nums.length; i++) {
			// 不实现保存rstList的当前大小的话，用于在循环中会向rstList中加入新列表，会死循环
			int size = rstList.size();
			for (int j = 0; j < size; j++) {
				List<Integer> tempList = new ArrayList<>();
				tempList.addAll(rstList.get(j));
				tempList.add(nums[i]);
				rstList.add(tempList);
			}
		}

		return rstList;
	}

	// 优秀代码示例1——转换为二进制位运算（很强了！！！）
	// 思路：集合的每个元素，都有可以选或不选(对于n个数字的数组，共2^n个子集)，用二进制和位运算，可以很好的表示。
	public static List<List<Integer>> binaryBit(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// 1 << nums.length 等价于 2^nums.length
		for (int i = 0; i < (1 << nums.length); i++) {
			List<Integer> sub = new ArrayList<Integer>();
			for (int j = 0; j < nums.length; j++)
				// 以0~(2^n)-1，2^n个n位二进制数，对应于所有子集，从后往前第 j 个二进制位为 0 表示不放入第 j 个元素,同理1表示放入。
				// " ((i >> j) & 1) == 1 " 表示对于二进制数i，从低位到高位逐个取其二进制位，并判断是否为1，若为1则放入对于nums中的元素。
				if (((i >> j) & 1) == 1)
					sub.add(nums[j]);
			res.add(sub);
		}
		return res;
	}

	// 优秀代码示例3——回溯算法
	public static List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(0, nums, res, new ArrayList<Integer>());
		return res;

	}

	private static void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
		res.add(new ArrayList<>(tmp));
		for (int j = i; j < nums.length; j++) {
			tmp.add(nums[j]);
			backtrack(j + 1, nums, res, tmp);
			// 去掉上一步tmp.add添加的那个元素
			tmp.remove(tmp.size() - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 2, 1, 7 };
//		List<List<Integer>> rstList = subsets(nums);
		List<List<Integer>> rstList1 = binaryBit(nums);
//		System.out.println("[");
		for (List<Integer> list : rstList1) {
			System.out.print("[ ");
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println("]");
		}
//		System.out.println("]");
	}

}
