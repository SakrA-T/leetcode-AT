package leetCode;

/**
 * @className MyAtoi.java
 * @author SakrA
 * @version 创建时间：2019年4月2日 下午6:54:04
 */
public class MyAtoi {
	// 丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
	// 第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的*连续*数字组合起来，作为该整数的正负号；
	// 假如第一个非空字符是数字，则直接将其与之后*连续*的数字字符组合起来，形成整数。
	// 忽略有效整数之后多余字符；
	// 不能进行有效的转换时，请返回 0。
	// INT_MAX (231 − 1) 或 INT_MIN (−231)
	public static int myAtoi(String str) {
		if (str.matches("\\s.*")) {
			str = str.split("[\\s\\D]")[1];
		} else {
			str = str.split("[\\s]+")[0];
		}
		String splitStr = str;
		if (!str.matches("(\\-\\d.*|\\+\\d.*|\\d.*)")) {
			System.out.println("0");
			return 0;
		}
		System.out.println(splitStr);
		if (splitStr.matches("[\\+\\-].*")) {
			splitStr = splitStr.substring(1, splitStr.length());
		}
		String[] splitArr = splitStr.split("\\D+", 2);
//		for (String item : splitArr) {
//			System.out.println("item = " + item);
//			System.out.println(str);
//			splitStr = splitStr.replaceFirst(item, "");
//			System.out.println(splitStr);
//		}
		splitStr = splitArr[0];
		System.out.println(splitArr[0]);
		if (splitStr.matches("[0].*")) {
			splitStr = splitStr.replaceFirst("0+", "");
		}
		if (splitStr.length() == 0) {
			return 0;
		}
		if (str.charAt(0) == '-') {
			if (splitStr.length() > 10) {
				return Integer.MIN_VALUE;
			}
//			splitStr = splitStr.substring(0, splitStr.length() - 1);
//			if (Integer.parseInt(splitStr) >Integer.MAX_VALUE / 10||Integer.parseInt(splitStr) ==Integer.MAX_VALUE / 10&&(end-'0'>8)) {
//				return Integer.MIN_VALUE;
//			}
			if (-1 * Double.parseDouble(splitStr) <= Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
			return Integer.parseInt(splitStr) * -1;
		} else {
			if (splitStr.length() > 10) {
				return Integer.MAX_VALUE;
			}
//			splitStr = splitStr.substring(0, splitStr.length() - 1);
			if (Double.parseDouble(splitStr) > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			return Integer.parseInt(splitStr);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "  42abac-+123ab";
		int rst = myAtoi(str);
		System.out.println(str + " transform to int is " + rst);
	}

}
