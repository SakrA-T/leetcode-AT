package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P119_GetRowYHTriangles.java
 * @author: AT
 * @version: 2019年7月25日 上午11:16:35
 * @question: leetcode.p119:杨辉三角II
 * @describe: 给定一个非负整数 numRows，生成杨辉三角的第 numRows 行（从第0行开始）。
 */

public class P119_GetRowYHTriangles {

	public static List<Integer> getRow(int rowIndex) {
		List<Integer> ansList = new ArrayList<Integer>();
		int sum = 0;
		ansList.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			// 对于第i行（0-rowIndex计数）应有i+1个数，前一行有i个数，0~i-1
			// 理论上，第i-1行第a个数和第a+1个数相加和存入第i行第a+1个数，但由于第a+1个数后续会用到，所以先存入第a个数，最终再在列表头部加入1，并将最后一个数改为1
			for (int j = 0; j + 1 < i; j++) {
				sum = ansList.get(j) + ansList.get(j + 1);
				ansList.set(j, sum);
			}
			ansList.add(0, 1);
			ansList.set(ansList.size() - 1, 1);
		}
		return ansList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numRows = 0;
		List<Integer> ansList = getRow(numRows);
		System.out.print("[ ");
		for (Integer num : ansList) {
			System.out.print(num + " ");
		}
		System.out.println("]");
	}

}
