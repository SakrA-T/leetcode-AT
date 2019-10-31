package leetCode;

import java.util.Arrays;

/**
 * @fileName: P75_ClassifyColors.java
 * @author: AT
 * @version: 2019年7月25日 下午12:07:12
 * @question: leetcode.p75:颜色分类
 * @describe: 整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * @describe: 一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * @require: 不能使用代码库中的排序函数；实现常数空间的一趟扫描算法。
 */

public class P75_ClassifyColors {
	// 思路：三指针，单指针遍历，双指针记录前后已固定的位置，遇到0往前放，遇到2往后放，1自然就在中间了
	private static void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	// p所在位置之前都是0，q所在位置之后都是2
	public static void sortColors(int[] nums) {
		int p = 0, q = nums.length - 1, i = 0;
		while (i <= q) {
			if (nums[i] == 0) {
				swap(nums, p, i);

				while (p <= q && nums[p] == 0)
					p++;
				i = p;
			} else if (nums[i] == 2) {
				swap(nums, q, i);
				while (p <= q && nums[q] == 2)
					q--;
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 2 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
	}

}
