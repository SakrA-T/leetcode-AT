package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @fileName: P102_LevelOrder.java
 * @author: AT
 * @version: 2019年7月22日 下午9:37:16
 * @question: leetcode.p102:二叉树的层次遍历
 * @describe: 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 */

public class P102_LevelOrder {
	// 递归（实际上等价于先序DFS，并非层次遍历）
	public static List<List<Integer>> levelRecurve(TreeNode r, int level, List<List<Integer>> list) {
		if (list.size() == level) {
			list.add(new ArrayList<>());
		}
		list.get(level).add(r.val);
		if (r.left != null) {
			list = levelRecurve(r.left, level + 1, list);
		}
		if (r.right != null) {
			list = levelRecurve(r.right, level + 1, list);
		}
		return list;
	}

	public static List<List<Integer>> levelOrder1(TreeNode root) {
		// 可以将rstList定义在最外层，这样就避免每次递归都要传递
		List<List<Integer>> rstList = new ArrayList<>();
		if (root == null) {
			return rstList;
		}
		return levelRecurve(root, 0, rstList);
	}

	// 迭代+队列
	public static List<List<Integer>> levelOrder(TreeNode root) {
		// 可以将rstList定义在最外层，这样就避免每次递归都要传递
		List<List<Integer>> rstList = new ArrayList<>();
		if (root == null) {
			return rstList;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 0;
//		int count = 1, pre_count = 0;
		while (!queue.isEmpty()) {
			rstList.add(new ArrayList<>());
//			pre_count = count;
//			count = 0;
//			while (pre_count != 0) {
			// 这边要先保存下来，用于后续循环，否则的话随着节点的加入queue.size()会发生变化，就只有一层了
			int level_size = queue.size();
			for (int i = 0; i < level_size; i++) {
				TreeNode crtNode = queue.remove();
//				pre_count--;
				rstList.get(level).add(crtNode.val);
				if (crtNode.left != null) {
					queue.offer(crtNode.left);
//					count++;
				}
				if (crtNode.right != null) {
					queue.offer(crtNode.right);
//					count++;
				}
			}
			level++;
		}
		return rstList;
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
		TreeNode q22 = new TreeNode(4);
		p2.right = q22;
		TreeNode q12 = new TreeNode(3);
		p1.right = q12;
		TreeNode q21 = new TreeNode(3);
		p2.left = q21;

		List<List<Integer>> rstList = levelOrder(root);
		for (List<Integer> list : rstList) {
			System.out.print("[ ");
			for (Integer li : list) {
				System.out.print(li + " ");
			}
			System.out.println("]");
		}
	}

}
