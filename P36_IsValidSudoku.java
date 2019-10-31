package leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @className P36_IsValidSudoku.java
 * @author AT
 * @version Create Time：2019年7月12日 下午5:53:30
 * @question leetcode.p36
 * @describe 判断一个9x9的数独是否有效。只需要数独规则，验证已经填入的数字是否有效即可（不管是否有解）。
 */
public class P36_IsValidSudoku {
	// 思路1：将每一行，每一列，每个3*3块，都加入一个set中，边遍历，边添加，'.'跳过
	// 时间复杂度：O(1),只遍历一次表格，空间复杂度：O(1),可计算的大小，n无关
	public static boolean isValidSudoku(char[][] board) {
//		List<HashSet<Integer>> rowList = new ArrayList<>();
		List<HashSet<Integer>> columnList = new ArrayList<>();
		List<HashSet<Integer>> gridList = new ArrayList<>();
		int curNum = 0;
		for (int i = 0; i < board.length; i++) {
			HashSet<Integer> rowSet = new HashSet<>();
			for (int j = 0; j < board[i].length; j++) {
				if (i == 0) {
					HashSet<Integer> columnSet = new HashSet<>();
					columnList.add(columnSet);
				}
				if (i % 3 == 0 && j % 3 == 0) {
					HashSet<Integer> gridSet = new HashSet<>();
					gridList.add(gridSet);
				}
				if (board[i][j] != '.') {
					curNum = board[i][j] - '0';
				} else {
					continue;
				}
				// 在3*3格子的set列表中，第(i,j)个元素，保存在index为i/3*3+j/3的列表中
				if (rowSet.contains(curNum) || columnList.get(j).contains(curNum)
						|| gridList.get(i / 3 * 3 + j / 3).contains(curNum)) {
					return false;
				}
				rowSet.add(curNum);
				columnList.get(j).add(curNum);
				gridList.get(i / 3 * 3 + j / 3).add(curNum);
			}
//			rowList.add(rowSet);
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		System.out.println(isValidSudoku(board));
	}

}
