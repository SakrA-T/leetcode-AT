package leetCode;

import java.util.Arrays;

/**
 * @className P977_SortedArraySquares.java
 * @author AT
 * @version Create Time：2019年7月15日 上午11:28:18
 * @question leetcode.p977:有序数组的平方
 * @describe 给定一个按非递减顺序排序的整数数组A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 */
public class P977_SortedArraySquares {
	// 思路1：全部平方保存，再排序
	// 时间复杂度O(nlogn)
	public static int[] sortedSquares(int[] A) {
		int[] ans = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			ans[i] = A[i] * A[i];
		}
		Arrays.sort(ans);
		return ans;
	}

	// 思路2：双指针，两端遍历（正负元素以绝对值比较大小，计算并记录较大值平方）
	// 时间复杂度O(n)
	public static int[] sortedSquares1(int[] A) {
		int len = A.length;
		int[] ans = new int[len];
		int left = 0, right = len - 1, k = len - 1;
		while (left <= right) {
			if (Math.abs(A[left]) >= Math.abs(A[right])) {
				ans[k--] = A[left] * A[left];
				left++;
			} else {
				ans[k--] = A[right] * A[right];
				right--;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { -4, -1, 0, 3, 10 };
		int[] ans = sortedSquares1(A);
		System.out.println(Arrays.toString(ans));
	}

}
