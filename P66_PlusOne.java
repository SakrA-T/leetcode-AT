package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className P66_PlusOne.java
 * @author AT
 * @version Create Time：2019年7月11日 下午9:17:03
 * @question leetcode.p66:加一
 * @describe 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 */
public class P66_PlusOne {
	// 思路1：从最低位，逐个计算，先将结果放入list中（方便从高位添加元素），再转为int数组
	public static int[] plusOne(int[] digits) {
		List<Integer> ansList = new ArrayList<>();
		int c = 1, sum = 0;
		for (int i = digits.length - 1; i >= 0; i--) {
			sum = digits[i] + c;
			c = (sum > 9) ? 1 : 0;
			ansList.add(0, sum % 10);
		}
		if (c == 1) {
			ansList.add(0, 1);
		}
		int[] ans = new int[ansList.size()];
		int count = 0;
		for (Integer i : ansList) {
			ans[count++] = i;
		}
		return ans;
	}

	// 思路2：（只有当所有位全为9时，才会出现最终进位，否则，使用原数组即可）
	// 从低位遍历，最先不为9的位，加一，遍历过程中，为9的位全置为0，该位之前的数全不变。若遍历结束，函数未返回，说明是所有位都为9的情况，只需要创建一个比当前数组大1的数组，并将其最高位0位置1即可。
	public static int[] plusOne1(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] != 9) {
				digits[i] += 1;
				return digits;
			} else {
				digits[i] = i == 0 ? 1 : 0;
			}
		}
		int[] newDigits = new int[digits.length + 1];
		newDigits[0] = 1;
		return newDigits;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] digits = { 9, 9 };
		int[] ans = plusOne1(digits);
		System.out.println(Arrays.toString(ans));
	}

}
