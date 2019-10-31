package leetCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @className P54_SpiralOrder.java
 * @author AT
 * @version Create Time：2019年10月3日 下午3:12:02
 * @question: leetcode.p54: 螺旋矩阵
 * @describe: 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 */

public class P54_SpiralOrder {
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> rst = new ArrayList<Integer>();
//		int row = matrix.length, column = matrix[0].length;
		// 剩余部分

		return rst;
	}

	public static void main(String[] args) {
		int m = 3, n = 4;
		int[][] matrix = new int[m][n];

		List<Integer> rst = spiralOrder(matrix);
//		Integer[] rstArr = (Integer[]) rst.toArray();
		Iterator<Integer> iter = rst.iterator();
		System.out.print("[ ");
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println("]");

	}

}
