package leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @className P46_FullPermute.java
 * @author AT
 * @version Create Time：2019年7月8日 下午8:58:29
 * @question leetcode.p46:给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class P46_FullPermutation {
	// 直接调用p31的nextPermutation(int[] nums)函数
	public static void reverseArr(int[] nums, int left, int right) {
		if (left == right) {
			return;
		}
		int mid = left + (right - left) / 2, tag = 0;
		while (left <= mid) {
			tag = nums[left];
			nums[left++] = nums[right];
			nums[right--] = tag;
		}
	}

	public static void nextPermutation(int[] nums) {
		int len = nums.length;
		int tag = 0;
		int i = 0, j = 0;
		// 从后查找，找到满足nums[i] < nums[i+1]的最大索引（第一个）及其对应值
		for (i = len - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				tag = nums[i];
				break;
			}
		}
		// 若已经是最大排列了，返回其反转排列
		if (i == -1) {
			reverseArr(nums, 0, len - 1);
			return;
		}
		// 寻找 [i+1,len-1] 中，比tag1大的最小值（最大索引），与其交换。因为要查找的子数组是从大到小排序的，所以直接从后面遍历即可
		// 交换后，nums[i+1,j-1] > nums[j] >= nums[j+1,len-1]
		for (j = len - 1; j > i; j--) {
			if (nums[j] > tag) {
				nums[i] = nums[j];
				nums[j] = tag;
				break;
			}
		}
		// 将 [i+1,len-1] 反转
		reverseArr(nums, i + 1, len - 1);
	}

	// 求阶乘（全排列个数）
	public static int getNFactorial(int n) {
		if (n == 0) {
			return 1;
		}
		return n * getNFactorial(n - 1);
	}

	// 数组转List
	public static List<Integer> arrToList(int[] arr) {
		List<Integer> list = new ArrayList<>();
//		for (int i = 0; i < arr.length; i++) {
//			list.add(arr[i]);
//		}
		for (int a : arr) {
			list.add(a);
		}
		return list;
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ptList = new ArrayList<>();
		int len = nums.length;
		// 减去本身一个
		int count = getNFactorial(len) - 1;
		// 为什么直接写 Arrays.toList(nums); 会出错？？？？？？？？？？？
		List<Integer> item = arrToList(nums);
		ptList.add(item);
		for (int i = 0; i < count; i++) {
			nextPermutation(nums);
			item = arrToList(nums);
			ptList.add(item);
		}
		return ptList;
	}

	// 优秀代码示例1————回溯法，不大理解
	// 思路：将第j个数字与第j,j+1,j+2,...,len(nums) - 1个数字分别交换，得到len(nums) - j种情况；
	// 在每种情况下递归，将第j+1处数字与第j+1,j+2,...,len(nums) - 1处数字分别交换；
	// 如[2,1,3,4,5]
	// 是1,2(j=0,j+1=1)交换之后的结果，在这种情况下，将1与3,4,5,分别交换获得结果，如1,3交换[2,3,1,4,5]
	public static List<List<Integer>> permute1(int[] nums) {
		int n = nums.length;
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		List<List<Integer>> result = new LinkedList<>();
		for (int a : nums) {
			tmp.add(a);
		}
		backTracking(n, 0, tmp, result);
		return result;
	}

	public static void backTracking(int n, int start, ArrayList<Integer> tmp, List<List<Integer>> result) {
		// 如果所有整数都遍历完了
		if (n == start) {
			// 问：为什么直接写result.add(tmp); 会出错？？？？？？？？？？？？
			// 答：Java中的对象是引用传递，直接output.add(nums)只是把nums的引用拷贝了一份，更改nums的时候就会影响到output中的每一份nums的引用。用拷贝构造函数就能进行值传递，把整个nums拷贝了一份，后面再修改就不会受影响。
			result.add(new ArrayList<Integer>(tmp));
		}
		for (int i = start; i < n; i++) {
			// Collections.swap() 交换指定位置的两个元素
			// 把第i个整数放在当前排列中的第一位
			Collections.swap(tmp, i, start);
			// 使用下一个整数完成排列
			backTracking(n, start + 1, tmp, result);
			// 回溯
			// 每个递归跳出后，要将交换过的元素还原；不还原会导致一次性交换了好几个元素
			Collections.swap(tmp, i, start);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 4 };
		List<List<Integer>> ptList = permute1(nums);
		ListIterator<List<Integer>> iter1 = ptList.listIterator();
		ListIterator<Integer> iter2;
		int count = 0;
		while (iter1.hasNext()) {
//			Object[] arr = iter1.next().toArray();
			iter2 = iter1.next().listIterator();
			System.out.print("[ ");
//			int len = arr.length;
//			for (int i = 0; i < len; i++) {
//				System.out.print(arr[i] + " ");
//			}
			count++;
			while (iter2.hasNext()) {
				System.out.print(iter2.next() + " ");
			}
			System.out.println("]");
		}
		System.out.println("count = " + count);
	}

}
