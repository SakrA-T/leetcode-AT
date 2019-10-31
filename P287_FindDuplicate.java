package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @fileName: P287_FindDuplicate.java
 * @author: AT
 * @version: 2019年7月22日 下午8:31:28
 * @question: leetcode.p287:寻找重复数 **抽屉原理or鸽子洞原理**
 * @describe: 给定一个包含n+1个整数的数组nums，其数字都在1到n之间（包括1和n，但并不是全都有），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * @require : 不能更改原数组（假设数组是只读的）。
 * @require : 只能使用额外的 O(1) 的空间。
 * @require : 时间复杂度小于 O(n^2) 。
 * @require : 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */

public class P287_FindDuplicate {
	// 思路1：伪链表+快慢指针，遍历到i>=nums.length时，使i=i%(nums.length);
	// Error：会出现无法得出结果，一直循环的情况
	// 若视作循环的话，应该是相同的数叠起来围成环，算法默认首尾相连成环，是不对的，会死循环。参考改进方法：优秀代码示例1
	public static int findDuplicate2(int[] nums) {
		int i = 0, j = 1;
		while (!(i != j && nums[i] == nums[j])) {
			i = (i + 1) % (nums.length);
			j = (j + 2) % (nums.length);
		}
		return nums[i];
	}

	// 不符合限制条件的思路：使用集合、排序
	public static int findDuplicate(int[] nums) {
		Set<Integer> numSet = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (numSet.contains(nums[i])) {
				return nums[i];
			}
			numSet.add(nums[i]);
		}
		return -1;
	}

	// 优秀代码示例1——弗洛伊德的乌龟和兔子（循环检测）
	// 有点类型思路1，但是思路1可能无限循环，这个不会
	// 思路：对 nums 的遍历方式，即对于每对索引 i 和值 vi​ 而言，“下一个”被遍历的数值 vj，位于索引 vi处，将此问题减少到循环检测。
	// 1. 因为 nums 中的每个数字都在1和n之间，所以它必定指向存在的索引。
	// 2. 由于0不会作为nums中的值出现，所以nums[0] 不能作为循环的一部分。
	public static int findDuplicate1(int[] nums) {
		// 找到快慢指针的交点
		int tortoise = nums[0];
		int hare = nums[0];
		do {
			// hare每次都往前走两步，tortoise每次往前走一步，差距就逐渐拉开了
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);
		System.out.println("tortoise = " + tortoise);
		// 找到环的“入口”
		int ptr1 = nums[0];
		int ptr2 = tortoise;
		while (ptr1 != ptr2) {
			ptr1 = nums[ptr1];
			ptr2 = nums[ptr2];
		}
		return ptr1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 3, 5, 4, 2, 7, 6, 1 };
		System.out.println(findDuplicate1(nums));
	}

}
