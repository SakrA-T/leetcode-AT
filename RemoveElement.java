package leetCode;

/**
 * @className RemoveElement.java
 * @author AT
 * @version Create Time：2019年7月4日 下午5:54:55
 */
public class RemoveElement {
	public static int removeElement(int[] nums, int val) {
		int rstLen = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[rstLen++] = nums[i];
				// rstLen++ 之后，rstLen为当前移除元素后的长度
			}
		}
		return rstLen;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 3, 2, 2, 1, 4, 5, 2, 4 };
		int val = 2;
		int len = removeElement(nums, val);
		int i = 0;
		for (i = 0; i < len; i++) {
			if (i == len - 1) {
				System.out.println(nums[i]);
			} else
				System.out.print(nums[i] + ",");
		}
	}

}
