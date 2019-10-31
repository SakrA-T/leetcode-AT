package leetCode;

/**
 * @className P53_MaxSubArray.java
 * @author AT
 * @version Create Time：2019年7月13日 下午9:18:15
 * @question leetcode.p53:最大子序和
 * @describe 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class P53_MaxSubArray {
	static int[] range = { -1, -1 };
//	static int[] l_range = { -1, -1 };
//	static int[] r_range = { -1, -1 };
//	static int[] m_range = { -1, -1 };

	// 思路：分治法
	// 没能记录范围，需要更多参数
	public static int divideConquer(int[] nums, int left, int right) {
		if (left == right) {
			return nums[left];
		}
		int mid = 0;
		int left_max = 0, right_max = 0, mid_max = 0;
		int temp_Sum = 0;
		if (left < right) {
			mid = left + (right - left) / 2;
			left_max = divideConquer(nums, left, mid);
			right_max = divideConquer(nums, mid + 1, right);
			mid_max += nums[mid] + nums[mid + 1];
			temp_Sum = mid_max;
			int i = mid - 1, j = mid + 2;
			while (i >= left) {
				temp_Sum += nums[i--];
				if (temp_Sum > mid_max) {
					mid_max = temp_Sum;
				}
			}
			temp_Sum = mid_max;
			while (j <= right) {
				temp_Sum += nums[j++];
				if (temp_Sum > mid_max) {
					mid_max = temp_Sum;
				}
			}
		}
		return Math.max(mid_max, Math.max(left_max, right_max));
	}

	public static int maxSubArray(int[] nums) {
		int max = divideConquer(nums, 0, nums.length - 1);
		return max;
	}

	// 优秀代码示例1
	// emmmm...这么简单的吗
	public static int maxSubArray1(int[] nums) {
		int max = nums[0];
		int sum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (sum < 0) {
				sum = nums[i];
			} else {
				sum = sum + nums[i];
			}
			if (max < sum) {
				max = sum;
			}
		}
		return max;
	}

	// 优秀代码示例2——动态规划
	public static int maxSubArray2(int[] nums) {
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			// 在遍历过程中修改nums，如遍历到下标i的元素，nums[i]储存为[0,i]数组中，包含当前元素的情况下最大子数组和
			// 两种情况，当前元素值+nums[i-1]中保存的最大值（因为nums[i-1]所记录的子数组必包含原nums[i-1]，故必连）
			// 当前元素值，不与前面的元素连接；两种情况取最大值存入nums[i],即可
			nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]);
			res = Math.max(res, nums[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4, 243, -56, -78, -92, 34, 56, -7, 89 };
		int maxSum = maxSubArray(nums);
		System.out.println("[ " + range[0] + " , " + range[1] + " ] = " + maxSum);
	}

}
