package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @className P34_CombinationSum.java
 * @author AT
 * @version Create Time：2019年8月28日 上午9:24:36
 * @question: leetcode.p39:组合总和
 * @describe:给定一个无重复元素的数组candidates和一个目标数target,找出candidates中所有可以使数字和为target的组合。candidates中的数字可以无限制重复被选取。
 * @describe:所有数字（包括 target）都是正整数。解集不能包含重复的组合。
 */

public class P34_CombinationSum {
	// -----------Better Solution----------
	public class Solution {
		private List<List<Integer>> res = new ArrayList<>();
		private int[] candidates;
		private int len;

		public List<List<Integer>> combinationSum(int[] candidates, int target) {
			int len = candidates.length;
			if (len == 0) {
				return res;
			}
			// 优化添加的代码1：先对数组排序，可以提前终止判断
			Arrays.sort(candidates);
			this.len = len;
			this.candidates = candidates;
			findCombinationSum(target, 0, new Stack<>());
			return res;
		}

		private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
			if (residue == 0) {
				// Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
				// 可以直接用栈new一个列表，栈底的index为0
				res.add(new ArrayList<>(pre));
				return;
			}
			// 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
			// residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
			// 这一点基于原始数组是排序数组的前提，因为数组剩余部分，任一数值都比residue小
			for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
				pre.add(candidates[i]);
				// 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
				findCombinationSum(residue - candidates[i], i, pre);
				// 出栈当前元素，之后循环，栈中就不包含当前元素了
				pre.pop();
			}
		}
	}

	// -------------My Solution------------
	private static List<List<Integer>> rstList = new ArrayList<List<Integer>>();

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		if (candidates.length == 0) {
			return rstList;
		}
		Arrays.sort(candidates);
		backTrack(new ArrayList<>(), candidates, 0, target);
		return rstList;
	}

	private static void backTrack(List<Integer> cur_combine, int[] candidates, int s_index, int cur_target) {
		int cur_num = candidates[s_index];
		if (cur_num > cur_target) {
			return;
		}
		int count = 0;
		// 包含未加入cur_num时
		while (cur_target > cur_num) {
			if (s_index < candidates.length - 1) {
				backTrack(cur_combine, candidates, s_index + 1, cur_target);
			}
			cur_combine.add(cur_num);
			cur_target -= cur_num;
			count++;
		}
		// 处理等于的情况
		if (cur_target == cur_num) {
			cur_combine.add(cur_num);
			count++;
			// Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
			rstList.add(new ArrayList<>(cur_combine));
		}
		// 将所有加入的当前数值都删除，回溯为原列表再返回
		for (int i = 1; i <= count; i++) {
			cur_combine.remove(cur_combine.size() - 1);
		}
		return;
	}

	public static void main(String[] args) {
		int[] candidates = { 2, 3, 5 };
		int target = 8;
		System.out.println("-------------My Solution------------");
		List<List<Integer>> rstList = combinationSum(candidates, target);
		for (List<Integer> list : rstList) {
			Integer[] combine = (Integer[]) (list.toArray(new Integer[list.size()]));
			System.out.println(Arrays.toString(combine));
		}
		System.out.println("-----------Better Solution----------");
		P34_CombinationSum obj = new P34_CombinationSum();
		Solution solution = obj.new Solution();
		List<List<Integer>> combinationSum = solution.combinationSum(candidates, target);
		System.out.println(combinationSum);
	}

}
