package leetCode;

/**
 * @className P58_LengthOfLastWord.java
 * @author AT
 * @version Create Time：2019年7月17日 下午5:00:05
 * @question leetcode.p58:最后一个单词的长度
 * @describe 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。 如果不存在最后一个单词，请返回0。
 */
public class P58_LengthOfLastWord {

	public static int lengthOfLastWord(String s) {
		int lastWordLen = 0;
		s.trim();
		int len = s.length();
		System.out.println(s + ", len = " + len);
		boolean isBegin = false;
		for (int i = len - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (isBegin) {
					break;
				}
				continue;
			}
			isBegin = true;
			lastWordLen++;
		}
		return lastWordLen;
	}

	// 优秀代码示例1
	// 另一种跳过空格的方式，好像更优秀更简洁
	public static int lengthOfLastWord1(String s) {
		int num = 0, len = s.length();
		while (len > 1 && s.charAt(len - 1) == ' ')
			len--;
		for (int i = len - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ')
				return num;
			else
				num++;
		}
		return num;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " my name is xyzt ";
		System.out.println(lengthOfLastWord(s));
	}

}
