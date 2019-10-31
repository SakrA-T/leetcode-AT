package leetCode;

/**
 * @fileName: P101_IsSymmetric.java
 * @author: AT
 * @version: 2019年7月22日 下午9:18:03
 * @question: leetcode.p101:对称二叉树
 * @describe: 给定一个二叉树，检查它是否是镜像对称的。
 */

public class P101_IsSymmetric {
	// 思路：递归 or 迭代，都很简单
	public static boolean doubleRecurve(TreeNode r1, TreeNode r2) {
		if ((r1 == null && r2 == null)) {
			return true;
		} else if (r1 == null || r2 == null) {
			return false;
		} else if (r1.val != r2.val) {
			return false;
		}
		boolean b1 = doubleRecurve(r1.left, r2.right);
		boolean b2 = doubleRecurve(r1.right, r2.left);
		return b1 && b2;

	}

	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return doubleRecurve(root.left, root.right);
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
		System.out.println(isSymmetric(root));
	}

}
