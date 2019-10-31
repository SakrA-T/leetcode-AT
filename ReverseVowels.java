package leetCode;

/**
 * @className ReverseVowels.java
 * @author AT
 * @version Create Time：2019年7月4日 下午4:40:40
 */
public class ReverseVowels {
	public static boolean isVowel(char c) {
		if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u'
				|| c == 'U') {
			return true;
		}
		return false;
	}

	public static String reverseVowels(String s) {
		StringBuilder rst = new StringBuilder(s);
		int i = 0, j = s.length() - 1;
		while (i < j) {
			while (!isVowel(s.charAt(i)) && i < j)
				i++;
			while (!isVowel(s.charAt(j)) && i < j)
				j--;
			if (i != j) {
				rst.setCharAt(i, s.charAt(j));
				rst.setCharAt(j--, s.charAt(i++));
			}
		}
		return rst.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = ".,";
		String str2 = "leecode";
		System.out.println(str1 + " -> " + reverseVowels(str1));
		System.out.println(str2 + " -> " + reverseVowels(str2));
	}

}
