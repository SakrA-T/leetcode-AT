package leetCode;

/**
 * @className P67_AddBinary.java
 * @author AT
 * @version Create Time：2019年7月12日 上午11:19:47
 * @question leetcode.p67:二进制求和
 * @describe 给定两个二进制字符串，返回他们的和（用二进制表示）。
 */
public class P67_AddBinary {
	public static String addBinary(String a, String b) {
		int a_len = a.length();
		int b_len = b.length();
		String ans = "";
		int i = a_len - 1, j = b_len - 1;
		int c = 0, x = 0, y = 0;
		while (i >= 0 || j >= 0) {
			if (i >= 0) {
				x = Integer.parseInt(a.charAt(i) + "");
				c += x;
			}
			if (j >= 0) {
				y = Integer.parseInt(b.charAt(j) + "");
				c += y;
			}
			System.out.println("x = " + x + ",y = " + y);
			ans = c % 2 + ans;
			c /= 2;
			i--;
			j--;
		}
		if (c == 1) {
			ans = c + ans;
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "1";
		String b = "111";
		System.out.println(addBinary(a, b));
	}

}
