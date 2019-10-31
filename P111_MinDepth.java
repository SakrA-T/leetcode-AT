package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @fileName: P111_MinDepth.java
 * @author: AT
 * @version: 2019年7月23日 上午11:37:00
 * @question: leetcode.p111:二叉树的最小深度
 * @describe: 给定一个二叉树，找出其最小深度。
 */

public class P111_MinDepth {
	// 层次遍历
	public static int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 1;
		while (!queue.isEmpty()) {
			// 这边要先保存下来，用于后续循环，否则的话随着节点的加入queue.size()会发生变化，就只有一层了
			int level_size = queue.size();
			for (int i = 0; i < level_size; i++) {
				// 开始遍历第x层时，level的值已为x
				TreeNode crtNode = queue.remove();
				if (crtNode.left == null && crtNode.right == null) {
					// 若当前节点为叶节点，则返回当前节点的层数
					return level;
				}
				if (crtNode.left != null) {
					queue.offer(crtNode.left);
				}
				if (crtNode.right != null) {
					queue.offer(crtNode.right);
				}
			}
			level++;
		}
		return level;
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

		int min = minDepth(root);
		System.out.print(min);
	}

}
