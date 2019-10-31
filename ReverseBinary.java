package leetCode;

import java.util.Scanner;

/**
 * @className ReverseBinary.java
 * @author SakrA
 * @version 创建时间：2019年3月25日 上午8:26:32
 */
public class ReverseBinary {
	// you need treat n as an unsigned value
	// 优秀代码示例
	public static int reverseBits(int n) {
		int a = 0;
		for (int i = 0; i <= 31; i++) {
			a = a + ((1 & (n >> i)) << (31 - i));
		}
		return a;
	}

	public static int reverseBits2(int n) {
		int ans = 0;
		int i = 32;
		while (i > 0) {
			ans <<= 1;
			ans += n & 1;
			n >>= 1;
			i--;
		}
		return ans;
	}

	public int reverseBits3(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res <<= 1;
			res |= (n & 1); // 注意顺序，不然把最高位给左移出去了
			n >>>= 1;
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		String line;
		while ((line = sc.nextLine()) != null) {
			System.out.println(line);
			int n = Integer.parseInt(line);

			int ret = reverseBits2(n);

			String out = String.valueOf(ret);

			System.out.print(out);
		}
		sc.close();
	}

}
