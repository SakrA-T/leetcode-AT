package leetCode;

/**
 * @fileName: P1016_QueryString.java
 * @author: AT
 * @version: 2019年7月24日 下午8:15:31
 * @question: leetcode.p1016:子串能表示从1到N数字的二进制串
 * @describe: 给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N;
 * @describe: 如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S的子串，就返回 true，否则返回 false。
 */

public class P1016_QueryString {
	// 思路:1-N将其逐个转换为二进制字符串，在S中查找是否有该子串
	private static String numberToBinary(int m) {
		int mod = 0;
		String binStr = "";
		while (m != 0) {
			mod = m % 2;
			binStr = mod + binStr;
			m /= 2;
		}
		return binStr;
	}

	public static boolean queryString(String S, int N) {
		String binStr = "";
		for (int i = 1; i <= N; i++) {
			// 有内置函数直接转十进制为二进制字符串噢~ binStr = Integer.toBinaryString(i);
			binStr = numberToBinary(i);

			System.out.println("binStr = " + binStr);
			if (S.indexOf(binStr) == -1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "0110";
		int N = 3;
		System.out.println(queryString(S, N));
	}

}
