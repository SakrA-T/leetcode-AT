package leetCode;

/**
 * @className P557_ReverseWords.java
 * @author AT
 * @version Create Time：2019年7月18日 下午7:19:18
 * @question leetcode.p557:反转字符串中的单词 III
 * @describe 给定一个字符串，反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 */
public class P557_ReverseWords {
	public static String reverse(String s, int b, int e) {
		char c = ' ';
		char[] chs = s.toCharArray();
		for (int i = 0; i <= (e - b) / 2; i++) {
			c = chs[i + b];
			chs[i + b] = chs[e - i];
			chs[e - i] = c;
		}
		return String.valueOf(chs);
	}

	public static String reverseWords(String s) {
		int len = s.length();
		int begin = -1, end = -1;
		int i = 0;
		while (i < len) {
			if (s.charAt(i) == ' ') {
				i++;
				continue;
			}
			if (s.charAt(i) != ' ' && begin == -1) {
				begin = i;
			}
			if (i == len - 1 || s.charAt(i + 1) == ' ') {
				end = i;
			}
			if (begin != -1 && end != -1) {
				s = reverse(s, begin, end);
				begin = -1;
				end = -1;
			}
			i++;
		}
		return s;
	}

	// 优秀代码示例1
	// 也是不错的方法，获取startIndex后第一个空格的下标作为endIndex
	//
	public static String reverseWords1(String s) {
		char[] chars = s.toCharArray();
		int startIndex = 0;
		while (startIndex < s.length()) {
			int endIndex = s.indexOf(' ', startIndex + 1);
			if (endIndex == -1) {
				endIndex = s.length();
			}
			reverse(startIndex, endIndex, chars);
			startIndex = endIndex + 1;
			// 排除多余空格
			while (startIndex < s.length() && s.charAt(startIndex) == ' ') {
				startIndex = startIndex + 1;
			}
		}
		return String.valueOf(chars);

	}

	private static void reverse(int startIndex, int endIndex, char[] chars) {
		for (int i = startIndex, k = endIndex - 1; i < k; i++, k--) {
			char temp = chars[i];
			chars[i] = chars[k];
			chars[k] = temp;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "   Let's take   LeetCode con test  ";
		System.out.println(reverseWords1(s));
	}

}
