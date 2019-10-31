package leetCode;

/**
 * @className ConvertToZ.java
 * @author SakrA
 * @version 创建时间：2019年4月1日 下午4:15:05
 */
public class ConvertToZ {
	// 我的优秀代码！
	// 找数字的规律
	public static String convert(String s, int numRows) {
		StringBuilder zStr = new StringBuilder();
		int len = s.length();
		if (numRows == 1) {
			return s;
		}
		for (int i = 0; i < numRows; i++) {
			int j = i;
			while (j < len) {
				if (i != numRows - 1) {
					zStr.append(s.charAt(j));
					j += 2 * (numRows - 1 - i);

				}
				if (i != 0 && j < len) {
					zStr.append(s.charAt(j));
					j += 2 * i;
				}
			}
		}
		return zStr + "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "L";
		int numRows = 1;
		String zStr = convert(s, numRows);
		System.out.println(zStr);
	}

}
