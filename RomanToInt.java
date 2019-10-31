package leetCode;

import java.util.Scanner;

/**
 * @className RomantoInt.java
 * @author SakrA
 * @version 创建时间：2019年3月26日 下午7:53:33
 */
public class RomanToInt {
	public static int romanToInt(String s) {
		String[] romanArr = { "CM", "CD", "XC", "XL", "IX", "IV", "M", "D", "C", "L", "X", "V", "I" };
		String[] replace = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D" };
		int i = 0, j = 0;
		int num = 0;
		for (; i < romanArr.length; i++) {
			s = s.replaceAll(romanArr[i], replace[i]);
		}
		for (; j < s.length(); j++) {
			switch (s.charAt(j)) {
			case '1':
				num += 900;
				break;
			case '2':
				num += 400;
				break;
			case '3':
				num += 90;
				break;
			case '4':
				num += 40;
				break;
			case '5':
				num += 9;
				break;
			case '6':
				num += 4;
				break;
			case '7':
				num += 1000;
				break;
			case '8':
				num += 500;
				break;
			case '9':
				num += 100;
				break;
			case 'A':
				num += 50;
				break;
			case 'B':
				num += 10;
				break;
			case 'C':
				num += 5;
				break;
			case 'D':
				num += 1;
				break;
			}
		}
		return num;
	}

	// 优秀代码示例
	public int romanToInt2(String s) {
		int x = 1000;
		int sum = 0;

		for (int i = 0; i < s.length(); i++) {
			int xOld = x;
			switch (s.charAt(i)) {
			case 'M':
				x = 1000;
				break;
			case 'D':
				x = 500;
				break;
			case 'C':
				x = 100;
				break;
			case 'L':
				x = 50;
				break;
			case 'X':
				x = 10;
				break;
			case 'V':
				x = 5;
				break;
			case 'I':
				x = 1;
				break;
			default:
				System.out.println("输入错误");
				break;
			}
			// 若后面出现比前面字母表示数值大的，则总数值减去之前加上的小数值，
			// 再加上之前字母与当前字母组合表示的数值。
			if (xOld < x) {
				x = x - xOld - xOld;
			}
			sum = sum + x;
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = "";
		while ((str = sc.nextLine()) != "") {
			int num = romanToInt(str);
			System.out.println(num);
		}
		sc.close();
	}
}
