package leetCode;

/**
 * @className P1138_AlphabetBoardPath.java
 * @author AT
 * @version Create Time：2019年7月31日 下午4:39:37
 * @question: leetcode.p1138:字母板上的路径
 * @describe: 从一块字母板上的位置(0,0)出发，该坐标对应的字符为board[0][0]。
 * @describe: 利用“UDLR”表示上下左右的移动，“!”表示就在当前位置。给定字母板为board=["abcde","fghij","klmno","pqrst","uvwxy","z"].
 * @describe: 给定目标字符串，返回最小行动次数的指令序列，
 */

public class P1138_AlphabetBoardPath {
	// 思路：开始位置在board[0][0]，即'a'处，显然当前target字符串有多少个字符就会有多少个'!'.
	// 转换为对应坐标，即a-z：0-25编号index:横坐标x=index%5,纵坐标y=index/5
	// 先走纵坐标or横坐标，都可以（本代码中先走纵坐标）
	// WARNING:在4行，1-4列的数是没有办法 D
	// 的，可以设置为如果向下走的话，先走横坐标再走D;或者设置成循环，若超出边界，则现在另一个方向，再回来走超出边界的方向。
	private static int[] toCoords(char c) {
		int index = c - 'a';
		int[] coords = { index % 5, index / 5 };
		return coords;
	}

	public static String alphabetBoardPath(String target) {
		String rstPath = "";
		int len = target.length();
		if (len == 0) {
			return rstPath;
		}
		int[] pre_coords = { 0, 0 };
		for (int k = 0; k < len; k++) {
			char c = target.charAt(k);
			int[] c_coords = toCoords(c);
			int x_diff = c_coords[0] - pre_coords[0];
			int y_diff = c_coords[1] - pre_coords[1];
			char x_order = (x_diff == 0) ? '!' : (x_diff > 0 ? 'R' : 'L');
			char y_order = (y_diff == 0) ? '!' : (y_diff > 0 ? 'D' : 'U');
			// 每走一步是 +1 or -1，要对差值为0做处理
			int y_step = (y_diff != 0 ? y_diff / Math.abs(y_diff) : 0);
			int x_step = (x_diff != 0 ? x_diff / Math.abs(x_diff) : 0);
			// 当超出边界时，先走另一个方向，再回来走当前方向。
			while (pre_coords[1] != c_coords[1] || pre_coords[0] != c_coords[0]) {
				while (!(pre_coords[0] != 0 && pre_coords[1] + y_step > 4) && pre_coords[1] != c_coords[1]) {
					rstPath += y_order;
					pre_coords[1] += y_step;
				}
				while (pre_coords[0] != c_coords[0]) {
					rstPath += x_order;
					pre_coords[0] += x_step;
				}
			}
			rstPath += "!";
//			pre_coords = c_coords;
		}
		return rstPath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { { 'a', 'b', 'c', 'd', 'e' }, { 'f', 'g', 'h', 'i', 'j' }, { 'k', 'l', 'm', 'n', 'o' },
				{ 'p', 'q', 'r', 's', 't' }, { 'u', 'v', 'w', 'x', 'y' }, { 'z' } };

		for (char[] cs : board) {
			System.out.print("[");
			for (char c : cs) {
				System.out.printf("%2c", c);
			}
			System.out.println(" ]");
		}
		String target = "zdz";
		System.out.println(alphabetBoardPath(target));
	}

}
