package leetCode;

import java.util.Scanner;

/**
 * @className LongestPalindrome.java
 * @author SakrA
 * @version 创建时间：2019年3月23日 上午9:15:20
 */
public class LongestPalindrome {
	public static class RecordNode implements Comparable<RecordNode> {
		int left = 0;
		int right = 0;
		int len = 0;

		RecordNode() {
			left = 0;
			right = 0;
			len = 0;
		}

		RecordNode(int l, int r, int n) {
			left = l;
			right = r;
			len = n;
		}

		@Override
		public int compareTo(RecordNode o) {
			if (o.len < len) {
				return 1;
			} else if (o.len > len) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	// 检讨：繁琐，乱，占用不必要的内存空间，时间复杂度并未减小（都是O(n^2)），因小失大
	public static String longestPalindrome(String s) {
		String longestPd = "";
		int len = s.length(), i = 0, j = 0;
		if (len == 0) {
			return "";
		}
		RecordNode[][] recordArr = new RecordNode[len][len];
		for (i = len - 1; i >= 0; i--) {
			for (j = i; j < len; j++) {
				if (j == i) {
					recordArr[i][j] = new RecordNode(i, j, 1);
				} else {
					if (s.charAt(i) == s.charAt(j)) {
						if (i + 1 == j) {
							recordArr[i][j] = new RecordNode(i, j, 2);
						} else if (recordArr[i + 1][j - 1].left == i + 1 && recordArr[i + 1][j - 1].right == j - 1) {
							recordArr[i][j] = new RecordNode(i, j, recordArr[i + 1][j - 1].len + 2);
						} else {
							if (recordArr[i][j - 1].compareTo(recordArr[i + 1][j]) >= 0) {
								recordArr[i][j] = recordArr[i][j - 1];
							} else {
								recordArr[i][j] = recordArr[i + 1][j];
							}
						}
					} else {
						if (recordArr[i][j - 1].compareTo(recordArr[i + 1][j]) >= 0) {
							recordArr[i][j] = recordArr[i][j - 1];
						} else {
							recordArr[i][j] = recordArr[i + 1][j];
						}
					}
				}
			}
		}
		longestPd = s.substring(recordArr[0][len - 1].left, recordArr[0][len - 1].right + 1);
		return longestPd;
	}

	// 优秀代码示例
	// 分析：中心扩散检查，分2种，一种存在中心点aba，一种不存在中心点如 abba;
	public String longestPalindrome2(String s) {
		if (s.length() == 0)
			return "";
		int start = 0;
		int end = 0;
		// 每个字符分别做两种中心扩散检查
		for (int i = 0; i < s.length() - 1; i++) {
			int len1 = center(s, i, i);
			int len2 = center(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start + 1) {
				end = i + len / 2;
				start = i - (len - 1) / 2;
			}

		}
		return s.substring(start, end + 1);
	}

	public int center(String s, int L, int R) {
		while (L >= 0 && R <= s.length() - 1 && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		} // 此时R,L已经是不包含在回文子串中的字符了，回文子串为[left+1，right-1]；
		return R - L - 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = "";
		String rst = "";
		System.out.print("please enter string to test: ");
		while (sc.hasNextLine()) {
			str = sc.nextLine();
			rst = longestPalindrome(str);
			System.out.println("The longest palindrome of \"" + str + "\" is " + rst);
		}
		sc.close();
	}

}
