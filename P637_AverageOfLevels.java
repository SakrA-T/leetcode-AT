package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @fileName: P637_AverageOfLevels.java
 * @author: AT
 * @version: 2019年7月23日 下午9:05:36
 * @question: leetcode.p637:二叉树的层平均值
 * @describe: 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 */

public class P637_AverageOfLevels {
	// 层次遍历
	public static List<Double> averageOfLevels(TreeNode root) {
		List<Double> averageList = new ArrayList<Double>();

		if (root == null) {
			return averageList;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		double sum = 0;
		while (!queue.isEmpty()) {
			// 这边要先保存下来，用于后续循环，否则的话随着节点的加入queue.size()会发生变化，就只有一层了
			int level_size = queue.size();
			sum = 0;
			for (int i = 0; i < level_size; i++) {
				TreeNode crtNode = queue.remove();
				// 累加当前遍历到的层的数值
				sum += crtNode.val;
				if (crtNode.left != null) {
					queue.offer(crtNode.left);
				}
				if (crtNode.right != null) {
					queue.offer(crtNode.right);
				}
			}
			averageList.add(sum / level_size);
		}
		return averageList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(1);

		// 第二层
		TreeNode p1 = new TreeNode(2);
		TreeNode p2 = new TreeNode(2);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(4);
		p1.left = q11;
//		TreeNode q22 = new TreeNode(4);
//		p2.right = q22;
		TreeNode q12 = new TreeNode(3);
		p1.right = q12;
//		TreeNode q21 = new TreeNode(3);
//		p2.left = q21;

		List<Double> avgList = averageOfLevels(root);
		Double[] avgArray = new Double[avgList.size()];
		avgList.toArray(avgArray);
		System.out.print(Arrays.toString(avgArray));
	}

}
