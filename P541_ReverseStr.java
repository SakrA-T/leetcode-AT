package leetCode;

/**
 * @className P541_ReverseStr.java
 * @author AT
 * @version Create Time：2019年7月18日 下午5:59:55
 * @question leetcode.p541:反转字符串 II
 * @describe 给定一个字符串和一个整数k，你需要对从字符串开头算起的每个2k个字符的前k个字符进行反转。如果剩余少于k个字符，则将剩余的所有全部反转。如果有小于2k但大于或等于k个字符，则反转前k个字符，并将剩余的字符保持原样。
 * @describe 比如k=2，则每4个字符，翻转前2个字符，最终若剩下3个字符，则翻转前两个
 */
public class P541_ReverseStr {

	public static String reverseStr(String s, int k) {
		int len = s.length();
		int i = 0, j = 0, e = 0, m = k;
		char c = ' ';
		StringBuilder str = new StringBuilder(s);
		while (i < len) {
			if (i + m >= len) {
				m = len - i;
			}
			System.out.println("m = " + m);
			for (j = 0; i + j < len && j < m / 2; j++) {
				e = i + m - 1 - j;
				c = str.charAt(i + j);
				str.setCharAt(i + j, str.charAt(e));
				str.setCharAt(e, c);
			}
			i = i + 2 * m;
		}
		return str.toString();
	}

	// 优秀代码示例1
	// 更简洁明了
	public static String reverseStr1(String s, int k) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i = i + 2 * k) {
			int end = i + k - 1 > chars.length - 1 ? chars.length - 1 : i + k - 1;
			reverse(chars, i, end);
		}
		// 可以通过这个方式将字符数组转换为字符串，chars.toString是不行的，是将chars本身而非其内容转换为字符串，而其本身是一个指针
		return String.valueOf(chars);
	}

	private static void reverse(char[] s, int start, int end) {
		while (start < end) {
			char temp = s[start];
			s[start] = s[end];
			s[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcdeksfyuttmhnbvctfasddsatioytrewqjfgh";
		int k = 6;
		String rs = reverseStr(s, k);
		for (int i = 0; i < s.length(); i++) {

			if ((i + 1) % k == 0) {
				System.out.println(rs.charAt(i));
			} else {
				System.out.print(rs.charAt(i));
			}
		}
	}

}
