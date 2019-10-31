package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @className P257_BinaryTreePaths.java
 * @author AT
 * @version Create Time：2019年7月30日 下午6:36:09
 * @question: leetcode.p257：二叉树的所有路径
 * @describe: 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 */

public class P257_BinaryTreePaths {
	private static void dfs(TreeNode r, List<String> rstList, String curStr) {
		if (r != null) {
			if (r.left == null && r.right == null) {
				rstList.add(curStr + r.val);
				return;
			}
			String nodeStr = r.val + "->";
			curStr += nodeStr;
			// 这边curStr 可修改，因为传的是值？在被调函数内对curStr的修改，都不会改变这边curStr的值
			dfs(r.left, rstList, curStr);
			dfs(r.right, rstList, curStr);
		}

	}

	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> rstList = new ArrayList<String>();
		dfs(root, rstList, "");
		return rstList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(1);

		// 第二层
		TreeNode p1 = new TreeNode(2);
		TreeNode p2 = new TreeNode(3);
		root.left = p1;
		root.right = p2;

		// 第三层
//		TreeNode q11 = new TreeNode(4);
//		p1.left = q11;
		TreeNode q12 = new TreeNode(5);
		p1.right = q12;
		TreeNode q21 = new TreeNode(7);
		p2.left = q21;
		TreeNode q22 = new TreeNode(9);
		p2.right = q22;

		List<String> rstList = binaryTreePaths(root);
		System.out.println("[ ");
		for (String path : rstList) {
			System.out.println("  \"" + path + "\"");
		}
		System.out.println("]");
	}

}
