package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @fileName: P104_MaxDepth.java
 * @author: AT
 * @version: 2019年7月23日 下午12:12:36
 * @question: leetcode.p104:二叉树的最大深度
 * @describe:
 */

public class P104_MaxDepth {

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 0;
		while (!queue.isEmpty()) {
			// 这边要先保存下来，用于后续循环，否则的话随着节点的加入queue.size()会发生变化，就只有一层了
			int level_size = queue.size();
			for (int i = 0; i < level_size; i++) {
				TreeNode crtNode = queue.remove();
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

	// 优秀代码示例1——递归
	public static int maxDepth1(TreeNode root) {
		return root != null ? Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1 : 0;
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
//		TreeNode q12 = new TreeNode(3);
//		p1.right = q12;
		TreeNode q21 = new TreeNode(3);
		p2.left = q21;

		int min = maxDepth(root);
		System.out.print(min);
	}

}
