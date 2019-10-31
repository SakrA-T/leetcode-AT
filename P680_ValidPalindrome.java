package leetCode;

/**
 * @className P680_ValidPalindrome.java
 * @author AT
 * @version Create Time：2019年7月17日 下午5:15:43
 * @question leetcode.p680:验证回文字符串 Ⅱ
 * @describe 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 */
public class P680_ValidPalindrome {
	// 思路1，从两边向中间遍历
	// 当第一次遇到左右不同时，如果left+1=right（已经是最后两个字符了，删除谁都可以）则返回true；
	// 如果不是，则在左右都可能被删除一个元素后变成回文，所以分别判断
	public static boolean isValid(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--)) {
				return false;
			}
		}
		return true;
	}

	public static boolean validPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		boolean rst = true;
		for (; l < r; l++, r--) {
			if (s.charAt(l) != s.charAt(r)) {
				break;
			}
		}
		System.out.println("l = " + l + ", r = " + r);
		if (l >= r || l + 1 == r) {
			return true;
		}
		rst = isValid(s, l + 1, r) || isValid(s, l, r - 1);
		return rst;
	}

	// 优秀代码示例1
	// emmm...比我稍稍简洁一点
	public static boolean validPalindrome1(String s) {
		int l = 0, r = s.length() - 1;
		for (; l < r; l++, r--) {
			// 当第一次发现字符不等时即可进入左右删除判断了，避免了不必要的条件判断
			if (s.charAt(l) != s.charAt(r)) {
				return isValid(s, l + 1, r) || isValid(s, l, r - 1);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abbcdbba";
		System.out.println(validPalindrome1(s));
	}

}
