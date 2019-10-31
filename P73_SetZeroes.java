package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @fileName: P73_SetZeroes.java
 * @author: AT
 * @version: 2019年7月27日 下午9:46:33
 * @question: leetcode.p73:矩阵置零
 * @describe: 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 */

public class P73_SetZeroes {
	// 思路1：遍历矩阵，遇到0时，记录其行和列到HashSet中（避免重复添加）
	// 再遍历一次，将所记录的行列对应元素全都改为0
	// 空间复杂度O(m + n),时间复杂度复杂度O(mn)
	public static void setZeroes(int[][] matrix) {
		if (matrix.length == 0) {
			return;
		}
		Set<Integer> rowSet = new HashSet<>();
		Set<Integer> columnSet = new HashSet<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					rowSet.add(i);
					columnSet.add(j);
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (rowSet.contains(i) || columnSet.contains(j)) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	// 思路2：空间暴力
	// 遍历到0是将其所在行列的所有元素修改为特殊标记数值，再次遍历，将标记数值修改为0
	// 感觉没有什么实际意义。

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		setZeroes(matrix);
		for (int[] row : matrix) {
			System.out.print("[ ");
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println("]");
		}
	}

}
