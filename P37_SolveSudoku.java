package leetCode;

/**
 * @className P37_SolveSudoku.java
 * @author AT
 * @version Create Time：2019年7月13日 上午11:21:17
 * @question leetcode.p37:解数独
 * @describe 给定填入部分数字的数独表格，将表格按数独规则填充完整
 */
public class P37_SolveSudoku {

	public static void solveSudoku(char[][] board) {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		solveSudoku(board);
		System.out.println(" —————————————————————————————————————— ");
		int count = 0;
		for (char[] cs : board) {
			System.out.print("| ");
			count++;
			for (char c : cs) {
				System.out.print(c + " | ");
			}
			System.out.println(" |");
			if (count % 3 == 0)
				System.out.println(" —————————————————————————————————————— ");
		}
//		System.out.print(" —————————————————————————————————————— ");
	}

}
