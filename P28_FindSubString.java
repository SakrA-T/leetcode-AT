package leetCode;

/**
 * @className P28_FindSubString.java
 * @author AT
 * @version Create Time：2019年7月6日 上午9:16:07
 * @question leetcode.p28 : 寻找字符串1中是否有子串2，若有则返回子串第一个字母所在位置下标
 */
public class P28_FindSubString {
	public static int strStr(String haystack, String needle) {
		int rstIndex = -1;
		if (needle.length() == 0) {
			return 0;
		}
		int i = 0, j = 0, k = -1;
		for (i = 0, j = 0; j < needle.length(); i++) {
			// 当 haystack 仅与 needle 前半部分匹配，实际长度不足 needle 时
			if (i >= haystack.length()) {
				return -1;
			}
			if (haystack.charAt(i) == needle.charAt(j)) {
				j++;
				if (k == -1) {
					k = i;
				}
				continue;
			} else {
				i = k == -1 ? i : k;
				k = -1;
				j = 0;
			}
		}
		rstIndex = k;
		return rstIndex;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String haystack = "aaa";
		String needle = "aaaaaa";
		int index = strStr(haystack, needle);
		System.out.println(haystack + " -- " + needle + " : " + index);
	}

}
