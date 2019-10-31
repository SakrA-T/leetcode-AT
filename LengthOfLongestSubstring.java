package leetCode;

import java.util.Scanner;

/**
 * @className lengthOfLongestSubstring.java
 * @author SakrA
 * @version 创建时间：2019年3月22日 下午8:01:00
 */
public class LengthOfLongestSubstring {
	/**
	 * @introduce substring which is continuous;
	 */
	public static int lengthOfLongestSubstring(String s) {
		if (s.length() == 0) {
			return 0;
		}
		int longestLen = 0, tempLen = 0;
//		int longestStrToIndex = 0, tempStrToIndex = -1;
		int searchIndex = 0, repeatIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			searchIndex = s.lastIndexOf(s.charAt(i), i - 1);
			if (searchIndex == -1 || searchIndex < repeatIndex) {
				tempLen++;
//				tempStrToIndex = i;
				if (i == s.length() - 1) {
					if (tempLen > longestLen) {
						longestLen = tempLen;
//						longestStrToIndex = tempStrToIndex;
					}
				}
			} else {
				repeatIndex = searchIndex;
				if (tempLen > longestLen) {
					longestLen = tempLen;
//					longestStrToIndex = tempStrToIndex;
				}
				tempLen = i - repeatIndex;
//				tempStrToIndex = i;
			}
		}
		return longestLen;
	}

	// 优秀代码示例
	public static int lengthOfLongestSubstring2(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[128]; // current index of character
		// try to extend the range [i, j]
		// index[] 记录所有字符最后出现的位置（整个字符串的第几个数），未出现过记为0；
		// j 记录当前遍历到的字符下标；
		// i 记录j之前的所有字符出现重复字符的最后一个位置，即下标为i-1的数与[i,j]中某个数重复，而[i,j]中没有重复字符。
		for (int j = 0, i = 0; j < n; j++) {
			i = Math.max(index[s.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = "";
		int len = 0;
		System.out.print("please enter string to test: ");
		while (sc.hasNextLine()) {
			str = sc.nextLine();
			len = lengthOfLongestSubstring2(str);
			System.out.println("The length of longest substring of \"" + str + "\" is " + len);
		}
		sc.close();
	}

}
