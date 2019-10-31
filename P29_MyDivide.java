package leetCode;

/**
 * @className P29_MyDivide.java
 * @author AT
 * @version Create Time：2019年7月6日 下午7:47:41
 * @question leetcode.p29:给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和mod运算符.返回被除数 dividend 除以除数 divisor 得到的商。
 */
public class P29_MyDivide {
	// 被除数和除数均为 32 位有符号整数,其数值范围是 [−2^31, 2^31 − 1]。除数不为 0。
	// 如果除法结果溢出，则返回 2^31 − 1。
	public static int divide(int dividend, int divisor) {
		int rst = 0;
		if (divisor == 1) {
			return dividend;
		}
		// 排除除数或被除数为-2^31的情况，因为后续取绝对值会仍然是 -2^31 而出错
		if (dividend == Math.pow(-2, 31)) {
			if (divisor == -1) {
				return (int) (Math.pow(2, 31) - 1);
			} else if (divisor == Math.pow(-2, 31)) {
				return 1;
			}
			dividend += Math.abs(divisor);
			rst = 1;
		}
		if (divisor == Math.pow(-2, 31)) {
			return 0;
		}
		int minus = 1;
		if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
			minus = -1;
		}
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		// 增倍除数
		int mult_div = 0, mult = 1;
		while (dividend >= divisor) {
			mult_div = divisor;
			mult = 1;
			while (dividend >= mult_div) {
				dividend = dividend - mult_div;
				rst += mult;
				if (mult_div <= Integer.MAX_VALUE / 2) {
					mult <<= 1; // 左移1位，相当于乘2
					mult_div <<= 1;
				}
			}
		}
		return rst * minus;
	}

	// 优秀代码示例1
	// 主要参考for循环部分
	public static int divide1(int dividend, int divisor) {
		// 异或决定符号位
		boolean navigate = (dividend ^ divisor) < 0;
		int result = 0;
		if (divisor == 1) {
			return dividend;
		}
		if (dividend == divisor) {
			return 1;
		}
		if (divisor == Integer.MIN_VALUE) {
			return 0;
		}
		// 因为规定了最大的存储为32位，所以不能用long
		// 如果被除数是负数最小值，在计算的时候是转化成正数然后除的，当除数长度和被除数相同时，被除数就会全部一起被转成正数，这个时候会溢出，要报错
		// 所以在这里先判断是不是最小值，如果是，先减去一份除数，使result=1，这样就不会溢出了
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == -1) {
				return Integer.MAX_VALUE;
			} else {
				dividend = dividend - divisor;
				result = 1;
			}
		}
		// 这个时候被除数和除数都不可能溢出，将被除数和除数转成正数
		dividend = dividend < 0 ? 0 - dividend : dividend;
		divisor = divisor < 0 ? 0 - divisor : divisor;
		// *主要部分*
		for (int i = 31; i >= 0; i--) {
			if (dividend >> i >= divisor) {
				result += 1 << i;
				// 左移一位相当于乘2
				dividend -= divisor << i;
			}
		}
		return navigate ? 0 - result : result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dividend = -1010369383;
		int divisor = -2147483648;
		int rst = divide(dividend, divisor);
		System.out.println(dividend + " / " + divisor + " = " + rst);
		System.out.println(dividend / divisor + "," + (dividend / divisor == rst ? "true" : "false"));

	}

}
