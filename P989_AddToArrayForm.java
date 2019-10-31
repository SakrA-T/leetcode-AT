package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @className P989_AddToArrayForm.java
 * @author AT
 * @version Create Time：2019年7月11日 下午10:02:15
 * @question leetcode.p989:数组形式的整数加法
 * @describe 对于非负整数X而言,如果X=1231，那么其数组形式为[1,2,3,1],给定非负整数X的数组形式A和数值K，返回整数X+K的数组形式。
 */
public class P989_AddToArrayForm {

	public static List<Integer> addToArrayForm(int[] A, int K) {
		List<Integer> ansList = new ArrayList<>();
		int c = 0; // 记录进位
		int i = A.length - 1, mod = 0, a = 0;
		if (K == 0) {
			for (int j = 0; j < A.length; j++)
				ansList.add(A[j]);
			return ansList;
		}
		while (i >= 0 || K > 0) {
			if (i < 0) {
				a = 0;
			} else {
				a = A[i--];
			}
			if (K > 0) {
				mod = K % 10;
				K = K / 10;
			} else {
				mod = 0;
			}
			ansList.add(0, (a + mod + c) % 10);
			if (a + mod + c > 9) {
				c = 1;
			} else {
				c = 0;
			}
		}
		if (c == 1) {
			ansList.add(0, 1);
		}
		return ansList;
	}

	// 优秀代码示例1
	public static List<Integer> addToArrayForm1(int[] A, int K) {
		List<Integer> ansList = new ArrayList<>();
		int c = 0; // 记录进位
		int i = A.length - 1, a = K;
		if (K == 0) {
			for (int j = 0; j < A.length; j++)
				ansList.add(A[j]);
			return ansList;
		}
		while (i >= 0 || a > 0) {
			if (i >= 0) {
				// 不需要像我写的那样复杂，记录进位，直接把A的对应位值加到a(K)上即可每步结尾只保存%10的余数，以及迭代/10的商（会自动记录进位）
				a += A[i--];
			}
			ansList.add(0, a % 10);
			a /= 10;
		}
		if (c == 1) {
			ansList.add(0, 1);
		}
		return ansList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { 9 };
		int K = 819;
		List<Integer> ansList = addToArrayForm(A, K);
		System.out.print("[ ");
		for (Integer i : ansList) {
			System.out.print(i + " ");
		}
		System.out.println("]");
	}

}
