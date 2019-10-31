package leetCode;

import java.util.Scanner;

/**
 * @className ReverseInteger.java
 * @author SakrA
 * @version 创建时间：2019年3月24日 下午3:26:59
 */
public class ReverseInteger {

	public static int reverse(int x) {
		double rst = 0;
		double max = Math.pow(2, 31);
		if (x == 0) {
			return 0;
		}
		int[] bitArray = new int[32];
		if (x < 0) {
			bitArray[0] = -1;
			x = -x;
		} else {
			bitArray[0] = 1;
		}
		int count = 0;
		while (x > 0) {
			bitArray[++count] = x % 10;
			x = x / 10;
		}
		for (int i = 1; i <= count; i++) {
			rst += bitArray[i] * Math.pow(10, (count - i));
			if (rst > max || (rst == max && bitArray[0] == 1)) {
				return 0;
			}
		}
		return ((int) rst) * bitArray[0];
	}

	// 优秀示例代码
	// 假如x是m位数，如翻转之后在边界之外，前m-1位翻转之后不可能<=Integer.MAX_VALUE / 10
	// 或< Integer.MIN_VALUE / 10；（-15/10=-1；-15%10=-5）
	// x本身不在Integer范围内的话也不会运行该函数
	public static int reverse2(int x) {
		int result = 0;
		while (x != 0) {
			if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10)
				return 0;
			result = result * 10 + x % 10;
			x /= 10;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println(-15 / 10);
		System.out.println(Integer.MIN_VALUE);
		int number = 0;
		while (sc.hasNextLine()) {
			number = reverse2(Integer.parseInt(sc.nextLine()));
			System.out.println("The number after reverse is " + number);
		}
		sc.close();
	}

}
