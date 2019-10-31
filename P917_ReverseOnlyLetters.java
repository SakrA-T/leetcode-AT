/** 
 * @fileName: P917_ReverseOnlyLetters.java  
 * @author: AT
 * @version: 2019年7月20日 下午8:23:34 
 * @question: leetcode.p917:仅仅反转字母
 * @describe: 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 */
package leetCode;

public class P917_ReverseOnlyLetters {

	public static String reverseOnlyLetters(String S) {
		int len = S.length();
		char[] sArray = S.toCharArray();
		int i = 0, j = len - 1;
		char c = ' ';
		while (i < j) {
			while (i < j && !Character.isLetter(sArray[i])) {
				i++;
			}
			while (i < j && !Character.isLetter(sArray[j])) {
				j--;
			}
			if (i < j) {
				c = sArray[i];
				sArray[i] = sArray[j];
				sArray[j] = c;
				i++;
				j--;
			}
		}
		return String.valueOf(sArray);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "7_28]";
		System.out.println(reverseOnlyLetters(S));
	}

}
