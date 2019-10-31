package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @className P108_SortedArrayToBST.java
 * @author AT
 * @version Create Time：2019年10月25日 下午3:01:30
 * @question: leetcode.p108：将有序数组转换为二叉搜索树
 * @describe: 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。（子树的高度差的绝对值不超过 1）
 */

public class P108_SortedArrayToBST {
	// 思路：由于数组是升序排列，每次找中位数即可
	public static TreeNode sortedArrayToBST(int[] nums) {
		TreeNode root = recurve(nums, 0, nums.length - 1);

		return root;
	}

	public static TreeNode recurve(int[] nums, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = left + (right - left) / 2;

		TreeNode r = new TreeNode(nums[mid]);
		r.left = recurve(nums, left, mid - 1);
		r.right = recurve(nums, mid + 1, right);
		return r;
	}

	public static String[] levelTraversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<String> treeList = new ArrayList<String>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode crt = queue.poll();
				if (crt != null) {
					treeList.add(crt.val + "");
					if (crt.left != null) {
						queue.offer(crt.left);
					} else {
						if (crt.right != null)
							queue.offer(null);
					}
					if (crt.right != null) {
						queue.offer(crt.right);
					} else {
						if (crt.left != null)
							queue.offer(null);
					}
				} else {
					treeList.add("null");
				}

			}
		}
		return (String[]) treeList.toArray(new String[treeList.size()]);

	}

	public static void main(String[] args) {
		int[] nums = { -10, -3, 0, 5, 9 };
		TreeNode rst = sortedArrayToBST(nums);
		String[] rstArr = levelTraversal(rst);
		System.out.println(Arrays.toString(rstArr));
	}

}
