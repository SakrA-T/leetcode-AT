package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P784_LetterCasePermutation.java
 * @author: AT
 * @version: 2019年7月26日 下午7:25:25
 * @question: leetcode.p784:字母大小写全排列
 * @describe: 给定一个字符串S，通过将字符串S中的 每个 字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 */

public class P784_LetterCasePermutation {
	// 思路1：可以用二进制位的方式解，每个字母都有转变大小写或不转变大小写的两种选择
	// 思路2：直接遍历即可，每遍历一个字符，生成当前列表中所存在的元素个数的一倍（将所存在的元素的对应该字符转变大小写形成新元素后添加到列表中），故若有m个字符，则最终形成集合中元素个数为2^m
	public static List<String> letterCasePermutation(String S) {
		List<String> strList = new ArrayList<>();
		if (S == "") {
			return strList;
		}
		// 将S本身添加到列表中
		strList.add(S);
		char c = ' ';
		for (int i = 0; i < S.length(); i++) {
			c = S.charAt(i);
			if (Character.isDigit(c)) {
				continue;
			} else if (Character.isLowerCase(c)) {
				c = Character.toUpperCase(c);
			} else {
				c = Character.toLowerCase(c);
			}
			int size = strList.size();
			for (int j = 0; j < size; j++) {
				String tempStr = strList.get(j);
				char[] tempArr = tempStr.toCharArray();
				tempArr[i] = c;
				strList.add(new String(tempArr));
			}
		}
		return strList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "a23bC5z9";
		List<String> strList = letterCasePermutation(S);
		System.out.print("[ ");
		for (String str : strList) {
			System.out.print("\"" + str + "\" ");
		}
		System.out.println("]");
	}

}
