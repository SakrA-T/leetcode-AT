package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P559_NTreeMaxDepth.java
 * @author: AT
 * @version: 2019年7月23日 下午8:44:05
 * @question: leetcode.p559:N叉树的最大深度
 * @describe: 给定一个 N 叉树，找到其最大深度。
 */

public class P559_NTreeMaxDepth {
	// 递归，深度优先搜索，至整棵树遍历结束，返回最大高度
	public static int maxDepth(Node root) {
		if (root == null) {
			return 0;
		}
		List<Node> children = root.children;
		int maxLevel = 1;
		if (children != null) {
			for (Node c : children) {
				maxLevel = Math.max(maxLevel, maxDepth(c) + 1);
			}
		}
		return maxLevel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Node> children_3 = new ArrayList<Node>();
		children_3.add(new Node(8, null));

		List<Node> children_2 = new ArrayList<Node>();
		children_2.add(new Node(5, null));
		children_2.add(new Node(6, children_3));

		List<Node> children_1 = new ArrayList<Node>();
		children_1.add(new Node(3, children_2));
		children_1.add(new Node(2, null));
		children_1.add(new Node(4, null));
		Node root = new Node(1, children_1);

		System.out.print(maxDepth(root));
	}

}
