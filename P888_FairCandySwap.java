package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @className P888_FairCandySwap.java
 * @author AT
 * @version Create Time：2019年7月9日 下午5:46:33
 * @question leetcode.p888:公平的糖果交换
 * 
 * @describe A[i]是A第i块糖的大小，B[j]是B第j块糖的大小。交换一个糖果，使其拥有相同的糖果总量。（拥有的糖果总量是他们拥有的糖果大小的总和<Integer.MAX_VALUE。）
 * @describe 返回一个整数数组ans，其中ans[0]是A必须交换的糖果棒的大小，ans[1]是B必须交换的糖果棒的大小。(Tip:默认答案一定存在)
 */
public class P888_FairCandySwap {
	// 思路：对于B中每一个数x，在A中查找x+diff/2，如果存在则直接返回
	public static int binarySearch(int[] arr, int n) {
		int left = 0, right = arr.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (arr[mid] == n) {
				return mid;
			} else if (arr[mid] > n) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	public static int[] fairCandySwap(int[] A, int[] B) {
//		int[] ans = new int[2];
		int[] ans = { -1, -1 };
		Arrays.sort(A);
		Arrays.sort(B);
		int sumA = 0, sumB = 0, diff = 0, index = -1;
		for (int i = 0; i < A.length; i++) {
			sumA += A[i];
		}
		for (int i = 0; i < B.length; i++) {
			sumB += B[i];
		}
		diff = sumA - sumB;
		for (int j = 0; j < B.length; j++) {
			if (j > 0 && B[j] == B[j - 1]) {
				continue;
			}
			// 注意此处diff要除2
			index = binarySearch(A, B[j] + diff / 2);
			if (index > -1) {
				ans[0] = A[index];
				ans[1] = B[j];
			}
		}
		return ans;
	}

	// 优秀代码示例1
	// 空间换时间
	public static int[] fairCandySwap1(int[] A, int[] B) {
		int sum_A = 0;
		int sum_B = 0;
		int[] set = new int[100001];
		for (int i = 0; i < A.length; i++) {
			set[A[i]] = 1;
			sum_A += A[i];
		}
		for (int i = 0; i < B.length; i++) {
			sum_B += B[i];
		}
		int temp = (sum_A - sum_B) / 2;
		int[] res = new int[2];
		for (int i = 0; i < B.length; i++) {
			// 由于糖果大小在[1,100000]范围内
			if (B[i] + temp <= 0 || B[i] + temp >= set.length)
				continue;
			if (set[B[i] + temp] != 0) {
				res[0] = B[i] + temp;
				res[1] = B[i];
				break;
			}
		}
		return res;
	}

	// 优秀代码示例2
	// 我的简化版
	public int[] fairCandySwap2(int[] A, int[] B) {
		int sa = 0, sb = 0; // sum of A, B respectively
		for (int x : A)
			sa += x;
		for (int x : B)
			sb += x;
		int delta = (sb - sa) / 2;
		// If Alice gives x, she expects to receive x + delta
		Set<Integer> setB = new HashSet<Integer>();
		// 将B数组转换为合集setB，方便使用setB.contains()查找
		for (int x : B)
			setB.add(x);
		for (int x : A)
			if (setB.contains(x + delta))
				return new int[] { x, x + delta };
		throw null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { 1, 2, 5 };
		int[] B = { 2, 4 };
		int[] ans = fairCandySwap(A, B);
		System.out.println(Arrays.toString(ans));
	}

}
