package leetCode;

/**
 * @className P43_StringMultiply.java
 * @author AT
 * @version Create Time：2019年7月12日 下午12:18:11
 * @question leetcode.p43:字符串相乘（p415相加）
 * @describe 给定两个以字符串形式表示的非负整数num1和num2，返回 num1和num2的乘积，它们的乘积也表示为字符串形式。
 */
public class P43_StringMultiply {
	// 思路1：将num1中每个元素，逐个乘num2中的每个元素，如num1[0,num1.length-1]*num2[0]，记录；
	// 并对于第num2[i]的计算结果，在后面添加len2-1-i个0,以便后续单个数乘积结果的加法计算
	// 时间复杂度O(n1*n2)，空间复杂度O(n2*(n1+n2))

	// 实际上可以，利用错位相乘的法则求解，注意第i位和第j位相乘之后出现的位置至多为i+j和i+j+1两个位置，每次计算之后对对应两个位置更新，则可以变乘边求和结果
	public static String multiply(String num1, String num2) {
		if (num1 == "0" || num2 == "0") {
			return 0 + "";
		}
		String ansStr = "";
		int len1 = num1.length(), len2 = num2.length();
		String[] tempArr = new String[len2];
		int c = 0;
//		int p1 = 0,p2 = 0;
		// 对num2中各元素，逐个计算与num1中元素的乘积结果。
		for (int i = len2 - 1; i >= 0; i--) {
			tempArr[i] = "";
			for (int j = len1 - 1; j >= 0; j--) {
				c += (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
				tempArr[i] = c % 10 + tempArr[i];
				c /= 10;
			}
			if (c != 0) {
				tempArr[i] = c + tempArr[i];
				c = 0;
			}
			for (int k = len2 - 1 - i; k > 0; k--) {
				tempArr[i] += "0";
			}
		}
		// 将所有乘积结果做加和计算
		// 由于tempArr[0]必然是最长的乘积结果，故以其长度为边界值
		int max_len = tempArr[0].length();
		int sum = 0;
		for (int i = 0; i < max_len; i++) {
			for (int j = 0; j < len2; j++) {
				int t_index = tempArr[j].length() - 1 - i;
				// t_index按加法计算思路，从低位向高位计算，如果当前乘积结果最高位已经计算完毕，则跳过它
				if (t_index >= 0) {
					sum += tempArr[j].charAt(t_index) - '0';
				}
			}
			ansStr = sum % 10 + ansStr;
			sum /= 10;
		}
		if (sum != 0) {
			ansStr = sum + ansStr;
		}
		return ansStr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num1 = "9";
		String num2 = "99";
		System.out.println("my answer : " + multiply(num1, num2));
		System.out.println("right answer : " + Integer.parseInt(num1) * Integer.parseInt(num2));
	}

}
