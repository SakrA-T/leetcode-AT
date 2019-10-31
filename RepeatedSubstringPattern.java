package leetCode;

/**
 * @className RepeatedSubstringPattern.java
 * @author AT
 * @version Create Time：2019年7月6日 上午9:41:49
 * @question leetcode.p459:给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 */
public class RepeatedSubstringPattern {
	public static boolean repeatedSubstringPattern(String s) {
		int endIndex = 0;
		int i = 0, j = 0;
		for (j = 0, i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(j)) {
//				if (j == endIndex) {
//					j = 0;
//				} else
//					j++;
				// 也可写为
				j = (j == endIndex) ? 0 : j + 1;
			} else {
				// 回到起点，可能为被重复子串的后一个字符，重新开始匹配
				endIndex = endIndex + 1;
				i = endIndex;
				j = 0;
			}
		}
		if (j != 0 || s.length() == 0 || endIndex == s.length() - 1) {
			return false;
		}
		return true;
	}

	// 优秀代码示例1
	// 利用子串匹配，并进行适当剪枝
	public static boolean repeatedSubstringPattern1(String s) {
		boolean res = false;
		for (int i = 1; i <= s.length() / 2; i++) {
			// 满足条件的字符串字符个数首先可被子串个数整除，且子串不可超过原字符串的一半
			if (s.length() % i == 0) {
				// 可能的子串 [0,i)
				String temp = s.substring(0, i);
				res = true;
				for (int m = i; m <= s.length() - i; m = m + i) {
					// 每次对子串[m,m+i)进行判断
					if (!temp.equals(s.substring(m, m + i))) {
						res = false;
						break;
					}
				}
				if (res) {
					break;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcabc";
		boolean isRepeated = repeatedSubstringPattern(s);
		System.out.println(isRepeated);
	}

}
