package leetCode;

/**
 * @className P357_CountNumbersWithUniqueDigits.java
 * @author AT
 * @version Create Time：2019年7月16日 下午4:33:29
 * @question leetcode.p357:计算各个位数不同的数字个数
 * @describe 给定一个非负整数n，计算各位数字都不同的数字x的个数，其中0≤x<10^n 。
 */
public class P357_CountNumbersWithUniqueDigits {
	// 从反面考虑，总数个数-重复个数，过于复杂！为什么不从正面考虑！不重复的个数！而且10^10会溢出！参见后面DP
	public static int factorial(int m, int k) {
		int mutiply = 1;
		for (int i = 0; i < k; i++) {
			mutiply *= (m - i);
		}
		return mutiply;
	}

	public static int countNumbersWithUniqueDigits(int n) {
		int cut_count = 0;
		int a = 0, b = 0, c = 0;
		for (int i = 2; i <= n; i++) {
			a = factorial(10 - 1, i - 2);
			// 最高位一定（非0），剩余位中有某一位和最高位重复，但是其余位皆不重复的情况（重复情况被包含在b内）
			// cut_count 为最高位一定且为0的所有重复数值情况
			b = (i - 1) * a;
			// 最高位n一定（非0），且第二高位n-1为0，剩余位中有某一位为0，且其余位不重复的情况（重复情况被包含在b内）
			// 累加是因为考虑以此类推，n-2位也为0的情况
			// 这些除最高位外其余依次为0且除0 外不重复其他数字的情况并不包含在b内
			if (i < 10) {
				c += (i - 2) * factorial(10 - 1, i - 3);
			}
//			System.out.println("a = " + a + " b = " + b + " c = " + c + " d = " + d);
			cut_count = (b + cut_count + c) * 9 + cut_count;
//			System.out.println(cut_count);
//			pre_cut_count = cur_cut_count;
		}
		return (int) (Math.pow(10, n) - (double) cut_count);
	}

	// 优秀代码示例1
	public static int countNumbersWithUniqueDigits1(int n) {
		if (n == 0) {
			return 1;
		}
		int[] dp = new int[11];
		dp[0] = 1;
		// dp
		int per = 1;
		for (int i = 1; i <= 10; i++) {
			// 9*pre 为不含重复数字的i位数的情况，（首位不为0）
			// dp[i - 1] 是其余情况
			// 对一个n位的数字，用0~9的数字去填充，一共有9*9*8.....*(9-(n-1))种不包含重复数字的可能；n=1时是9，0归为没有数字，即n=0的情况
			dp[i] = dp[i - 1] + 9 * per;
			per = per * (10 - i);
		}
		// ERROR有投机取巧的成分，测试案例中n<=10！不对不对！！！！！
		// 当n> 10 时， 显然对于任何一个超过10位的数，我们在n位数中找不到任何一个不含重复数字的数，所以此时dp[n] = dp[10];
		if (n <= 10) {
			return dp[n];
		} else {
			return dp[10];
		}
	}

	// 优秀代码示例2
	public static int countNumbersWithUniqueDigits2(int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 10;
		int[] dp = new int[n + 1];
		// 数值0归于0位数
		dp[0] = 1;
		// 1位数而言，有9种可能
		dp[1] = 9;
		int num = 10;
		for (int i = 2; i <= n; i++) {
			// 对于i=10的情况说明：i=10时，若前9位都已经确定且不重复，那么个位的值是被确定好唯一的，所以i=10和i=9的不重复数值个数相同
			// 解释为高i-1位+低1位
			// 问：为什么前面确定的数位视作高位而不视作低位？
			// 答：因为视作低位的话，dp[1]也应该为10而不是9，高位会出现为0的困扰，完全不方便计算，视作高位就比较好
			dp[i] = dp[i - 1] * (11 - i);
			// 当i=11时dp[11]会被计算为0，而后，无论i是多少dp[i]都是0了，故不会改变了
			System.out.println(dp[i]);
			num += dp[i];
		}
		return num;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 12;
		int count = countNumbersWithUniqueDigits1(n);
		System.out.println(count);
		System.out.println(countNumbersWithUniqueDigits2(n));
	}

}
