package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @className P1137_Tribonacci.java
 * @author AT
 * @version Create Time：2019年7月30日 上午10:37:18
 * @question: leetcode.p1137:第 N 个泰波那契数
 * @describe: 泰波那契序列Tn定义如下：T0=0,T1=1,T2=1,且在n>=0的条件下Tn+3=Tn+Tn+1+Tn+2（即，当前数等于前面三个数的和）
 * @describe: 给定整数 n，请返回第 n 个泰波那契数 Tn 的值。
 */

public class P1137_Tribonacci {
	private static List<Integer> tribArr = new ArrayList<Integer>();

	// 注意n是从0开始的，可以直接对应列表下标
	public static int tribonacci(int n) {
		tribArr.add(0);
		tribArr.add(1);
		tribArr.add(1);
		if (n < tribArr.size()) {
			return tribArr.get(n);
		}
		for (int k = 3; k <= n; k++) {
			int rst = 0;
			for (int i = 1; i <= 3; i++) {
				rst += tribArr.get(k - i);
			}
			tribArr.add(rst);
		}
		return tribArr.get(n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		System.out.print(tribonacci(n));
	}

}
