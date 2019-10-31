package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @className P47_PermuteUnique.java
 * @author AT
 * @version Create Time：2019年7月13日 下午5:19:20
 * @question leetcode.p47:全排列 II
 * @describe 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 */
public class P47_PermuteUnique {
	// 思路1：使用集合Set排除已存在的序列
	private static List<List<Integer>> rstList = new ArrayList<List<Integer>>();
	private static boolean[] vaild;

	private static void backTrack(int count, int[] nums, List<Integer> numList) {
		// 每次都要复制到一个新的列表再添加到rstList中，否则的话，会改变原列表，最终导致rstList中add的所有列表都是指向一个地址处的列表，就都一样
		if (count == nums.length) {
			rstList.add(new ArrayList<>(numList));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (vaild[i] == false) {
				if (i == 0 || nums[i] != nums[i - 1] || vaild[i - 1] == true) {
					vaild[i] = true;
					numList.add(nums[i]);
					backTrack(count + 1, nums, numList);
					vaild[i] = false;
					numList.remove(numList.size() - 1);
				}
			}
		}
	}

	public static List<List<Integer>> permuteUnique(int[] nums) {
		if (nums.length == 0) {
			return rstList;
		}
		Arrays.sort(nums);
		vaild = new boolean[nums.length];
		// 如示例1所示，也可以将列表改为栈，方便出入，backTrack(0, nums, new Stack<>());
		// 因为都是Collections的子类，Stack栈也可以直接用new ArrayList<>(stack)生成对应列表
		backTrack(0, nums, new ArrayList<>());
		return rstList;
	}

	// 优秀代码示例1——回溯算法+适当剪枝
	private static List<List<Integer>> res = new ArrayList<>();
	private static boolean[] used;

	private static void findPermuteUnique(int[] nums, int depth, Stack<Integer> stack) {
		if (depth == nums.length) {
			res.add(new ArrayList<>(stack));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				// 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
				// 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
				// 这种情况跳过即可
				if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
					continue;
				}
				used[i] = true;
				stack.add(nums[i]);
				findPermuteUnique(nums, depth + 1, stack);
				stack.pop();
				used[i] = false;
			}
		}
	}

	public static List<List<Integer>> permuteUnique1(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return res;
		}
		// 修改 1：首先排序，之后才有可能发现重复分支
		Arrays.sort(nums);
		used = new boolean[len];
		findPermuteUnique(nums, 0, new Stack<>());
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 9, 1, 9 };
		List<List<Integer>> permute = permuteUnique(nums);
		for (List<Integer> p : permute) {
			System.out.print("[ ");
			for (Integer num : p) {
				System.out.print(num + " ");
			}
			System.out.println("]");
		}

	}

}
