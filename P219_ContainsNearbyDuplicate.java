package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @className P219_ContainsNearbyDuplicate.java
 * @author AT
 * @version Create Time：2019年7月10日 下午2:24:46
 * @question leetcode.p219:存在重复元素II
 * @describe 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums[i]=nums[j]，并且i和j的差的绝对值<=k。
 */
public class P219_ContainsNearbyDuplicate {
	// 思:1：对于每个元素i，检查其[i+1,i+k]之间是否有与其值相等的元素，若有则直接返回true
	// 都有超时的风险
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		int len = nums.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len && j <= i + k; j++) {
				if (nums[i] == nums[j]) {
					return true;
				}
			}
		}
		return false;
	}
	// 思路2：BST 通过自平衡二叉搜索树来维护一个 k 大小的滑动窗口。
	// BST 中搜索，删除，插入都可以保持O(log k)的时间复杂度，其中 k是BST中元素的个数。
	// 1) 遍历数组，对于每个元素做以下操作：
	// 2) 在 BST 中搜索当前元素，如果找到了就返回 true。
	// 3) 在 BST 中插入当前元素。
	// 4) 如果当前 BST 的大小超过了 k，删除当前 BST 中最旧的元素。

	// 优秀代码示例1
	// 思路：用散列表来维护这个k大小的滑动窗口。（非常优秀的做法，值得借鉴！！！）
	// 1) 遍历数组，对于每个元素做以下操作：
	// 2) 在散列表 set 中搜索当前元素，如果找到了就返回 true。
	// 3) 在散列表中插入当前元素。
	// 4) 如果当前散列表的大小超过了k， 删除散列表中最旧的元素。
	public static boolean containsNearbyDuplicate1(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; ++i) {
			if (set.contains(nums[i]))
				return true;
			set.add(nums[i]);
			if (set.size() > k) {
				// 当满足if条件时，当前遍历到的元素与需要删除的元素下标相差k，j=i-k
				set.remove(nums[i - k]);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 5, 2, 4 };
		int k = 2;
		System.out.println(containsNearbyDuplicate1(nums, k) ? true : false);
	}

}
