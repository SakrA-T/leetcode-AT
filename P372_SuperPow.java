package leetCode;

/**
 * @fileName: P372_SuperPow.java
 * @author: AT
 * @version: 2019年7月21日 上午10:13:19
 * @question: leetcode.p372:超级次方
 * @describe: 计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * @describe: 如：a = 2, b = [1,0] -> 1024
 */

public class P372_SuperPow {
	// 思路1：将b数组的值转换保存到power中（不可以，示例中b数组可以超过百位，无法保存）

	public static int superPow1(int a, int[] b) {
		int power = 0;
		for (int i = 0; i < b.length; i++) {
			power = power * 10 + b[i];
		}
		if (a == 1) {
			return a;
		}
		return binaryPow(a % 1337, power);
	}

	public static int binaryPow(int a, int n) {
		if (n == 0) {
			return 1;
		}
		int h = binaryPow(a, n / 2);
		if (n % 2 == 0) {
			return (h * h) % 1337;
		} else {
			// 此处务必两次mod，因为h*h*a的话，以最坏情况1336^3计算，是会超过Integer.MAX_VALUE的
			return ((h * h) % 1337) * a % 1337;
		}
	}

	// 思路2：根据a^b[i]*a^10^(length-1-i)来从后往前逐个计算b数组中的次方
	// 逐个存储a^10^(length-1-i)%1337的值，这样下一个i-1只需要在上一个基础上*10%1337即可
	public static int superPow(int a, int[] b) {
		int rst = 1;
		int ten_power = 0, i_power = 0;
		if (a == 1) {
			return a;
		}
		a = a % 1337;
		ten_power = a;

		for (int i = b.length - 1; i >= 0; i--) {

			i_power = binaryPow(ten_power, b[i]);
			rst = (rst * i_power) % 1337;
			ten_power = binaryPow(ten_power, 10);
		}
		return rst;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 78267;
		int[] b = { 1, 9, 2, 1, 3, 5, 1, 6, 3, 4, 3, 4, 1, 6, 8, 4, 2, 5 };
		System.out.println(superPow(a, b));
	}

}
