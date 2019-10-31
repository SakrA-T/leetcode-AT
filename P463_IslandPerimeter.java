package leetCode;

/**
 * @className P463_IslandPerimeter.java
 * @author AT
 * @version Create Time：2019年7月29日 下午7:38:55
 * @question: leetcode.p463:岛屿的周长（相似问题P695）
 * @describe: 1 表示陆地 0 表示水域。一格周长为4
 */

public class P463_IslandPerimeter {
	// ERROR:原题表示有多个岛屿，就是一块岛屿（实际上测试发现，正确答案会计算所有岛屿周长，哪怕是分开的）
	// 思路：经过计算，若岛屿为1格，则其周长为4，后每增加一格+4，减去自身与其他岛屿重合的边
	// 直接DFS遍历所有岛屿(利用队列)，计算过的做已计算陆地标记，记为-1，记录最大周长

	private static int addPerimeter(int[][] grid, int row, int column, int cur_girth) {
		grid[row][column] = -1;
		cur_girth += 4;
		int rowLen = grid.length, columnLen = grid[0].length;
		int tag = 0;
		// 查找其上下左右，看是否为1
		for (int k = 0; k < 4; k++) {
			int[] temp = direction(row, column, k);
			if (temp[0] >= 0 && temp[0] < rowLen && temp[1] >= 0 && temp[1] < columnLen) {
				tag = grid[temp[0]][temp[1]];
				if (tag == -1) {
					// 减去自身与其他岛屿重合的边
					cur_girth -= 1;
				} else if (tag == 1) {
					cur_girth -= 1;
					cur_girth = addPerimeter(grid, temp[0], temp[1], cur_girth);
				}
			}
		}
		return cur_girth;
	}

	private static int[] direction(int row, int column, int d) {
		switch (d) {
		case 0:
			return new int[] { row - 1, column };
		case 1:
			return new int[] { row, column - 1 };
		case 2:
			return new int[] { row + 1, column };
		default:
			return new int[] { row, column + 1 };
		}
	}

	public static int islandPerimeter(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
//		int max_grith = 0;
		int sum_grith = 0;
		int rowLen = grid.length, columnLen = grid[0].length;
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				int cur = grid[i][j];
				if (cur == 1) {
					sum_grith += addPerimeter(grid, i, j, 0);
//					max_grith = Math.max(max_grith, addPerimeter(grid, i, j, 0));
				}
			}
		}
		return sum_grith;
	}

	// 优秀代码示例1

	public static int islandPerimeter1(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int n = grid.length;
		int m = grid[0].length;
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					res += 4;
					// 左或上邻域出现陆地，减去彼此重合的，-2
					// 每个陆地都只需要对其左和上处理或者右和下处理即可，不需要处理周围全部！！！因为一块陆地的右边，只可能和其他陆地的左边重合，而下边只会和上边重合。
					if (i > 0 && grid[i - 1][j] == 1)
						res -= 2;
					if (j > 0 && grid[i][j - 1] == 1)
						res -= 2;
				}
			}
		}
		return res;
	}

	// 优秀代码示例2
	// 由于岛屿内没有湖,所以只需要求出 北面(或南面) + 西面(或东面)的长度再乘2即可
	// （因为必然对称，北面必对应一个南面）
	public static int islandPerimeter2(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int n = grid.length;
		int m = grid[0].length;
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					// 在边界处的陆地才加上周长，内陆本身就不需要加啊！！！
					if (i - 1 < 0 || grid[i - 1][j] == 0)
						res += 1;
					if (j - 1 < 0 || grid[i][j - 1] == 0)
						res += 1;
				}
			}
		}
		return res * 2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] grid = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
//		int[][] grid3 = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };
//		int[][] grid1 = { { 1, 1 }, { 1, 1 } };
		int[][] grid2 = { { 0, 0, 1 }, { 0, 1, 1 }, { 1, 1, 0 } };

		System.out.println(islandPerimeter1(grid2));
	}

}
