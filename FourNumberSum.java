package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @className FourNumberSum.java
 * @author AT
 * @version Create Time：2019年7月1日 下午3:38:19
 */

public class FourNumberSum {

	// 思路： 使用双循环固定两个数，用双指针找另外两个数，通过比较与target
	// 的大小，移动指针。时间复杂度不超过O(n^3),若直接使用四重循环，时间复杂度为O(n^4)
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> rstList = new ArrayList<>();
		int i = 0, j = 0, m = 0, n = 0;
		int len = nums.length, sum = 0;

		if (len < 4) {
			// rstList.add(Arrays.asList(0));
			return rstList;
		}
		Arrays.sort(nums);
		System.out.println("len = " + len);
		for (i = 0; i <= len - 4; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			for (j = i + 1; j <= len - 3; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				m = j + 1;
				n = len - 1;

				sum = nums[i] + nums[j] + nums[m] + nums[n];
				while (m < n) {
					if (sum != target) {

						if (sum < target) {
							++m;
						} else {
							--n;
						}
					} else {
						rstList.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
						while (m < n && nums[m] == nums[++m])
							continue;
						while (m < n && nums[n] == nums[--n])
							continue;
					}
					sum = nums[i] + nums[j] + nums[m] + nums[n];
				}
			}
		}
		return rstList;
	}

	// 优秀代码示例
	public List<List<Integer>> fourSum2(int[] nums, int target) {

		if (nums.length < 4) {
			return new ArrayList<>();
		}
		List<List<Integer>> rs = new ArrayList<>();
		// 从小到大排序
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 3;) {
			for (int j = i + 1; j < nums.length - 2;) {
				if (nums[i] + nums[j] > target / 2) {// 剪枝优化
					break;
				}
				int left = j + 1;
				int right = nums.length - 1;
				while (left < right) {
					if (left >= right || nums[left] + nums[right] < target / 2) {
						break;
					}
					int result = nums[i] + nums[j] + nums[left] + nums[right];
					if (result == target) {
						rs.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
					}
					if (result > target) {
						// 右侧重复值
						while (left < right && nums[right--] == nums[right])
							continue;
					} else {
						// 左侧重复值
						while (left < right && nums[left++] == nums[left])
							continue;
					}
				}
				while (j < nums.length - 1 && nums[j] == nums[++j])
					continue;
			}
			while (i < nums.length - 1 && nums[i] == nums[++i])
				continue;
		}
		return rs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = { -1, 0, -5, -2, -2, -4, 0, 1, -2 };
		int target = -9;
		List<List<Integer>> rstList = fourSum(nums, target);
		System.out.println("[");
		for (List<Integer> list : rstList) {
			ListIterator<Integer> iter = list.listIterator();
			System.out.print("[");
			while (iter.hasNext()) {
				System.out.print(iter.next() + " ");
			}
			System.out.println("] , ");
		}
		System.out.println("]");
	}

}
