package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @className P130_SurroundedArea.java
 * @author AT
 * @version Create Time：2019年7月30日 上午11:17:42
 * @question: leetcode.p130:被围绕的区域
 * @describe: 给定一个二维的矩阵，包含'X'和'O'（字母O）。找到所有被'X'围绕的区域，并将这些区域里所有的'O'用'X'填充。
 */

public class P130_SurroundedArea {
	// 思路1：遍历二维数组，对于所有值为'O'的元素，遍历其四周，并将遍历过的O做标记1，当遇到边缘时，记为false，并在本次遍历结束时将false标记存到列表中；
	// 当遍历结束但未遇到边缘时，记为true，将true标记存到列表中；
	// 数组遍历结束后，二次遍历，每次首次遇到标记为1的都取列表中一个元素，若为true，则将其值改为X；若为false，则将其值还原为O
	private static Queue<Boolean> tagList = new LinkedList<Boolean>();
	private static int time = 1, count = 0;

	private static void neighbourSearch(char[][] board, int row, int column, char tagChar) {
		if (row >= board.length || row < 0 || column >= board[0].length || column < 0) {
			// 不管是第一次还是第二次遍历，都需要边界啊！
			if (time == 1 && tagList.size() < count) {
				tagList.offer(false);
			}
			return;
		}
		char toChar = (time == 1) ? '1' : (tagList.peek() ? 'X' : 'O');
		if (board[row][column] == tagChar) {
			board[row][column] = toChar;
			neighbourSearch(board, row - 1, column, tagChar);
			neighbourSearch(board, row, column - 1, tagChar);
			neighbourSearch(board, row + 1, column, tagChar);
			neighbourSearch(board, row, column + 1, tagChar);
		}
	}

	public static void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}
		int rowLen = board.length;
		int columnLen = board[0].length;

		while (time <= 2) {
			char tagChar = (time == 1) ? 'O' : '1';
			for (int i = 0; i < rowLen; i++) {
				for (int j = 0; j < columnLen; j++) {
					char cur = board[i][j];
					if (cur == tagChar) {
						count++;
						neighbourSearch(board, i, j, tagChar);
						if (time == 2) {
							tagList.poll();
						} else {
							if (tagList.size() < count) {
								tagList.offer(true);
							}
						}
					}
				}
			}
			time++;
		}
	}

	// 优秀代码示例1
	// 先沿着边界的O区域遍历，将其对应O区域标记为#；除此之外，就都是内部O区域啦，直接二次遍历，O改为X，#改为O即可
	public static void solve1(char[][] board) {
		int m = board.length;
		if (m == 0)
			return;
		int n = board[0].length;
		int i = 0, j = 0;
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				// 如果'O'在边界上，则遍历其整块区域，并将值标记为'#'
				if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
					if (board[i][j] == 'O') {
						dfs(board, m, n, i, j);
					}
				}
			}
		}
		// 二次遍历
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
//				if (board[i][j] == 'O')
//					board[i][j] = 'X';
//				if (board[i][j] == '#')
//					board[i][j] = 'O';
				char cur = board[i][j];
				board[i][j] = (cur == 'O') ? 'X' : (cur == '#') ? 'O' : cur;
			}
		}
	}

	public static void dfs(char[][] board, int m, int n, int i, int j) {
		if (i >= m || i < 0 || j >= n || j < 0) {
			return;
		}
		if (board[i][j] == 'O') {
			board[i][j] = '#';
			dfs(board, m, n, i - 1, j);
			dfs(board, m, n, i + 1, j);
			dfs(board, m, n, i, j - 1);
			dfs(board, m, n, i, j + 1);
		}
	}

	private static void twoDimArrayPrint(char[][] arr) {
		System.out.println("----------------");
		for (char[] row : arr) {
			System.out.print("[");
			for (char num : row) {
				System.out.printf("%2c", num);
			}
			System.out.println(" ]");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } };
//		char[][] board1 = { { 'O', 'X', 'O' }, { 'X', 'O', 'X' }, { 'O', 'X', 'O' } };
		twoDimArrayPrint(board);
		solve1(board);
		twoDimArrayPrint(board);
	}

}
