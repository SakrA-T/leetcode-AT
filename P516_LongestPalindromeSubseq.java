package leetCode;

/**
 * @className P516_LongestPalindromeSubseq.java
 * @author AT
 * @version Create Time：2019年7月19日 上午10:18:58
 * @question leetcode.p516:最长回文子序列
 * @describe 定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 */
public class P516_LongestPalindromeSubseq {
	// 思路1：转换为求LCS
	// 相当于原字符串s，与倒置后所得字符串s'，计算两个字符串的最长公共子序列。
	// 超出时间限制
	public static int LCS(String s1, int n1, String s2, int n2) {
		// 迭代
//		int len1 = 0, len2 = 0;
//		int i = n1-1,j = n2-1;
//		while (i >= 0 && j >= 0) {
//			if (s1.charAt(i) == s2.charAt(j)) {
//				return LCS(s1, i - 1, s2, j - 1) + 1;
//			} else {
//				len1 = LCS(s1, i, s2, j - 1);
//				len2 = LCS(s1, i - 1, s2, j);
//				return Math.max(len1, len2);
//			}
//		}
		// 递归
		// 必须用[1...n1][1...n2]来存储值，[0,0][0,1][1,0]默认为0，否则的话在i-1和j-1关于0 的边界处很难处理
		int[][] dp = new int[n1 + 1][n2 + 1];
		for (int i = 1; i <= n1; i++) {
			for (int j = 1; j <= n2; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[n1][n2];
	}

	public static int longestPalindromeSubseq1(String s) {
		int len = s.length();
		String _s = new StringBuffer(s).reverse().toString();
		return LCS(s, len, _s, len);
	}

	// 思路2：动态规划
	// 优秀代码示例
	// 注意遍历顺序，i 从最后一个字符开始往前遍历，j 从 i + 1 开始往后遍历，这样可以保证每个子问题都已经算好了
	public static int longestPalindromeSubseq(String s) {
		int len = s.length();
		// dp[i][j] 表示[i...j]中最长回文子序列的长度
		int[][] dp = new int[len][len];
		int i = 0, j = 0;
		for (i = len - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (j = i + 1; j < len; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][len - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "bbbab";
		System.out.println(longestPalindromeSubseq1(s));
	}

}
