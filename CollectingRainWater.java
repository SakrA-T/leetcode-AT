package leetCode;

/**
 * @className CollectingRainWater.java
 * @author AT
 * @version Create Time：2019年6月25日 下午5:10:00
 */
public class CollectingRainWater {

	public static int trap(int[] height) {
		if (height.length == 0) {
			return 0;
		}
		int area = 0, i = 0, j = 0;
		int h_max = height[0], index_max = 0;
		// 求出所有位置中，最高位置所在下标（若存在同高则取下标较大者，此处无所谓）
		for (i = 1; i < height.length; i++) {
			if (height[i] > h_max) {
				h_max = height[i];
				index_max = i;
			}
		}
		int temp_sum = 0, temp_max = height[0];
		// 从左遍历至最高位置index_max
		// 当当前记录的最高位置（已遍历过）比当前位置大时，计算可蓄水面积（已记录最高-当前），加入总蓄水面积。
		for (i = 1; i <= index_max; i++) {
			if (height[i] >= temp_max) {
				area += temp_sum;
				temp_sum = 0;
				temp_max = height[i];
			} else {
				temp_sum += (temp_max - height[i]);
			}
		}
		// 从右遍历至最高位置index_max
		temp_sum = 0;
		temp_max = height[height.length - 1];
		for (j = height.length - 2; j >= index_max; j--) {
			if (height[j] >= temp_max) {
				area += temp_sum;
				temp_sum = 0;
				temp_max = height[j];
			} else {
				temp_sum += (temp_max - height[j]);
			}
		}
		return area;
	}

	// 稍微优质代码示例
	public int trap2(int[] height) {
		int sum = 0;
		int[] max_left = new int[height.length];
		int[] max_right = new int[height.length];

		for (int i = 1; i < height.length - 1; i++) {
			max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
		}
		for (int i = height.length - 2; i >= 0; i--) {
			max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
		}
		// 若当前位置，左右的最高挡板都比当前位置高，则可蓄水，蓄水量是左右中较低挡板减去当前位置高度。
		for (int i = 1; i < height.length - 1; i++) {
			int min = Math.min(max_left[i], max_right[i]);
			if (min > height[i]) {
				sum = sum + (min - height[i]);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int height[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		int area = trap(height);
		System.out.println("The area of rain container is : " + area);

	}

}
