package leetCode;

/**
 * @fileName: P605_CanPlaceFlowers.java
 * @author: AT
 * @version: 2019年7月22日 下午6:47:21
 * @question: leetcode.p605:种花问题
 * @describe: 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数n。
 * @describe: 花坛种植一部分花，要求相邻地块不可同时有花，问是否可再种入n朵花？能则返回True，不能则返回False。
 */

public class P605_CanPlaceFlowers {

	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		int len = flowerbed.length;
		boolean isAble = true;
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (flowerbed[i] == 0) {
				count += (isAble ? 1 : 0);
				isAble = !isAble;
			} else {
				if (!isAble) {
					count--;
				}
				isAble = false;
			}
		}
		return (n <= count) ? true : false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] flowerbed = { 1, 0, 0, 0, 0, 1 };
		int n = 2;
		System.out.println(canPlaceFlowers(flowerbed, n));
	}

}
