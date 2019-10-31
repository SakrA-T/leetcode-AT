package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className AddThreeNumber.java
 * @author SakrA
 * @version 创建时间：2019年3月19日 上午9:47:21
 */
public class AddThreeNumbers {
	public static List<List<Integer>> threeSum(int[] nums) {
		int len = nums.length;
		int i = 0, left = 0, right = 0;
		List<List<Integer>> list = new ArrayList<>();

		Arrays.sort(nums);

		for (i = 0; i < len - 2; i++) {
			if (nums[i] > 0)
				break;
			if (i >= 1 && nums[i] == nums[i - 1] && i < len - 2)
				continue;
			left = i + 1;
			right = len - 1;
			while (left < right) {
				if (nums[left] + nums[right] == -nums[i]) {
					list.add(Arrays.asList(nums[i], nums[left], nums[right]));
					left++;
					right--;
					while (left < right && nums[left] == nums[left - 1]) {
						left++;
					}
					while (left < right && nums[right] == nums[right + 1]) {
						right--;
					}

				} else if (nums[left] + nums[right] < -nums[i]) {
					left++;
					while (left < right && nums[left] == nums[left - 1]) {
						left++;
					}
				} else {
					right--;
					while (left < right && nums[right] == nums[right + 1]) {
						right--;
					}
				}

			}
		}

		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = { -4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6 };
		List<List<Integer>> list = threeSum(nums1);
		System.out.println(Arrays.toString(nums1));
		for (List<Integer> item : list) {
			System.out.println(Arrays.toString(item.toArray()));
		}
		int[] nums2 = { -2, 0, 1, 1, 2 };
		list = threeSum(nums2);
		System.out.println(Arrays.toString(nums2));
		for (List<Integer> item : list) {
			System.out.println(Arrays.toString(item.toArray()));
		}
	}

}
