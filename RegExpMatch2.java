package leetCode;

/**
 * @className RegExpMatch2.java
 * @author AT
 * @version Create Time：2019年6月26日 下午5:04:16
 */
public class RegExpMatch2 {
//	对于*：匹配零个或多个前面的那一串元素
	static int[][] dp;

	public static int dp(int i, int j, String s, String p) {

		int n = s.length(), m = p.length();
//		System.out.println("i = " + i + ", j = " + j);
		if (dp[i][j] >= 0) {
//			System.out.println("dp[" + i + "]" + "[" + j + "]" + " = " + dp[i][j]);
			return dp[i][j];
		} else {
			if ((j + 1) < m && p.charAt(j + 1) == '*') {
				if (i == n) {
					dp[i][j] = dp(i, j + 2, s, p);
				} else {
					if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
						dp[i][j] = Math.max(dp(i, j + 2, s, p), dp(i + 1, j, s, p));
					} else {
						dp[i][j] = dp(i, j + 2, s, p);
					}
				}
			} else {
				if (i == n) {
					dp[i][j] = 0; // s为空串，但p不为空串且出现字符后不接*的情况时
				} else {
					if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
						dp[i][j] = dp(i + 1, j + 1, s, p);
					} else {
						dp[i][j] = 0;
					}
				}
			}
//			System.out.println("dp[" + i + "]" + "[" + j + "]" + " = " + dp[i][j]);
			return dp[i][j];
		}
	}

	public static boolean isMatch(String s, String p) {
		boolean ismatch = true;
		int n = s.length(), m = p.length();
		dp = new int[n + 1][m + 1];
		// dp[i][j]表示s[i]-s[n-1]与p[j]-p[m-1]是否可匹配，若为1则匹配，为0则不匹配
		// dp[n][m]表示s和p都为空
		int i = 0, j = 0, k = 0;
		for (i = 0; i < n + 1; i++) {
			for (j = 0; j < m + 1; j++) {
				dp[i][j] = -1;
			}
		}
		for (k = 0; k < n; k++) {
			dp[k][m] = 0; // 当s有剩余>1个字符，而p无可匹配字符时，皆为不可匹配
		}
		dp[n][m] = 1; // 若s,p本身为空串，上面dp[k=0][m=0]会将其置为0（不可匹配），实际上是可匹配，此处会加以修正
		if (m >= 1) {
			dp[n][m - 1] = 0;
		}
		if (dp(0, 0, s, p) == 0) {
			ismatch = false;
		}
		return ismatch;
	}

	// 优秀代码示例
	public static boolean isMatch2(String s, String p) {
		// dp[i][j]:s[0..i-1]和p[0..j-1]已经匹配,s的前i个字符和p的前j个字符
		// dp[i][j]=dp[i-1][j-1]: p[j-1]!=* && (s[i-1]==p[j-1] || p[j-1] == .)
		// if p[j-1]==* 并且模式使用0次, dp[i][j] = dp[i][j-2]（j回退）
		// if p[j-1]==* 并且模式多重复1次, dp[i][j] = dp[i-1][j] && (s[i - 1] == p[j - 2] ||
		// p[j - 2] == '.')（i回退且判断当前重复是否匹配）
		// 注意此处是dp[i-1][j]而不是dp[i-1][j-2]或[j-1]，为什么呢，因为重复一次指的是，在之前已经匹配的基础上，再将x*多匹配一次，如果是dp[i-1][j-2]，就表示也抹去了之前可能已经匹配的那一次（若为[j-1]则忽略没有匹配的情况，默认为一定匹配一次），都会出错

		int sLength = s.length();
		int pLength = p.length();
		boolean[][] memory = new boolean[sLength + 1][pLength + 1];
		memory[0][0] = true; // 0 表示空
		// 注意i和j不是同步增长也不需要，也不可以
		for (int i = 0; i <= sLength; i++) {
			// p为空串没有意义,不用计算，boolean默认为false，所以除了i=0之外的，所有j=0的情况，都是false
			for (int j = 1; j <= pLength; j++) {
				if (p.charAt(j - 1) == '*') { // 当前一个字符有可重复标志时，分是否使用该字符的情况

					memory[i][j] = memory[i][j - 2] || (i > 0
							&& (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && memory[i - 1][j]);
				} else {
					// 前(i-1,j-1)字符串是否匹配 && 当前第(i,j)个字符，s[i-1]，p[j-1]是否可匹配
					// 若i = 0，即s为空串，且当前可匹配字符不为*
					memory[i][j] = i > 0 && memory[i - 1][j - 1]
							&& (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
				}
//				System.out.println("memory[" + i + "]" + "[" + j + "]" + " = " + memory[i][j]);
			}
		}

		return memory[sLength][pLength];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean ismatch = false;

		String s_t1 = "mississippi";
		String p_t1 = "mis*is*p*.";
		ismatch = isMatch2(s_t1, p_t1);
		System.out.println("Is match ? (false) " + ismatch);

		String s_t2 = "a";
		String p_t2 = "a*";
		ismatch = isMatch2(s_t2, p_t2);
		System.out.println("Is match ? (true) " + ismatch);

		String s_t3 = "acaabbac";
		String p_t3 = "a*.*b*.*a*ba*a*";
		ismatch = isMatch2(s_t3, p_t3);
		System.out.println("Is match ? (false) " + ismatch);

//		String s_t4 = "aaaab";
//		String p_t4 = "c*a*b";
//		ismatch = isMatch(s_t4, p_t4);
//		System.out.println("Is match ? (true) " + ismatch);
	}

}
