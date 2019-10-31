package leetCode;

/**
 * @className P200_NumIslands.java
 * @author AT
 * @version Create Time：2019年7月30日 上午10:53:24
 * @question: leetcode.p200:岛屿数量
 * @describe: 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 */

public class P200_NumIslands {
	// 思路：遍历查找，对于每个值为1 的块，遍历其周围所有的陆地，并将其值置为2
	// 原题中给定grid为char数组，这边为方便设为int数组
	private static void neighbourSearch(int[][] grid, int row, int column) {
		if (row >= grid.length || row < 0 || column >= grid[0].length || column < 0) {
			return;
		}
		if (grid[row][column] == 1) {
			grid[row][column] = 2;
			neighbourSearch(grid, row - 1, column);
			neighbourSearch(grid, row, column - 1);
			neighbourSearch(grid, row + 1, column);
			neighbourSearch(grid, row, column + 1);
		}
	}

	public static int numIslands(int[][] grid) {
		int count = 0;
		if (grid.length == 0) {
			return count;
		}
		int rowLen = grid.length;
		int columnLen = grid[0].length;

		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				if (grid[i][j] == 1) {
					count++;
					neighbourSearch(grid, i, j);
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid4 = { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				{ 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };

//			int[][] grid3 = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };
//			int[][] grid1 = { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } };
//			int[][] grid2 = { { 0, 0, 1 }, { 0, 1, 1 }, { 1, 1, 0 } };

		for (int[] row : grid4) {
			System.out.print("[");
			for (int num : row) {
				System.out.printf("%3d", num);
			}
			System.out.println(" ]");
		}
		System.out.println(numIslands(grid4));
		for (int[] row : grid4) {
			System.out.print("[");
			for (int num : row) {
				System.out.printf("%3d", num);
			}
			System.out.println(" ]");
		}
	}

}
