package leetCode;

/**
 * @className P38_CountAndSay.java
 * @author AT
 * @version Create Time：2019年7月13日 上午11:32:46
 * @question leetcode.p38:报数
 * @describe 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。给定一个正整数n（1≤n≤30），输出报数序列的第n项。
 */
public class P38_CountAndSay {

	public static String countAndSay(int n) {
		String pre_s = "1";
		String cur_s = "";
		int count = 0;
		for (int i = 2; i <= n; i++) {
			// 遍历s并记录报数
			count = 0;
			cur_s = "";
			if (pre_s.length() == 1) {
				cur_s = "1" + pre_s;
				pre_s = cur_s;
				continue;
			}
			for (int j = 0; j < pre_s.length(); j++) {
				if (j > 0 && pre_s.charAt(j) != pre_s.charAt(j - 1)) {
					cur_s = cur_s + count + pre_s.charAt(j - 1);
					count = 0;
				}
				count++;
			}
			cur_s = cur_s + count + pre_s.charAt(pre_s.length() - 1);
			pre_s = cur_s;
		}
		return pre_s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		String str = countAndSay(n);
		System.out.println(str);
	}

}
