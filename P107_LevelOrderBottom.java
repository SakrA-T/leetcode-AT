package leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @fileName: P107_LevelOrderBottom.java
 * @author: AT
 * @version: 2019年7月23日 下午9:22:51
 * @question: leetcode.p107:二叉树的层次遍历 II
 * @describe: 给定一个二叉树，返回其节点值自底向上的层次遍历。
 */

public class P107_LevelOrderBottom {
	// 把 P102 层次遍历的结果reverse一下再返回就好啦；
	// 或者用栈储存每一层的List<Integer>，后逐个pop
	// 层次遍历必是从上往下遍历，单考虑从下往上遍历保存的话，是没法实现的
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> rstList = new ArrayList<>();
		if (root == null) {
			return rstList;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 0;
		while (!queue.isEmpty()) {
			rstList.add(new ArrayList<>());
			// 这边要先保存下来，用于后续循环，否则的话随着节点的加入queue.size()会发生变化，就只有一层了
			int level_size = queue.size();
			for (int i = 0; i < level_size; i++) {
				TreeNode crtNode = queue.remove();
				rstList.get(level).add(crtNode.val);
				if (crtNode.left != null) {
					queue.offer(crtNode.left);
				}
				if (crtNode.right != null) {
					queue.offer(crtNode.right);
				}
			}
			level++;
		}
		Collections.reverse(rstList);
		return rstList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(3);

		// 第二层
		TreeNode p1 = new TreeNode(9);
		TreeNode p2 = new TreeNode(20);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(5);
		p1.left = q11;
		TreeNode q22 = new TreeNode(7);
		p2.right = q22;
		TreeNode q12 = new TreeNode(15);
		p1.right = q12;
//		TreeNode q21 = new TreeNode(7);
//		p2.left = q21;

		List<List<Integer>> rstList = levelOrderBottom(root);
		for (List<Integer> list : rstList) {
			System.out.print("[ ");
			for (Integer li : list) {
				System.out.print(li + " ");
			}
			System.out.println("]");
		}
	}

}
