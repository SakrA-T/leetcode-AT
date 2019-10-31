package leetCode;

/**
 * @className P48_RotateImage.java
 * @author AT
 * @version Create Time：2019年9月30日 下午8:01:30
 * @question: leetcode.p48:旋转图像
 * @describe: 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
 */

public class P48_RotateImage {
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		// 需要翻转的矩阵个数n/2，开始元素分别为(0,0),(1,1),...以此类推
		for (int i = 0; i < n / 2; i++) {
			// 起始元素(i,i)
			int row = i;
			// 需要从(i,i) 循环到 (i,m-1)进行旋转，因为(i,i)会旋转n-1格到(i,m)的位置上
			int m = n - i - 1;
			for (int column = i; column < m; column++) {
				int crt = matrix[row][column]; // 当前元素
				for (int j = 0; j < 4; j++) {
					// 每个元素都需要向前走m-i步
					int pre = crt;
					for (int k = 0; k < m - i; k++) {
						if (row == i && column < m) {
							column++;
						} else if (column == m && row < m) {
							row++;
						} else if (row == m && column > i) {
							column--;
						} else if (column == i && row > i) {
							row--;
						}
					}
					crt = matrix[row][column];
					matrix[row][column] = pre;
					System.out.println("row = " + row + "; column = " + column);
				}
			}
		}
	}

	// 优秀代码示例1
	// 图像旋转本质就是这4个数的相互交换，找出这几个数索引之间的关系(规律)。
	// 即:任意一个(i,j) , (j, n-i-1), (n-i-1, n-j-1), (n -j-1, i)就是这四个索引号上的数交换.

	public void rotate1(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n - j - 1][i];
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
				matrix[j][n - i - 1] = tmp;
			}
		}
	}

	// 优秀代码示例2
	// 翻转整个数组,再按正对角线交换两边的数
	// [1,2,3], -----> [7,8,9], -----> [7,4,1],
	// [4,5,6], -----> [4,5,6], -----> [8,5,2],
	// [7,8,9], -----> [1,2,3], -----> [9,6,3]
	public void rotate2(int[][] matrix) {
		int n = matrix.length;
		// 上下翻转
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n - i - 1][j];
				matrix[n - i - 1][j] = tmp;
			}
		}
		// System.out.println(Arrays.deepToString(matrix));
		// 对角翻转
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int n = 5;
		int[][] matrix = new int[n][n];
		matrix[0] = new int[] { 7, 5, 1, 9, 11 };
		matrix[1] = new int[] { 2, 4, 13, 8, 10 };
		matrix[2] = new int[] { 13, 3, 11, 6, 7 };
		matrix[3] = new int[] { 15, 8, 14, 12, 16 };
		matrix[4] = new int[] { 9, 10, 13, 2, 7 };
//		matrix[0] = new int[] { 1, 2, 3 };
//		matrix[1] = new int[] { 4, 5, 6 };
//		matrix[2] = new int[] { 7, 8, 9 };
		rotate(matrix);
		for (int[] row : matrix) {
			System.out.print("[");
			for (int m : row) {
				System.out.printf("%3d", m);
			}
			System.out.println(" ]");
		}
	}

}
