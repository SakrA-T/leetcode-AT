package leetCode;

/**
 * @className P415_StringAdd.java
 * @author AT
 * @version Create Time：2019年7月12日 下午5:40:00
 * @question leetcode.p415:字符串相加（p43相乘）
 * @describe 给定两个字符串形式的非负整数num1和num2，计算它们的和。
 */
public class P415_StringAdd {

	public static String addStrings(String num1, String num2) {
		int len1 = num1.length(), len2 = num2.length();
		String ansStr = "";
		int i = len1 - 1, j = len2 - 1;
		int c = 0;
		while (i >= 0 || j >= 0) {
			if (i >= 0) {
				c += num1.charAt(i) - '0';
				i--;
			}
			if (j >= 0) {
				c += num2.charAt(j) - '0';
				j--;
			}
			ansStr = c % 10 + ansStr;
			c /= 10;
		}
		if (c != 0) {
			ansStr = c + ansStr;
		}
		return ansStr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num1 = "154685796";
		String num2 = "1365";
		System.out.println("my answer : " + addStrings(num1, num2));
		System.out.println("right answer : " + (Integer.parseInt(num1) + Integer.parseInt(num2)));
	}

}
