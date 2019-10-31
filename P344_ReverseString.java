package leetCode;

/**
 * @className P344_ReverseString.java
 * @author AT
 * @version Create Time：2019年7月18日 下午5:52:19
 * @question leetcode.p344:反转字符串
 * @describe 将输入的字符数组反转过来，空间复杂度O(1)。
 */
public class P344_ReverseString {
	public static void reverseString(char[] s) {
		int len = s.length;
		char c = ' ';
		for (int i = 0; i < len / 2; i++) {
			c = s[i];
			s[i] = s[len - 1 - i];
			s[len - 1 - i] = c;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "123456";
		char[] chs = s.toCharArray();
		reverseString(chs);
		for (int i = 0; i < chs.length; i++) {
			System.out.print(chs[i] + " ");
		}

	}

}
