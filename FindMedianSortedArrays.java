package leetCode;

import java.util.Arrays;

/**
 * @className FindMedianSortedArrays.java
 * @author SakrA
 * @version 创建时间：2019年3月24日 上午9:54:50
 */
public class FindMedianSortedArrays {
	// 两数组元素个数并不一定相同，不能用分治法求解，不仅很麻烦，而且求不出来；
	// 合并后求解已是极好的求解方法了，而且时间复杂度控制在O(m+n)
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double median = 0;
		int len1 = nums1.length, len2 = nums2.length, mergeLen = len1 + len2;
		int[] mergeArr = new int[mergeLen];
		int mid = mergeLen / 2;
		int i = 0, j = 0, k = 0;
		while (i < len1 && j < len2) {
			if (nums1[i] <= nums2[j]) {
				mergeArr[k++] = nums1[i++];
			} else {
				mergeArr[k++] = nums2[j++];
			}
		}
		while (i < len1) {
			mergeArr[k++] = nums1[i++];
		}
		while (j < len2) {
			mergeArr[k++] = nums2[j++];
		}
		if ((len1 + len2) % 2 == 0) {
			median = (mergeArr[mid] + mergeArr[mid - 1]) / 2.0;
		} else {
			median = mergeArr[mid];
		}
		return median;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = { 1, 2 };
		int[] nums2 = { 3, 4 };
		double median = findMedianSortedArrays(nums1, nums2);
		System.out.println(
				"The median of " + Arrays.toString(nums1) + " and " + Arrays.toString(nums2) + " is " + median);
	}

}
