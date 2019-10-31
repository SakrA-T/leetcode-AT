package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @className P788_RotatedDigits.java
 * @author AT
 * @version Create Time：2019年7月6日 上午11:45:56
 * @question leetcode.p788:我们称一个数X为好数,如果它的每位数字逐个地被旋转180度后,我们仍可以得到一个有效的，且和X不同的数。要求每位数字都要被旋转。
 */
public class P788_RotatedDigits {
	static List<Integer> goodNums = new ArrayList<>();

	public static int rotatedDigits(int N) {
		int count = 0;
		List<Character> invaildList = new ArrayList<>();
		invaildList.add('3');
		invaildList.add('4');
		invaildList.add('7');
		List<Character> vaildList = new ArrayList<>();
		vaildList.add('2');
		vaildList.add('5');
		vaildList.add('6');
		vaildList.add('9');
		boolean isVaild = false;
		// 若只包含0、1、8则旋转之后不变，故不是好数
		// 包含无效数字，不可旋转，不是好数
		for (int i = 1; i <= N; i++) {
			String ntostr = Integer.toString(i);
			isVaild = false;
			for (int j = 0; j < ntostr.length(); j++) {
				if (invaildList.contains(ntostr.charAt(j))) {
					isVaild = false;
					break;
				}
				if (vaildList.contains(ntostr.charAt(j))) {
					isVaild = true;
				}
			}
			if (isVaild) {
				goodNums.add(Integer.parseInt(ntostr));
				count++;
			}
		}
		return count;
	}

	// 优秀代码示例1
	// 0 - 9: 0,1,8 必须和其他好数配合，2，5，6，9 是好数，其余一出现就不是好数
	private static int[] sMap = { 0, 0, 1, -1, -1, 1, 1, -1, 0, 1 };

	public static int rotatedDigits1(int N) {
		int count = 0;

		for (int i = 1; i <= N; i++) {
			if (isGood(i)) {
				count++;
			}
		}
		return count;
	}

	// 0 和任何数或运算都是那个数，-1 和任何数或运算都是 -1（如果内存是8bit，则-1在内存中储存为11111111）
	// 计算机中存储的都是数的补码
	// 正数的原码、反码、补码都是相同的；而负数的原码、反码、补码是不一样的，补码=原码取反+1（符号位负为1不变）。
	private static boolean isGood(int i) {
		int flag = 0;
		while (i > 0) {
			// 从末位开始取，至最高位
			int mod = i % 10;
			// | (无条件或、位或)，原数转为二进制计算。两个位只要有一个为1，那么结果就是1，否则就为0
			flag |= sMap[mod];
			i /= 10;
		}
		return flag > 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 100;
		int count = rotatedDigits(N);
		System.out.println("count = " + count);
		if (goodNums != null) {
			ListIterator<Integer> iter = goodNums.listIterator();
			while (iter.hasNext()) {
				System.out.print(iter.next() + " ");
			}
		} else {
			System.out.println(goodNums);
		}
	}

}
