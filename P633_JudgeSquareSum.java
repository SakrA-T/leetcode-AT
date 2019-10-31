package leetCode;

import java.util.Arrays;

/**
 * @fileName: P372_SuperPow.java
 * @author: AT
 * @version: 2019年7月22日 上午09:55:42
 * @question: leetcode.p633:平方数之和
 * @describe: 给定一个非负整数c，你要判断是否存在两个整数a和b，使得 a2 + b2 = c。
 * @example: 如：1 * 1 + 2 * 2 = 5 -> true
 */

public class P633_JudgeSquareSum {
	static int[] addend = new int[2];

	public static boolean judgeSquareSum(int c) {
		int left = 0, right = (int) Math.sqrt(c);
		int sum = 0;
		while (left <= right) {
			sum = left * left + right * right;
			if (sum == c) {
				addend[0] = left;
				addend[1] = right;
				return true;
			} else if (sum < c) {
				left++;
			} else {
				right--;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c = 0;
		System.out.println(judgeSquareSum(c) + ": " + Arrays.toString(addend));
	}

}
