package leetCode;

/**
 * @className CopyFileNames.java
 * @author AT
 * @version Create Time：2019/6/25 15:39:20
 */

public class WaterContainer {
	public static int[] insertSort(int[] arr) {
		int[] copyarr = new int[arr.length];
		int[] rst = new int[arr.length];
		int i = 0, j = 0, crt = 0;
		copyarr = arr.clone();
		rst[0] = 0;
		for (i = 1; i < copyarr.length; i++) {
			crt = copyarr[i];

			for (j = i - 1; j >= 0; j--) {
				if (crt > copyarr[j]) {
					copyarr[j + 1] = copyarr[j];
					rst[j + 1] = rst[j];
				} else {
					break;
				}
			}
			copyarr[j + 1] = crt;
			rst[j + 1] = i;
		}
//		for (i = 0; i < rst.length; i++)
//			System.out.print(rst[i] + "\t");
//		System.out.println();
		return rst;
	}

	public static int maxArea(int[] height) {
		int max_area = 0, crt_area = 0;
		int s1 = 0, s2 = 0, crt_s = 0;
		// 根据height数组的数值大小，将数组重新排序(从大到小)并储存到xSort[]，数组中存储对应数组在height数组中的下标
		int xSort[] = insertSort(height);
		// x_max x_min 分别记录当前遍历到的最大和最小x轴值
		int i = 0, x_max = xSort[0], x_min = xSort[0];
		for (i = 1; i < xSort.length; i++) {
			if (x_max - xSort[i] > xSort[i] - x_min) {
				crt_area = height[xSort[i]] * (x_max - xSort[i]);
				crt_s = x_max;
			} else {
				crt_area = height[xSort[i]] * (xSort[i] - x_min);
				crt_s = x_min;
			}
			if (x_max < xSort[i]) {
				x_max = xSort[i];
			}
			if (x_min > xSort[i]) {
				x_min = xSort[i];
			}
			if (crt_area > max_area) {
				max_area = crt_area;
				s1 = crt_s;
				s2 = xSort[i];
			}
		}
		System.out.println("Two sides are：" + (s1 + 1) + "、" + (s2 + 1));
		return max_area;
	}

	// 优秀代码示例
	// 从两端开始计算，每次用较短板长度乘总宽，所得结果与当前记录的最大面积比较；
	// 每次放弃两端中较短板，并左/右移一格，总宽减一但短板可能变长，这样才有可能以更长板作为最短板以计算得到更大的面积
	public static int maxArea2(int[] height) {
		int len = height.length;
		int num = 0;
		int res = 0;
		int left = 0, right = len - 1;
		while (left < right) {
			num = Math.min(height[left], height[right]) * (right - left);
			res = Math.max(num, res);
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int height[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		int height[] = { 7, 3, 5, 1, 2, 6, 5, 7, 9 };
		int area = maxArea(height);
		System.out.println("The max area is " + area);
	}

}
