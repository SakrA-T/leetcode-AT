package leetCode;

/**
 * @fileName: P50_MyPow.java
 * @author: AT
 * @version: 2019年7月20日 下午9:51:18
 * @question: leetcode.p50:Pow(x, n)
 * @describe: 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 */

public class P50_MyPow {

	public static double myPow(double x, int n) {
		if (n == 0 || x == 1) {
			return 1.0;
		}
		if (x == -1) {
			return (n % 2 == 0) ? 1.0 : (-1.0);
		}
		// 注意此处要对n进行转换，否则若n为-2^31时，执行n=-n会溢出出错
		double N = n;
		boolean isMinus = false;
		double rst = 1, temp = 1;
		if (N < 0) {
			isMinus = true;
			N = -N;
		}
		int i = 1, t = 0;
		while (t < N && Double.isFinite(temp)) {
			i = 1;
			temp = x;
			while ((double) i * 2 + t <= N) {
				if (Double.isInfinite(temp)) {
					break;
				}
				temp = temp * temp;
				i = i * 2;
			}
			t += i;
			rst *= temp;
		}

		return isMinus ? (1.0 / rst) : rst;
	}

	// 优秀代码示例1
	// 使用递归，比我的迭代相对简洁明了一些
	private static double fastPow(double x, long n) {
		if (n == 0) {
			return 1.0;
		}
		double half = fastPow(x, n / 2);
		if (n % 2 == 0) {
			// 若n为偶数，则x^n = x^(n/2)*x^(n/2)
			return half * half;
		} else {
			// 若n为奇数，则x^n = x^(n/2)*x^(n/2)*x=x^(n-1)*x
			return half * half * x;
		}
	}

	public static double myPow1(double x, int n) {
		// 注意此处要对n进行转换，否则若n为-2^31时，执行n=-n会溢出出错
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		return fastPow(x, N);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x = 2;
		int n = -2147483647;
		System.out.println(myPow1(x, n));
	}

}
