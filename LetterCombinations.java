package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @className LetterCombinations.java
 * @author AT
 * @version Create Time：2019年6月27日 下午9:40:44
 */
public class LetterCombinations {
	static String[] numToLet = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public static int count(String i_digits) {
		int size = 1;
		int d_len = i_digits.length();
		int i = 0;
		for (i = 0; i < d_len; i++) {
			int t = i_digits.charAt(i) - '0';
			if (t == 7 || t == 9) {
				size *= 4;
				continue;
			} else
				size *= 3;
		}
		return size;
	}

	public static ArrayList<String> letterCombinations(String digits) {
		int d_len = digits.length();
		ArrayList<String> letList = new ArrayList<String>();
		int i = 0, j = 0, k = 0, item_len = 0;
		StringBuilder itemStr = new StringBuilder();
		int total_size = 1, item_size = 0, interval = 0, times = 0, index = 0;

		total_size = count(digits);
		for (i = 0; i < d_len; i++) {
			itemStr = new StringBuilder(numToLet[digits.charAt(i) - '2']);
			item_len = itemStr.length();
			// 每个字符每次需要重复多少个
			item_size = total_size / item_len;
			// 计算间隔 每次重复从几开始
			interval = count(digits.substring(i, d_len));
			// 每轮连续输出多少次
			if (i + 1 >= d_len) {
				times = 1;
			} else
				times = count(digits.substring(i + 1, d_len));

//			interval = total_size / count;
//			System.out.println("i = " + i + " numToLet[digits.charAt(i) - '2'] = " + numToLet[digits.charAt(i) - '2']);
//			System.out.println("interval = " + interval + " times = " + times + " item_size = " + item_size);
			// 遍历单个字符串中每个字符，如abc中的a
			int count = 0, begin_index = 0;
			for (k = 0; k < item_len; k++) {
				// 每个字符需要重复item_size次,共重复item_size / times轮
//				System.out.println("j_total = " + item_size / times);
				// 每个字符起始index为：前一个的起始 times * k
				begin_index = times * k;
//				System.out.println("begin_index = " + begin_index);
				for (j = 0; j < item_size / times; j++) {
					for (count = 0; count < times; count++) {
						index = begin_index + j * interval + count;
//						System.out.println("index = " + index);
						if (index < total_size) {
							if (letList.size() < index + 1) {
								letList.add(itemStr.charAt(k) + "");
							} else
								letList.set(index, letList.get(index) + itemStr.charAt(k));
						} else
							break;
//						System.out.println("letList.get(" + index + ") = " + letList.get(index));
					}
				}
			}
		}
		return letList;
	}

	// 优秀代码示例 1
	public List<String> letterCombinations2(String digits) {
		List<String> res = new ArrayList<>();
		if (digits.isEmpty()) {
			return res;
		}
		String[] strs = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		dfs(digits, strs, 0, "", res);
		return res;
	}

	private void dfs(String digits, String[] strs, int i, String cur, List<String> res) {
		if (i == digits.length()) {
			res.add(cur);
			return;
		}
		String str = strs[digits.charAt(i) - '0'];
		for (char c : str.toCharArray()) {
			dfs(digits, strs, i + 1, cur + c, res);
		}
	}

	// 优秀代码示例 2
	public static List<String> letterCombinations3(String digits) {
		String[] board = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

		List<String> ls = new ArrayList<>();
		if (digits.length() == 0) {
			return ls;
		}
		ls.add("");
		for (int i = 0; i < digits.length(); i++) {
			int d = digits.charAt(i) - '0';
			List<String> tmpls = new ArrayList<>();
			for (int j = 0; j < ls.size(); j++) {
				// 对于每个之前已经保存的字符，对当前字符串中，每个字符进行组合
				// 如，935，之前保持wxyz，对于单独的w，重复def，组合为wd,we,wf，递归地，对于wd，重复jkl，wdj,wdk,wdl
				int k = 0;
				for (k = 0; k < board[d].length(); k++) {
					// 对于每个单独数字的迭代，tmpls都是新建的空列表
					tmpls.add(ls.get(j) + board[d].charAt(k));
					System.out.println("ls.get(j) + board[d].charAt(k) = " + ls.get(j) + board[d].charAt(k));
				}
				System.out.println("j = " + j);
			}
			ls = tmpls;
			System.out.println("ls.size() = " + ls.size());
		}
		return ls;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String digits = "935";
		List<String> letList = letterCombinations3(digits);
		ListIterator<String> iter = letList.listIterator();
		int count = 0;
		while (iter.hasNext()) {
			count++;
			System.out.print(iter.next() + ",");
		}
		System.out.println();
		System.out.println("count = " + count);
		System.out.println("over main! ");
	}

}
