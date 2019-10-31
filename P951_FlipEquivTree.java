package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @className P951_FlipEquivTree.java
 * @author AT
 * @version Create Time：2019年7月31日 下午1:12:07
 * @question: leetcode.p951:翻转等价二叉树
 * @describe: 定义二叉树T的翻转操作：选择任意节点，然后交换它的左子树和右子树。只要经过*一定次数*的翻转操作后，能使X等于Y，就称二叉树X翻转等价于二叉树Y。
 * @describe: 编写一个判断两个二叉树是否是翻转等价的函数。
 */

public class P951_FlipEquivTree {
	// 思路1：对于翻转等价的两棵树，其所有路径都是对应相等的，求出两棵树的路径，并比较即可。
	// 思路2：递归：同时遍历两棵树，在第一棵树以先序遍历，所遍历到的节点值，在另一棵树上必须相等，但其子树的值，可对应另一棵树上任意一边

	public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		} else if (root1 == null || root2 == null || root1.val != root2.val) {
			return false;
		}
		boolean left_search = true, right_search = true;
		left_search = flipEquiv(root1.left, root2.left);
		right_search = left_search && flipEquiv(root1.right, root2.right);
		if (!right_search) {
			left_search = flipEquiv(root1.left, root2.right);
			right_search = left_search && flipEquiv(root1.right, root2.left);
		}
		return right_search;
	}

	// 优秀代码示例1
	// 标准态遍历：自定义标准化形态。本题中给出标准化定义：所有左孩子都<=右孩子。再实现NLR中序遍历，这样所有翻转等价二叉树在转换成标准态后都是完全一样的。
	public static boolean flipEquiv1(TreeNode root1, TreeNode root2) {
		List<Integer> vals1 = new ArrayList<>();
		List<Integer> vals2 = new ArrayList<>();
		dfs(root1, vals1);
		dfs(root2, vals2);
		return vals1.equals(vals2);
	}

	public static void dfs(TreeNode node, List<Integer> vals) {
		if (node != null) {
			vals.add(node.val);
			int L = node.left != null ? node.left.val : -1;
			int R = node.right != null ? node.right.val : -1;
			// 规定遍历顺序，先遍历节点值较小的一边，（相同则从左边开始），这样的话理论上最终形成的遍历结果列表，如果是翻转相等的话，会有值相等的列表
			if (L < R) {
				dfs(node.left, vals);
				dfs(node.right, vals);
			} else {
				dfs(node.right, vals);
				dfs(node.left, vals);
			}

			vals.add(null);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一棵树：第一层
		TreeNode p = new TreeNode(1);

		// 第二层
		TreeNode p1 = new TreeNode(2);
		TreeNode p2 = new TreeNode(3);
		p.left = p1;
		p.right = p2;

		// 第三层
		TreeNode p11 = new TreeNode(4);
		p1.left = p11;
		TreeNode p12 = new TreeNode(5);
		p1.right = p12;
		TreeNode p21 = new TreeNode(6);
		p2.left = p21;
		// 第四层
		TreeNode p121 = new TreeNode(7);
		p12.left = p121;
		TreeNode p122 = new TreeNode(8);
		p12.right = p122;

		// 第二棵树：第一层
		TreeNode q = new TreeNode(1);

		// 第二层
		TreeNode q1 = new TreeNode(3);
		TreeNode q2 = new TreeNode(2);
		q.left = q1;
		q.right = q2;

		// 第三层
		TreeNode q12 = new TreeNode(6);
		q1.right = q12;
		TreeNode q21 = new TreeNode(4);
		q2.left = q21;
		TreeNode q22 = new TreeNode(5);
		q2.right = q22;
		// 第四层
		TreeNode q221 = new TreeNode(8);
		q22.left = q221;
		TreeNode q222 = new TreeNode(7);
		q22.right = q222;

		System.out.println(flipEquiv(p, q));
	}

}
