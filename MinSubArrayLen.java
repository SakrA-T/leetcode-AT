package leetCode;

/**
 * @className MinSubArrayLen.java
 * @author AT
 * @version Create Time：2019年7月2日 上午10:48:43
 * @question 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s
 *           的长度最小的连续子数组长度。如果不存在符合条件的连续子数组，返回 0。
 * @idea 到现在为止，我们都保持子数组的左端点不动去找右端点。 其实一旦知道这个位置开始的子数组不会是最优答案了，我们就可以移动左端点。
 *       我们可以移动左端点，因为以它为开头的满足sum≥s条件的最短子数组已经求出来了
 */
public class MinSubArrayLen {
//	int[] range = { -1, -1 };

	public static int subLen(int s, int left, int rigth, int[] nums) {
		int minLen = 0;
		int sum = 0;
		if (rigth == left) {
			sum = nums[left];
			if (sum < s) {
				return nums.length + 1;
			} else {
				return 1;
			}
		}
		int mid_len = nums.length + 1;
		while (left < rigth) {
			int mid = (rigth - left) / 2;
			int left_len = subLen(s, left, mid, nums);
			int right_len = subLen(s, mid + 1, rigth, nums);
			if (minLen > 2) {
				mid_len = midSubLen(s, mid, nums, minLen);
			}
			minLen = Math.min(mid_len, Math.min(left_len, right_len));
		}
		return minLen;
	}

	public static int midSubLen(int s, int mid, int[] nums, int minLen) {
		int len = 2;
		int sum = nums[mid] + nums[mid + 1];
		int i = mid - 1, j = mid + 2;
		while (sum < s && len < minLen && (i > 0 || j < nums.length - 1)) {
			if (i > 0 && j < nums.length - 1) {
				sum += (nums[i] > nums[j] ? nums[i--] : nums[j++]);
			} else if (j < nums.length - 1) {
				sum += nums[j++];
			} else if (i > 0) {
				sum += nums[i--];
			}
			len++;
		}
		if (sum < s) {
			return nums.length + 1;
		}
		System.out.println("mid_len = " + len);
		return len;
	}

	public static int minSubArrayLen(int s, int[] nums) {
		int minLen = 0;
		minLen = subLen(s, 0, nums.length - 1, nums);
		return minLen;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 2, 3, 1, 2, 4, 3 };
		int s = 7;
		int minLen = minSubArrayLen(s, nums);
		System.out.println(minLen);
	}

}
