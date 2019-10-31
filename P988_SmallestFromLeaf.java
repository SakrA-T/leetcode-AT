package leetCode;

/**
 * @fileName: P988_SmallestFromLeaf.java
 * @author: AT
 * @version: 2019年7月24日 上午9:16:32
 * @question: leetcode.p988:从叶结点开始的最小字符串
 * @describe: 给定根结点为 root 的二叉树，树中的每个结点的值[0,25]，分别代表字母'a'到'z'：值0代表'a'，25代表'z'.
 * @describe: 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 */

public class P988_SmallestFromLeaf {
	// 思路：DFS遍历所有的路径，获得从叶结点到根的字符串，字符串插入列表时进行简单判断
	// 实际上获得某个从叶结点到根的字符串时，是否保留，仅与列表最前面的字符串有关，故只需要每次保存最小字符串即可，不需要列表全保存
	// a的ASCII码为97
//	private static List<String> strList = new ArrayList<>();
	private static String smallestStr = "";

	private static void dfs(TreeNode r, String s) {
		s = (char) ('a' + r.val) + s;
		if (r.left == null && r.right == null) {
			// 若所生成字符串比当前最小字符串小，则更新保留
			if (smallestStr == "" || s.compareTo(smallestStr) < 0) {
				smallestStr = s;
			}
		} else {
			if (r.left != null) {
				dfs(r.left, s);
			}
			if (r.right != null) {
				dfs(r.right, s);
			}
		}
	}

	public static String smallestFromLeaf(TreeNode root) {
		if (root == null) {
			return "";
		}
		dfs(root, "");
//		for (String s : strList) {
//			System.out.print(s + " ");
//		}
//		System.out.println();
//		return strList.get(0);
		return smallestStr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(2);

		// 第二层
		TreeNode p1 = new TreeNode(1);
		TreeNode p2 = new TreeNode(2);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(3);
		p1.left = q11;
		TreeNode q22 = new TreeNode(4);
		p2.right = q22;
		TreeNode q12 = new TreeNode(3);
		p1.right = q12;
		TreeNode q21 = new TreeNode(4);
		p2.left = q21;

		System.out.println("smallest string = " + smallestFromLeaf(root));
	}

}
