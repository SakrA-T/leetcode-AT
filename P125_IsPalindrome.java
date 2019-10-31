package leetCode;

/**
 * @className P125_IsPalindrome.java
 * @author AT
 * @version Create Time：2019年7月18日 上午11:10:51
 * @question leetcode.p125:验证回文串
 * @describe 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * @describe 本题中，将空字符串定义为有效的回文串。
 */
public class P125_IsPalindrome {

	public static boolean isPalindrome(String s) {
		// 当一个字符和一个整数相加时，计算时使用的是字符的统一码。
		// (int)('10'-'0');返回10
		if (s.length() == 0) {
			return true;
		}
		int l = 0, r = s.length() - 1;
		char cl = ' ', cr = ' ';
		while (l < r) {

			while (l < r && !Character.isLetterOrDigit(s.charAt(l)))
				l++;
			while (l < r && !Character.isLetterOrDigit(s.charAt(r)))
				r--;
			cl = s.charAt(l++);
			cr = s.charAt(r--);
			if (Character.isDigit(cl) || Character.isDigit(cr)) {
				if (cl != cr) {
					return false;
				}
				continue;
			}
			cl = Character.toLowerCase(cl);
			cr = Character.toLowerCase(cr);
			if (cl != cr) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " A man, a plan, a ccanal: Panama";
		System.out.println(isPalindrome(s));
		char c = ' ';
		System.out.println(Character.digit(c, 10));
	}

}
