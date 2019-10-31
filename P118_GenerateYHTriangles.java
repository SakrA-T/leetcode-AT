package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P118_GenerateYHTriangles.java
 * @author: AT
 * @version: 2019年7月24日 下午10:12:40
 * @question: leetcode.p118:杨辉三角
 * @describe: 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 */

public class P118_GenerateYHTriangles {
	// 第i行应有i个数组成
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> triangles = new ArrayList<List<Integer>>();
		if (numRows == 0) {
			return triangles;
		}
		for (int i = 0; i < numRows; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(1);

			for (int j = 1; j <= i; j++) {
				if (j == i) {
					list.add(1);
					break;
				}
				// 因为i是 从0到numRows-1 记，所以i表示的就是当前列表所应该存入triangles 列表中的下标
				int sum = triangles.get(i - 1).get(j - 1) + triangles.get(i - 1).get(j);
				list.add(sum);
			}
			triangles.add(list);
		}
		return triangles;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numRows = 10;
		List<List<Integer>> triangles = generate(numRows);
		for (List<Integer> list : triangles) {
			System.out.print("[ ");
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println("]");
		}
	}

}
