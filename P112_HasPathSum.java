package leetCode;

/**
 * @fileName: P112_HasPathSum.java
 * @author: AT
 * @version: 2019年7月24日 下午5:06:57
 * @question: leetcode.p112:路径总和
 * @describe: 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 */

public class P112_HasPathSum {
	private static boolean dfs(TreeNode r, int s, int sum) {
		s += r.val;
		if (r.left == null && r.right == null) {
			if (s == sum) {
				return true;
			} else {
				return false;
			}
		}
		boolean hasLeft = false;
		boolean hasRight = false;
		if (r.left != null) {
			hasLeft = dfs(r.left, s, sum);
		}
		if (r.right != null) {
			hasRight = dfs(r.right, s, sum);
		}
		return hasLeft || hasRight;
	}

	public static boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		return dfs(root, 0, sum);
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
		TreeNode q12 = new TreeNode(7);
		p1.right = q12;
		TreeNode q22 = new TreeNode(15);
		p2.right = q22;
//		TreeNode q21 = new TreeNode(7);
//		p2.left = q21;

		int sum = 19;
		System.out.print(hasPathSum(root, sum));
	}

}
