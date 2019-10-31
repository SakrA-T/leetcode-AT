package leetCode;

/**
 * @fileName: P124_MaxPathSum.java
 * @author: AT
 * @version: 2019年7月24日 下午1:22:50
 * @question: leetcode.p124:二叉树中的最大路径和
 * @describe: 给定一个非空二叉树，返回其 “路径” 和的最大值。
 * @describe: 本题路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 */

public class P124_MaxPathSum {
	// 思路：由P543求二叉树直径进行稍微修改，DFS递归遍历，计算更新最大路径和
	private static int sum = Integer.MIN_VALUE;

	private static int maxSubTree(TreeNode r) {
		if (r == null) {
			return 0;
		}
		int max_left = 0, max_right = 0;
		max_left = maxSubTree(r.left);
		max_right = maxSubTree(r.right);
		int r_maxsum = r.val;
		r_maxsum += (max_left > 0 ? max_left : 0);
		r_maxsum += (max_right > 0 ? max_right : 0);
		sum = r_maxsum > sum ? r_maxsum : sum;
		int max_sub = Math.max(max_left, max_right);
		// 返回当前以r为根的树的“单边路径” （从任意左/右子树节点到r的左/右孩子）和的最大值，以供后续使用
		return max_sub > 0 ? (max_sub + r.val) : r.val;
	}

	public static int maxPathSum(TreeNode root) {
		maxSubTree(root);
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(2);

		// 第二层
		TreeNode p1 = new TreeNode(2);
		TreeNode p2 = new TreeNode(-1);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(5);
		p1.left = q11;
		TreeNode q12 = new TreeNode(-1);
		p1.right = q12;
//		TreeNode q22 = new TreeNode(1);
//		p2.right = q22;
//		TreeNode q21 = new TreeNode(4);
//		p2.left = q21;

		System.out.print(maxPathSum(root));
	}

}
