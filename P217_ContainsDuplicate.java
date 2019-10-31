package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @className P217_ContainsDuplicate.java
 * @author AT
 * @version Create Time：2019年7月10日 上午10:52:16
 * @question leetcode.p217:存在重复元素
 * @describe 给定一个整数数组，判断是否存在重复元素。
 */
public class P217_ContainsDuplicate {

	public static boolean containsDuplicate(int[] nums) {
		Arrays.sort(nums);
		int len = nums.length;
		// 若未返回，则退出循环时i==j或i=j+1，都是已经被循环覆盖的情况（被检索过了
		// 且会包含len=1（直接返回false）的情况，len=2（i=j+1，循环执行一次）的情况
		for (int i = 0, j = len - 1; i < j; i++, j--) {
			if (nums[i] == nums[i + 1] || nums[j] == nums[j - 1]) {
				return true;
			}
		}
		return false;
	}

	// 优秀代码示例1
	// 将遍历到的元素逐个加入到合集set中，若检测到已存在则直接返回，时间复杂度相同，另一种思路吧
	public boolean containsDuplicate1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		boolean isContains = false;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (!set.contains(nums[i])) {
				set.add(nums[i]);
			} else {
				isContains = true;
				break;
			}
		}
		return isContains;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 5, 4, 6, 9, 7, 8 };
		System.out.println(containsDuplicate(nums) ? true : false);
	}

}
