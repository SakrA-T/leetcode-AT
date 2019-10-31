package leetCode;

import java.util.Arrays;

/**
 * @className RegExpMatch.java
 * @author AT
 * @version Create Time：2019年6月26日 上午10:19:28
 */
public class RegExpMatch {
//	对于*：匹配零个或多个前面的那一串元素
	public static boolean isSingleMatch(String sstr, String pstr) {
		int len = sstr.length();
		if (len != pstr.length()) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			if (sstr.charAt(i) == pstr.charAt(i) || pstr.charAt(i) == '.') {
				i++;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean isMatch(String s, String p) {
		boolean ismatch = true;
		boolean isend = true;
//		if (p == ".*") {
//			return true;
//		}
		if (p.equals("")) {
			if (s.equals("")) {
				return true;
			}
			return false;
		}
		if (p.substring(p.length() - 1).equals("*")) {
			isend = true;
		} else {
			isend = false;
		}
		if (s.equals("")) {
			// equals()比较的是对象的内容（区分字母的大小写格式），但是如果使用“==”比较两个对象时，比较的是两个对象的内存地址
			if (isend) {
				return true;
			} else {
				return false;
			}
		}
		String regexpArr[] = p.split("\\*");
		int len = regexpArr.length;
//		if (!p.substring(p.length() - 1).equals("*")) {
//			if (regexpArr[len - 1].equals(".")) {
//				s = s.substring(0, s.length() - 1);
//			} else if(sub){
//				s = s.substring(0, s.length() - regexpArr[len - 1].length());
//			}
//		}
		System.out.println("split : " + Arrays.toString(regexpArr));
		if (p == regexpArr[0]) {
			if (s == p) {
				return true;
			} else {
				return false;
			}
		}
		int t_index = 0, rg_len = 0, i = 0;
		String sstr = "";
//		boolean isnext = false;
		for (i = 0; i < len; i++) {
			rg_len = regexpArr[i].length();
			if (t_index >= s.length() || t_index < 0) {
				break;
			}
			// 当最终以非*结尾
			if (i == len - 1 && !isend) {
				System.out.println("i = " + i + " t_index = " + t_index);
				if ((t_index + rg_len) <= s.length()) {

					System.out.println(s.substring(t_index, s.length()));
					sstr = s.substring(t_index, s.length());
					ismatch = isSingleMatch(sstr, regexpArr[i]);

					return ismatch;
				} else {
					break;
				}
			}
			// 当某个匹配字符串为"."时
			if (regexpArr[i].equals(".") && i != len - 1) {
				if (isend) { // 若以*结尾
					return true;
				} else {
					System.out.println("here!");
//					System.out.println(s.substring(s.length() - regexpArr[len - 1].length(), s.length()));
//					if (s.substring(s.length() - regexpArr[len - 1].length(), s.length()).equals(regexpArr[len - 1])) {
//						return true; // 若以非*结尾，且末尾可匹配
//					} else {
//						return false;// 不匹配
//					}
					i = len - 2;
					t_index = s.length() - regexpArr[len - 1].length();
					continue;
				}
			}
			while ((t_index + rg_len) <= s.length()) {
				if (isSingleMatch(s.substring(t_index, t_index + rg_len), regexpArr[i])) {
					System.out.println(s.substring(t_index, t_index + rg_len));
					t_index = t_index + rg_len;
				} else {
					break;
				}
			}
		}
		if (t_index >= s.length()) {
			ismatch = true;
		} else {
			ismatch = false;
		}
		return ismatch;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean ismatch = false;

		String s_t1 = "mississippi";
		String p_t1 = "mis*is*ip*.";
		ismatch = isMatch(s_t1, p_t1);
		System.out.println("Is match ? (false) " + ismatch);

		String s_t2 = "abbbbabc";
		String p_t2 = "ab*bbbbabc";
		ismatch = isMatch(s_t2, p_t2);
		System.out.println("Is match ? (false) " + ismatch);

		String s_t3 = "a";
		String p_t3 = ".*..";
		ismatch = isMatch(s_t3, p_t3);
		System.out.println("Is match ? (false) " + ismatch);

		String s_t4 = "aaaab";
		String p_t4 = "c*a*b";
		ismatch = isMatch(s_t4, p_t4);
		System.out.println("Is match ? (true) " + ismatch);
	}

}
