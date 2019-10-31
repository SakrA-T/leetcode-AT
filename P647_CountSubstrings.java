package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @className P647_CountSubstrings.java
 * @author AT
 * @version Create Time：2019年7月18日 下午7:55:33
 * @question leetcode.p647:回文子串
 * @describe 给定一个字符串，计算这个字符串中有多少个回文子串。
 */
public class P647_CountSubstrings {

	static List<String> hwList = new ArrayList<>();

	// 思路1：动态规划
	public static int countSubstrings(String s) {
		int count = 0;
		int len = s.length();
		int[][] hwArray = new int[len][len];
		int i = 0, j = 0, k = 0;
		// 回文子串标记为1，非回文标记为0，所有单个字符都算作回文
		// 对单个字符和两个字符的情况做先处理
		for (; i < len; i++) {
			hwArray[i][i] = 1;
			count++;
			hwList.add(s.substring(i, i + 1));
			if (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
				hwArray[i][i + 1] = 1;
				count++;
				hwList.add(s.substring(i, i + 2));
			}
		}
		// 对于[i...j]可以通过先判断[i+1...j-1]来判断是否为回文串
		for (k = 2; k < len; k++) {
			for (i = 0; i < len - k; i++) {
				j = i + k;
				if (hwArray[i + 1][j - 1] == 0 || s.charAt(i) != s.charAt(j)) {
					hwArray[i][j] = 0;
				} else {
					hwArray[i][j] = 1;
					count++;
					hwList.add(s.substring(i, j + 1));
				}
			}
		}
		return count;
	}

	// 思路2：中心扩展
	// 由[i]和[i,i+1]分别向两边扩展，充分考虑回文串字符个数为奇数和偶数的情况。
	// 优秀代码示例：
	public int countSubstrings2nd(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			// 以当前点i位置，向两边扩展,以i i+1位置向两边扩展
			result += countSegment(s, i, i);
			result += countSegment(s, i, i + 1);
		}
		return result;
	}

	public int countSegment(String s, int start, int end) {
		int count = 0;
		// start往左边跑，end往右边跑，注意边界
		// 当首次遇到边界两字符不相等的情况，就会退出循环的！一直相等才会继续！
		while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aaaabba";
		System.out.println(countSubstrings(s));
		ListIterator<String> iter = hwList.listIterator();
		System.out.print(" | ");
		while (iter.hasNext()) {
			System.out.print(iter.next() + " | ");
		}
		System.out.println();
	}

}
