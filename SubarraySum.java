package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @className subarraySum.java
 * @author SakrA
 * @version 创建时间：2019年4月1日 下午2:34:12
 */
public class SubarraySum {
	// 优秀代码示例
	/**
	 * 需要保存和出现的次数！！！ 扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
	 **/
	// 注意保存的是和sum出现的次数，不是sum-k；评判时使用当前sum-k来匹配。
	public static int subarraySum(int[] nums, int k) {
		int i = 0, len = nums.length, count = 0;
		Map<Integer, Integer> remainMap = new HashMap<>();
		remainMap.put(0, 1);
		int sum = 0;
		for (; i < len; i++) {
			sum += nums[i];
			if (remainMap.containsKey(sum - k)) {
				count += remainMap.get(sum - k);
			}
			// 用哈希表保存第0个元素到当前元素的和
			remainMap.put(sum, remainMap.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 0, 0, 0, 0 };
		int k = 0;
		int count = subarraySum(nums, k);
		System.out.println(count);
	}

}
