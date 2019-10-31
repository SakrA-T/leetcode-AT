package leetCode;

/**
 * @fileName: P543_DiameterOfBinaryTree.java
 * @author: AT
 * @version: 2019年7月23日 下午9:49:00
 * @question: leetcode.p543:二叉树的直径
 * @describe: 给定一棵二叉树，计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过 根 结点。
 */

public class P543_DiameterOfBinaryTree {
	// 思路1：所求直径，即根结点左子树的最大深度+右子树的最大深度
	// 深度初始化为0，表示根结点无左/右子树，则对应边深度为0
	// ERROR:最大直径不一定由根的左右子树中各一个结点形成，可以单在一边子树上。
	// 一个结点的最大直径 = 它左树的高度 + 它右树的高度
	// DFS, 在其过程中记录左右子树深度和（所有结点的直径），以最大深度和作为直径
	static int diam = 0;

	public static int maxDepth(TreeNode r) {
		if (r == null) {
			return 0;
		}
		int max_left = 0, max_right = 0;
		max_left = maxDepth(r.left);
		max_right = maxDepth(r.right);
		if (max_left + max_right > diam) {
			diam = max_left + max_right;
		}
		// 返回当前以r为根的树的最大高度，以供后续使用
		return Math.max(max_left, max_right) + 1;
	}

	public static int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		maxDepth(root);
		return diam;
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

		System.out.print(diameterOfBinaryTree(root));
	}

}
