package leetCode;

/**
 * @fileName: P289_GameOfLife.java
 * @author: AT
 * @version: 2019年7月27日 下午10:08:44
 * @question: leetcode.p289:生命游戏
 * @describe: 给定一个包含m×n个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态live(1)即为活细胞，或dead(0)即为死细胞。
 * @describe: 细胞遵循四条生存定律根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。（细胞的出生和死亡是同时发生的，即不会二次影响）
 * @rule: live(1)：周围8个位置，活细胞数为2or3，细胞live，其他dead
 * @rule: dead(0)：周围8个位置，活细胞数为3，细胞live，其他dead
 */

public class P289_GameOfLife {
	// 思路1：遍历，对于每个元素，遍历其周围8个元素，（由于转换都是同时发生的，所有只能对转换元素做标记，否则会影响之后遍历到的元素）将由1转为0的点记为-2；将由0转为1的点记为-1
	public static void gameOfLife(int[][] board) {
		int rowLen = board.length;
		if (rowLen == 0) {
			return;
		}
		int columnLen = board[0].length;
		// 记录周围1的个数
		int count = 0;
		for (int row = 0; row < rowLen; row++) {
			for (int column = 0; column < columnLen; column++) {
				count = 0;
				int i = (row > 0 ? (row - 1) : 0);
				while (i < rowLen && i <= row + 1) {
					int j = (column > 0 ? (column - 1) : 0);
					while (j < columnLen && j <= column + 1) {
						if (board[i][j] == 1 || board[i][j] == -2) {
							count++;
						}
						j++;
					}
					i++;
				}
				// 因为之前遍历的时候会把当前结点也算上,所以如果是活细胞的话，会多一个1
				if (board[row][column] == 1 && (count != 3 && count != 4)) {
					board[row][column] = -2;
				}
				if (board[row][column] == 0 && count == 3) {
					board[row][column] = -1;
				}
			}
		}
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				if (board[i][j] < 0) {
					board[i][j] += 2;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		gameOfLife(board);
		for (int[] row : board) {
			System.out.print("[ ");
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println("]");
		}
	}

}
