package leetCode;

/**
 * @fileName: P69_MySqrt.java
 * @author: AT
 * @version: 2019年7月20日 下午8:58:08
 * @question: leetcode.p69:x 的平方根
 * @describe: 实现 int sqrt(int x) 函数。计算并返回x的平方根，其中x是非负整数。由于返回类型是整数，结果只保留整数的部分。
 */
public class P69_MySqrt {
	public static int mySqrt(int x) {
		if (x == 1 || x == 0) {
			return x;
		}
		int l = 0, r = x / 2, m = 0;
		double m2 = 0;
		while (l <= r) {
			m = l + (r - l) / 2;
			m2 = (double) m * m;
			if (m2 == x) {
				return m;
			} else if (m2 < x) {
				// 如果正确结果是m，但是由于m^2<x在这里l=m+1，原m被去掉的话，最终会以r==l,m^2>x,使r = m-1，而告终，此时r就是最终结果
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return r;
	}

	// 优秀代码示例1
	public static int mySqrt1(int a) {
		if (a == 0 || a == 1) {
			return a;
		}
		long x = a;
		while (x > a / x) {
			x = (x + a / x) / 2;
		}
		return (int) x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 188888;
		System.out.println(mySqrt(x));
		System.out.println((double) mySqrt(x) * mySqrt(x));
	}

}
