package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @className P220_ContainsNearbyAlmostDuplicate.java
 * @author AT
 * @version Create Time：2019年7月10日 下午3:06:04
 * @question leetcode.p220:存在重复元素
 * @describe 给定一个整数数组，判断数组中是否有两个不同的索引i和j，使得nums[i]和nums[j]的差的绝对值<=t，并且i和j之间的差的绝对值<=ķ。
 */
public class P220_ContainsNearbyAlmostDuplicate {
	// 思路：对于每一个元素i，遍历其[i+1,i+k]的元素，判断是否满足条件
	// 超时
	public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		int len = nums.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len && j <= i + k; j++) {
				if ((double) nums[i] - (double) nums[j] <= Integer.MIN_VALUE
						|| (double) nums[i] - (double) nums[j] > Integer.MAX_VALUE) {
					return false;
				}
				if (Math.abs(nums[i] - nums[j]) <= t) {
					return true;
				}
			}
		}
		return false;
	}

	// 思路: 维护一个以窗口大小k为最大size的List，有序保存当前遍历到的元素i的前k个元素
	// 二分查找list找到当前nums[i]应该插入的位置下标index，若插入后list.size>k，则判断要删除的元素nums[i-k]与nums[i]的差值是否满足条件，若满足则返回，不满足则删除
	// 计算插入元素的最小差值value1和插入元素的后一个元素（tempList中）的最小差值value2，若其中有<=t则返回
	// 若设记录差值的map（此题没必要），则list中，第一个元素对应value值为-1
	public static int binarySearch(List<Integer> list, int n) {
		int len = list.size();
		int left = 0, right = len - 1, mid = 0;
		while (left < right) {
			mid = left + (right - left) / 2;
			// 这边不存在等于的情况
			if (n > list.get(mid)) {
				left = mid + 1;
			} else if (n < list.get(mid)) {
				right = mid - 1;
			}
		}
		// 以left==right退出循环，插入点可能在left/right的任意一边，需判断
		// 或以right=left-1退出循环，插入点必在right和left之间

		if (right == -1 || n > list.get(right)) {
			return right + 1;
		} else
			return right;
	}

	public static boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
		if (nums.length <= 1 || t < 0 || k == 0) {
			return false;
		}
		// key为nums元素值，value为距离该元素k范围内的元素的最小差值
//		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> tempList = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			// 存在差值为0的两个元素，则满足任意非负整数t
			if (tempList.contains(nums[i])) {
				return true;
			}
			// 寻找新元素应该插入的位置
			int index = binarySearch(tempList, nums[i]);
			System.out.println("index = " + index);
			tempList.add(index, nums[i]);
			// value1是记录本身插入元素的最小差值，value2是计算插入元素的后一个元素（tempList中）的最小差值
			double value1 = -1, value2 = -1;
			// 若是插入后窗口已经大于k，则删除加入最久的元素

			if (tempList.size() > k) {
				// 对要删除的数与新加入的数进行判断，否则删除后无法判断到
				if (Math.abs((double) nums[i] - (double) nums[i - k]) <= t) {
					return true;
				}
				System.out.println("remove index = " + tempList.indexOf(nums[i - k]));
//				if (tempList.indexOf(nums[i - k]) == 0) {
//					int v = map.replace(tempList.get(1), -1);
//					System.out.println("back value = " + v);
//				}
				tempList.remove(tempList.indexOf(nums[i - k]));
//				map.remove(nums[i - k]);
//				map.replace(key, value)
			}
			// 取删除旧元素后，当前插入元素的新下标
			int newIndex = tempList.indexOf(nums[i]);
			System.out.println("new index = " + newIndex);
			if (newIndex != 0) {
				value1 = (double) nums[i] - (double) tempList.get(newIndex - 1);
			}
//			map.put(nums[i], (int) value1);

			if (newIndex + 1 < tempList.size()) {
				value2 = (double) tempList.get(newIndex + 1) - (double) nums[i];
//				map.replace(tempList.get(newIndex + 1), (int) value2);
			}
			if ((value1 != -1 && value1 <= t) || (value2 != -1 && value2 <= t)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2 };
		int k = 1, t = 1;
		System.out.println(containsNearbyAlmostDuplicate1(nums, k, t) ? true : false);
	}

}
