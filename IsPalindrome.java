package leetCode;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @className IsPalindrome.java
 * @author SakrA
 * @version 创建时间：2019年3月26日 上午8:36:11
 */
public class IsPalindrome {
	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int count = 0;
		ArrayList<Integer> bitArr = new ArrayList<>(); // new ArrayList();也行
		while (x != 0) {
			bitArr.add(x % 10);
			x /= 10;
			count++;
		}
		for (int i = 0; i < count / 2; i++) {
			if (bitArr.get(i) != bitArr.get(count - i - 1)) {
				return false;
			}
		}
		return true;
	}

	// 优秀代码示例
	public boolean isPalindrome2(int x) {
		if (x < 0) {
			return false;
		}
		int m = x;
		int y = 0;
		while (x > 0) { // 将数值翻转
			y = y * 10 + x % 10; // 极好的方法，多次出现，值得借鉴，不断向后面添加数字
			x = x / 10;
		}
		if (m == y) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int x = 0;
		boolean flag = true;
		while ((x = sc.nextInt()) != 0) {
			flag = isPalindrome(x);
			if (flag) {
				System.out.println(x + " is a palindrome !");
			} else {
				System.out.println(x + " is not a palindrome !");
			}
		}
		sc.close();
	}

}
