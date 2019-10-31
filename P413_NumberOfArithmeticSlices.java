package leetCode;

/**
 * @fileName: P413_NumberOfArithmeticSlices.java
 * @author: AT
 * @version: 2019年7月22日 上午10:22:35
 * @question: leetcode.p413:等差数列划分
 * @describe: 编写函数返回给定数组 A 中所有为等差数组的子数组(P,Q)个数。
 * @describe: 其中P+1<Q,(子数组，不是子串)
 */

public class P413_NumberOfArithmeticSlices {
	// 思路：对于k个连续且满足等差条件的元素，每次本段等差数列增加个数t_count值，从第3个数开始（即第一次出现与上一个差值相同的值时）分别为1,2,3,...,k-2。
	public static int numberOfArithmeticSlices(int[] A) {
		int len = A.length;
		if (len < 3) {
			return 0;
		}
		int i = 0, diff = A[1] - A[0], t_diff = 0;
		int count = 0, t_count = 0;
		for (i = 2; i < len; i++) {
			t_diff = A[i] - A[i - 1];
			if (diff != t_diff) {
				diff = t_diff;
				t_count = 0;
			} else {
				t_count++;
				count += t_count;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(numberOfArithmeticSlices(A));
	}

}
