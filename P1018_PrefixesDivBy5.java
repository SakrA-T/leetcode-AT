package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @fileName: P1018_PrefixesDivBy5.java
 * @author: AT
 * @version: 2019年7月22日 下午5:16:51
 * @question: leetcode.p1018:
 * @describe: 给定二进制数组A。从A[0]到A[i]的第i个子数组（从最高有效位到最低有效位）所表示的数值可被5整除时，对应answer[i]为true，否则为false。
 */

public class P1018_PrefixesDivBy5 {
	// 思路：遍历求值，对于i,mod保存A[0...i-1]所表示数值mod 5的结果
	public static List<Boolean> prefixesDivBy5(int[] A) {
		List<Boolean> answer = new ArrayList<>();
		int temp = 0, mod = 0;
		for (int i = 0; i < A.length; i++) {
			// <<的优先级低于+;MOD运算的分配律
			temp = (mod << 1) + A[i];
			mod = temp % 5;
			answer.add((mod == 0) ? true : false);
		}
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated jmethod stub
		int[] A = { 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 };
		List<Boolean> answer = prefixesDivBy5(A);
		Boolean[] a = answer.toArray(new Boolean[answer.size()]);
		System.out.println(Arrays.toString(a));
	}

}
