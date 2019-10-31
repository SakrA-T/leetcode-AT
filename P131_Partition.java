package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @className P131_Partition.java
 * @author AT
 * @version Create Time：2019年10月26日 下午3:22:40
 * @question: leetcode.p131:分割回文串
 * @describe: 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。给出s所有可能的分割方案
 */

public class P131_Partition {
	// 思路1：内存超出
	public static List<List<String>> partition(String s) {
		List<List<String>> crtList = new ArrayList<>();
		int len = s.length();
		List<List<List<String>>> listArr = new ArrayList<>();
		List<String> itemList = new ArrayList<>();
//		itemList.add(s.substring(0, 1));
		crtList.add(itemList);
		listArr.add(crtList);
		// listArr 第一项为空
		for (int i = 0; i < len; i++) {
			int bg = findPalindrome(s, i);
			List<List<String>> dList = new ArrayList<>();
			for (List<String> li : listArr.get(i)) {
				List<String> list = new ArrayList<String>(li);
				list.add(s.substring(i, i + 1));
				dList.add(list);
			}
			if (bg != -1) {
				for (List<String> li : listArr.get(bg)) {
					List<String> list = new ArrayList<String>(li);
					list.add(s.substring(bg, i + 1));
					dList.add(list);
				}
			}
			listArr.add(dList);
		}
		return listArr.get(len);
	}

	private static int findPalindrome(String str, int end) {
		for (int i = end - 1; i >= 0; i--) {
			if (isPalindrome(str.substring(i, end + 1))) {
				return i;
			}
		}
		return -1;
	}

	private static boolean isPalindrome(String str) {
		int left = 0, right = str.length() - 1;
		if (left < right) {
			if (str.charAt(left) == str.charAt(right)) {
				left--;
				right++;
			} else {
				return false;
			}
		}
		return true;
	}

	// 优秀代码示例1
	// 为什么这个不会超？？使用回溯法，避免使用三级列表保存
	public static List<List<String>> partition1(String s) {
		List<List<String>> res = new ArrayList<>();
		backtrack(res, s, new ArrayList<String>());
		return res;

	}

	private static void backtrack(List<List<String>> res, String s, ArrayList<String> tmp) {
		if (s == null || s.length() == 0)
			res.add(new ArrayList<>(tmp));
		for (int i = 1; i <= s.length(); i++) {
			// 每次将当前位置开始的第一个回文串，添加到列表中
			if (isPalindrome(s.substring(0, i))) {
				// System.out.println(s.substring(0, i));
				tmp.add(s.substring(0, i));
				backtrack(res, s.substring(i, s.length()), tmp);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String s = scanner.nextLine();
			if (s.equals("0")) {
				break;
			} else {
				List<List<String>> rstList = partition1(s);
				Display<String> display = new Display<>();
				display.doubleListDisplay(rstList);
			}
		}
		scanner.close();
	}

}
