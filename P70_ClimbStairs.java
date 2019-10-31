package leetCode;

/**
 * @className P70_ClimbStairs.java
 * @author AT
 * @version 2019年7月21日 下午1:57:18
 * @question leetcode.p70:爬楼梯
 * @describe 假设你正在爬楼梯，需要 n阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。问有多少种不同的方法爬到楼顶？
 */
public class P70_ClimbStairs {
	// 思路：斐波那契数：F(n) = F(n-1)+F(n-2)
	public static int climbStairs(int n) {
		int i = 0;
		// 初始化pre表示n=1的情况，prepre表示n=0的情况
		int pre = 1, prepre = 0;
		int ans = 0;
		for (i = 1; i <= n; i++) {
			// 对于i，pre 记录假设第一步走1个台阶，共F(i-1)种情况
			// 对于i，prepre 记录假设第一步走2个台阶，共F(i-2)种情况
			ans = pre + prepre;
			prepre = pre;
			pre = ans;
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		System.out.println(climbStairs(n));
	}
}
