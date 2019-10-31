package leetCode;

import java.util.Arrays;

/**
 * @className P88_MergeTwoArray.java
 * @author AT
 * @version Create Time：2019年7月15日 上午11:03:25
 * @question leetcode.p88:合并两个有序数组
 * @describe 给定两个有序整数数组nums1和nums2，将nums2合并到nums1中，使得num1成为一个有序数组。
 * @describe 初始化nums1和nums2的元素数量分别为m和n。假设nums1有足够>=的空间。
 */
public class P88_MergeTwoArray {
	// 思路1：将nums2中元素复制到nums1中，并对nums1中[0,m+n)范围内元素进行排序
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		for (int i = 0; i < n; i++) {
			nums1[m + i] = nums2[i];
		}
		// System.arraycopy(nums2, 0, nums1, m, n);
		Arrays.sort(nums1, 0, n + m);
	}

	// 思路2：双指针，从后往前（从前往后需要将num1中原m个元素复制保存）
	// p,q 分别指向两个数组遍历到的位置
	public static void merge1(int[] nums1, int m, int[] nums2, int n) {
		int p = m - 1, q = n - 1, k = m + n - 1;
		while (p >= 0 && q >= 0) {
			nums1[k--] = (nums1[p] > nums2[q]) ? nums1[p--] : nums2[q--];
		}
		// 若最终nums2遍历结束，nums1未结束，则无所谓，因为nums1剩余元素就在原数组中
		// 但若nums2有剩余，则需要将剩余元素复制到nums1中，剩余元素为[0,q+1)
		// 若q+1为0，即nums2已经遍历结束，则不会执行复制，故无需多余条件
		System.arraycopy(nums2, 0, nums1, 0, q + 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = { 2, 0 };
		int m = 1;
		int[] nums2 = { 1 };
		int n = 1;
		merge1(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));
	}

}
