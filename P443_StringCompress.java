package leetCode;

/**
 * @className P443_StringCompress.java
 * @author AT
 * @version Create Time：2019年7月13日 上午11:55:12
 * @question leetcode.p443:压缩字符串
 * @describe 给定一组字符，使用原地算法将其压缩。压缩后的长度必须始终小于或等于原数组长度。数组的每个元素应该是长度为1的字符（不是int整数类型）。在完成原地修改输入数组后，返回数组的新长度。要求空间复杂度为O(1)。
 * @describe 如：["a","b","b","b","b","b","b","b","b","b","b","b","b"]，压缩为：["a","b","1","2"]
 */
public class P443_StringCompress {

	public static int compress(char[] chars) {
		int count = 0, i = 0, j = 1;
		// 保存将字符计数值转换为的字符串，方便添加到字符数组中
		String cStr = "";
		for (i = 0; i <= chars.length; i++) {
			if ((i == chars.length) || (i > 0 && chars[i] != chars[i - 1])) {
				if (count > 1) {
					cStr = Integer.toString(count);
					for (char c : cStr.toCharArray()) {
						chars[j++] = c;
					}
				}
				if (i != chars.length) {
					chars[j++] = chars[i];
				}
				count = 0;
			}
			count++;
		}
		return j;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chars = { 'a', 'a', 'a', 'a', 'b', 'b', 'c', 'c' };
		int len = compress(chars);
		System.out.println(len);
		System.out.print("[ ");
		for (int i = 0; i < len; i++) {
			System.out.print(chars[i] + " ");
		}
		System.out.println("]");
//		System.out.println((char) (9 + '0'));
	}

}
