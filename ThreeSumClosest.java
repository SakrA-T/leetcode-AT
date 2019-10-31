package leetCode;

import java.util.Arrays;

/**
 * @className ThreeSumClosest.java
 * @author SakrA
 * @version 创建时间：2019年3月19日 下午9:34:16
 */
public class ThreeSumClosest {

//	public int threeSumClosest(int[] nums, int target) {
//        Arrays.sort(nums);
//        int closestNum = nums[0] + nums[1] + nums[2];
//        for (int i = 0; i < nums.length - 2; i++) {
//            int l = i + 1;
//            int r = nums.length - 1;
//            while (l < r){
//                int threeSum = nums[l] + nums[r] + nums[i];
//                if (Math.abs(threeSum - target) < Math.abs(closestNum - target)) {
//                    closestNum = threeSum;
//                }
//                if (threeSum > target) {
//                    r--;
//                } else if (threeSum < target) {
//                    l++;
//                } else {
//                    return target;
//                }
//
//            }
//
//        }
//
//        return closestNum;
//    }
	public static int threeSumClosest(int[] nums, int target) {
		int closestSum = Integer.MAX_VALUE;
		int tempSum = Integer.MAX_VALUE;
//		int[] temp1 = { sum, sum }, temp2 = { sum, sum };
//		int[] temp1 = { sum, sum }, temp2 = temp1, temp3 = temp1; // 引用赋值，会导致temp2和temp3都指向temp1，同时修改
		Arrays.sort(nums);
		int left = 0, right = 0, len = nums.length;
		for (int i = 0; i < len - 2; i++) {
			if (i >= 1 && nums[i] == nums[i - 1] && i < len - 2)
				continue;
			left = i + 1;
			right = len - 1;

			while (left < right) {
				tempSum = nums[i] + nums[left] + nums[right];
				if (Math.abs(tempSum - target) < Math.abs(closestSum - target)) {
					closestSum = tempSum;
				}
				if (tempSum < target) {
					while (nums[left] == nums[left + 1] && left + 1 < right) {
						left++;
					}
					left++;
				} else if (tempSum > target) {
					while (nums[right] == nums[right - 1] && left < right - 1) {
						right--;
					}
					right--;
				} else {
					return target;
				}
			}
		}
		return closestSum;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;

		int[] nums1 = { 1, 2, 4, 8, 16, 32, 64, 128 };
		sum = threeSumClosest(nums1, 82);
		System.out.println(Arrays.toString(nums1));
		System.out.println(sum);

		int[] nums2 = { 1, -3, 3, 5, 4, 1 };
		sum = threeSumClosest(nums2, 1);
		System.out.println(Arrays.toString(nums2));
		System.out.println(sum);

		int[] nums3 = { -1, 2, 1, -4 };
		sum = threeSumClosest(nums3, 1);
		System.out.println(Arrays.toString(nums3));
		System.out.println(sum);
	}

}
