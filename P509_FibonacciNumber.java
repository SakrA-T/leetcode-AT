package leetCode;

/**
 * @className P509_FibonacciNumber.java
 * @author AT
 * @version 创建时间：2019年7月21日 下午2:22:00
 * @question leetcode.p509:斐波那契数列
 * @describe 实现斐波那契数求解
 */
public class P509_FibonacciNumber {
	// 递归容易造成大量重复计算，不可用
	public static int fib(int N) {
		if (N == 0 || N == 1) {
			return N;
		}
		int ans = 0, pre = 1, prepre = 0;
		for (int i = 2; i <= N; i++) {
			ans = pre + prepre;
			prepre = pre;
			pre = ans;
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 10;
		System.out.println(fib(N));
	}
}
