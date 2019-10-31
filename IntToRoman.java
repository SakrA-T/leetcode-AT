package leetCode;

import java.util.Scanner;

/**
 * @className IntToRoman.java
 * @author SakrA
 * @version 创建时间：2019年3月26日 上午9:14:58
 */
public class IntToRoman {
	public static String intToRoman(int num) {
		String romanStr = "";
		String[] romanArr = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] intArr = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		int i = 0, bit = 0;
		while (num != 0) {
			bit = num / intArr[i];
			for (; bit != 0; bit--) {
				romanStr += romanArr[i];
			}
			num %= intArr[i++];
		}
		return romanStr;
	}

	// 优秀代码示例
	static int[] vals = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	static String[] roms = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	public String intToRoman2(int num) {
		StringBuilder buff = new StringBuilder();

		for (int i = 0; i < vals.length; i++) {
			while (num >= vals[i]) {
				buff.append(roms[i]);
				num -= vals[i];
			}
		}
		return buff.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int num = 0;
		while ((num = sc.nextInt()) != 0) {
			String romanStr = intToRoman(num);
			System.out.println(romanStr);
		}
		sc.close();
	}

}
