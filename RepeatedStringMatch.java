package leetCode;

/**
 * @className RepeatedStringMatch.java
 * @author AT
 * @version Create Time：2019年7月6日 上午10:42:57
 * @question leetcode.p686:给定两个字符串A和B,寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回-1。
 */
public class RepeatedStringMatch {

	public static int repeatedStringMatch(String A, String B) {
		if (B.length() == 0) {
			return 0;
		}
		if (A.length() == 0) {
			return -1;
		}
		int amount = -1, i = 0;
		String C = "";
		// 最小次数必是当A*amount>B&&A*(amount-1)<B
		for (i = 0; i < Math.ceil(B.length() * 1.0 / A.length()) + 1; i++)
			C = C + A;
		amount = i;
		System.out.println("amount = " + amount + "," + C);
		for (i = 0; i < A.length() && i + B.length() <= C.length(); i++) {
			if (A.charAt(i) == B.charAt(0)) {
				if (C.substring(i, i + B.length()).equals(B)) {
					System.out.println(i + B.length() + "," + A.length() * (amount - 1));
					if (i + B.length() <= A.length() * (amount - 2)) {
						amount -= 2;
					} else if (i + B.length() <= (A.length() * (amount - 1))) {
						amount -= 1;
					}
					return amount;
				}
			}
		}
		return -1;
	}

	// 优秀代码示例1
	public static int repeatedStringMatch1(String A, String B) {
		StringBuilder sb = new StringBuilder(A);
		// 最多重复A串个数为B长度/A长度+2（假设B左右两端各多出一个A串），再多就是无意义的重复了
		int max = B.length() / A.length() + 2;
		int i = 1;
		while (i <= max) {
			// indexOf(String str) 返回指定子字符串第一次出现的字符串中的索引。
			// 此处indexOf或者lastIndexOf都可以，因为只匹配一个即可
			if (sb.indexOf(B) > -1) {
				// 在 sb 中找到了子串 B
				return i;
			} else {
				// 当前sb串未找到，则再添加一个A串
				i++;
				sb.append(A);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String A = "abc";
		String B = "abcabc";
		int amount = repeatedStringMatch1(A, B);
		System.out.println(amount);
	}

}
