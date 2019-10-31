package leetCode;

/**
 * @className P434_CountSegments.java
 * @author AT
 * @version Create Time：2019年7月18日 下午7:47:19
 * @question leetcode.p434:字符串中的单词数
 * @describe 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 */
public class P434_CountSegments {

	public static int countSegments(String s) {
		int count = 0;
		boolean isNew = true;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ' && isNew) {
				count++;
				isNew = false;
			}
			if (s.charAt(i) == ' ' && !isNew) {
				isNew = true;
			}
		}
		return count;
	}

	// 示例1
	// 将s以空格隔开，在遍历过程中判断字符串是否为空，不为空则计数
	public static int countSegments1(String s) {
		String[] sArray = s.split(" ");
		int count = 0;
		for (String item : sArray) {
			if (!item.equals("")) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " Ha!  Let's take   LeetCode contest  ";
		System.out.println(countSegments(s));
	}

}
