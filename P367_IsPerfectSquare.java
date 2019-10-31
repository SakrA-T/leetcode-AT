package leetCode;

/**
 * @fileName: P367_IsPerfectSquare.java
 * @author: AT
 * @version: 2019年7月20日 下午9:42:13
 * @question: leetcode.p367:有效的完全平方数
 * @describe: 给定一个正整数 num，编写一个函数，如果num是一个完全平方数，则返回True，否则返回False。
 */
public class P367_IsPerfectSquare {
	public static boolean isPerfectSquare(int num) {
		if (num == 1) {
			return true;
		}
		int l = 0, r = num / 2, m = 0;
		double m2 = 0;
		while (l <= r) {
			m = l + (r - l) / 2;
			m2 = (double) m * m;
			if (m2 == num) {
				return true;
			} else if (m2 < num) {
				// 如果正确结果是m，但是由于m^2<x在这里l=m+1，原m被去掉的话，最终会以r==l,m^2>x,使r = m-1，而告终，此时r就是最终结果
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 169;
		System.out.println(isPerfectSquare(num));
	}

}
