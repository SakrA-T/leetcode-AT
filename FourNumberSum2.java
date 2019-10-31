package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @className FourNumberSum2.java
 * @author AT
 * @version Create Time：2019年7月2日 上午9:10:39
 */
public class FourNumberSum2 {
	// 优质代码示例
	// 思路：将数组C，D 任意组合的和存入查找表HashMap中， key是和，value 是出现的次数。
	// 记录A，B任意组合的和的负值，然后在查找表中查找是否有对应的值,时间复杂度：O(n^2)。
	public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int rstCount = 0;
		int i = 0, j = 0, len = A.length, key = 0;
		Map<Integer, Integer> cdCombine = new HashMap<>();
		for (i = 0; i < len; i++) {
			for (j = 0; j < len; j++) {
				key = C[i] + D[j];
//				if (cdCombine.containsKey(key)) {
//					cdCombine.put(key, cdCombine.get(key) + 1);
//                } else {
//                	cdCombine.put(key, 1);
//                }
				cdCombine.put(key, cdCombine.containsKey(key) ? cdCombine.get(key) + 1 : 1);
			}
		}
		Set<Integer> keyset = cdCombine.keySet();
		for (Object k : keyset) {
			System.out.printf("%3d%s%3d\n", k, ",", cdCombine.get(key));
		}
		int sum = 0;
		for (i = 0; i < len; i++) {
			for (j = 0; j < len; j++) {
				sum = (A[i] + B[j]) * (-1);
				if (cdCombine.containsKey(sum)) {
					rstCount += cdCombine.get(sum);
				}
			}
		}
		return rstCount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = { 0, 1, -1 };
		int B[] = { -1, 1, 0 };
		int C[] = { 0, 0, 1 };
		int D[] = { -1, 1, 1 };
		int count = fourSumCount(A, B, C, D);
		System.out.println("The count is " + count);
	}

}
