package leetCode;

/**
 * @fileName: P1017_BaseNeg2.java
 * @author: AT
 * @version: 2019年7月24日 下午5:29:12
 * @question: leetcode.p1017:负二进制转换
 * @describe: 给出数字 N，返回由若干 "0" 和 "1"组成的字符串，该字符串为 N 的负二进制（base-2）表示。
 * @describe: 负二进制：如110，表示(-2)^2+(-2)^1+(-2)^0 = 2
 */

public class P1017_BaseNeg2 {
	// 3/(-2)= -1; 3%(-2) = 1;(-3)/(-2) = 1; (-3)%(-2) = -1
	public static String baseNeg2(int N) {
		String ans = "";
		if (N == 0) {
			return "0";
		}
		int mod = 0;
		while (N != 0) {
			mod = N % (-2);
			ans = Math.abs(mod) + ans;
			N = N / (-2);
			// 因为实际上如同求二进制一样，N%(-2)应该以0或1为结果才能得到正确的负二进制数,但是负奇数MOD(-2)会得到-1的结果，如-5%(-2)=2...-1,实际上我们想要的是-5=(-2)*3+1，所以需要转换一下；正奇数余-2余数为1
			if (mod == -1) {
				N++;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 10;
		System.out.println(baseNeg2(N));
		System.out.print((-5) % (-2));
	}

}
