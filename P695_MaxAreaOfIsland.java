package leetCode;

/**
 * @fileName: P695_MaxAreaOfIsland.java
 * @author: AT
 * @version: 2019年7月28日 上午10:22:21
 * @question: leetcode.p695:
 * @describe: 给定一个包含了一些0和1的非空二维数组 grid,(1代表土地，0代表水域)
 * @describe: 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 */

public class P695_MaxAreaOfIsland {
	// 思路失败！过于繁琐，情况很多，不然直接沿着1遍历直到没有邻域为1为止，DFS，见示例
	public static int maxAreaOfIsland(int[][] grid) {
		int rowLen = grid.length;
		int columnLen = grid[0].length;
		int maxArea = 0, crtArea = 0;
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				// 若未超出边界且四个方向原本为1，则记录值，否则记记录的负值
				int upArea = (i > 0 && grid[i - 1][j] > 0 ? grid[i - 1][j] : 0);
				int downArea = (i + 1 < rowLen && grid[i + 1][j] > 0 ? grid[i + 1][j] : 0);
				int leftArea = (j > 0 && grid[i][j - 1] > 0 ? grid[i][j - 1] : 0);
				int rightArea = (j + 1 < columnLen && grid[i][j + 1] > 0 ? grid[i][j + 1] : 0);
				crtArea = upArea + downArea + leftArea + rightArea;
				// 注意减去可能重复计算的领域，左上以及自身(0不会有自身重复计算问题)会被左边和上边重复计算（右边和下边还没有计算，所以不会出现重复计算）
				if (grid[i][j] == 1) {
					if (upArea >= 1) {
						if (leftArea >= 1) {
							crtArea -= grid[i - 1][j - 1] > 0 ? grid[i - 1][j - 1] : 0;
						}
					}
					crtArea -= (leftArea > 1 ? 1 : 0);
					crtArea -= (upArea > 1 ? 1 : 0);
					grid[i][j] = crtArea + 1;
				} else {
					if (upArea >= 1 && leftArea >= 1) {
						if (grid[i - 1][j - 1] > 0) {
							crtArea -= grid[i - 1][j - 1] > 0 ? grid[i - 1][j - 1] : 0;
						} else {
							crtArea = Math.max(leftArea, upArea);
						}
					}
					grid[i][j] = -(crtArea) + downArea + rightArea;
				}
				if (Math.abs(grid[i][j]) > maxArea) {
					maxArea = Math.abs(grid[i][j]);
				}
			}
		}
		return maxArea;
	}

	// 优秀代码示例1
	int[][] grid;
	int rows;
	int columns;
	int max = Integer.MIN_VALUE;
	int sum = 0;

	public int maxAreaOfIsland1(int[][] grid) {
		// dfs,把所有岛屿都找出来被
		// 走过的1就改变其值，不用担心跳过情况，因为只要能走到这个1，他身边的1就全可被遍历。
		if (grid.length == 0 || grid == null)
			return 0;
		this.grid = grid;
		this.rows = grid.length;
		this.columns = grid[0].length;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					findArea(i, j);
					max = Math.max(sum, max);
					sum = 0;
				}
			}
		}
		return max == Integer.MIN_VALUE ? 0 : max;
	}

	// 不能在这个函数里传sum,比如[[1,1],[1,0]]，
	// 无论是我走到右边的1，还是下边的1，此时sum都为1+1=2。而其实已经走过了另一个1，
	// 但是没有加进来。
	public void findArea(int i, int j) {
		// 如果当前是0或者超出邻域或已经被遍历过，则退出
		if (i < 0 || i >= rows || j < 0 || j >= columns || grid[i][j] == 0 || grid[i][j] == -1) {
			return;
		}
		sum++;
		grid[i][j] = -1;
		// max=Math.max(sum,max);
		findArea(i - 1, j);
		findArea(i + 1, j);
		findArea(i, j + 1);
		findArea(i, j - 1);
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
//			for (int[] row : grid) {
//				System.out.print("[");
//				for (int num : row) {
//					System.out.printf("%3d", num);
//				}
//				System.out.println(" ]");
//			}
//			System.out.println(maxAreaOfIsland(grid));
//			for (int[] row : grid) {
//				System.out.print("[");
//				for (int num : row) {
//					System.out.printf("%3d", num);
//				}
//				System.out.println(" ]");
//			}
		P695_MaxAreaOfIsland temp = new P695_MaxAreaOfIsland();
		System.out.println(temp.maxAreaOfIsland1(grid4));
	}

}
