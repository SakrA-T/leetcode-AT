package leetCode;

/**
 * @fileName: P129_SumNumbers.java
 * @author: AT
 * @version: 2019年7月24日 下午1:05:11
 * @question: leetcode.p129:求根到叶子节点数字之和
 * @describe: 每个根到叶的路径生成一个数值字符串（根在最高位），求所有路径的数值和
 */

public class P129_SumNumbers {
	private static int sum = 0;

	private static void dfs(TreeNode r, int num) {
		num = num * 10 + r.val;
		// r是叶结点，说明当前路径数值已经生成完毕，累加到总数上即可
		if (r.left == null && r.right == null) {
			sum += num;
		} else {
			if (r.left != null) {
				dfs(r.left, num);
			}
			if (r.right != null) {
				dfs(r.right, num);
			}
		}
	}

	public static int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		dfs(root, 0);
		return sum;
	}

	// 优秀代码示例1
	// 原理相同，实现上比我的更简化一些
	public static int sumNumbers1(TreeNode root) {
		return sum(root, 0);
	}

	public static int sum(TreeNode root, int i) {
		if (root == null)
			return 0;
		int parent = i * 10 + root.val;
		if (root.left == null && root.right == null) {
			// 若为叶结点，结束了，返回最终形成的数值
			return parent;
		}
		// 当前节点不为叶结点，继续累积数值
		return sum(root.left, parent) + sum(root.right, parent);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(4);

		// 第二层
		TreeNode p1 = new TreeNode(9);
		TreeNode p2 = new TreeNode(0);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(5);
		p1.left = q11;
		TreeNode q12 = new TreeNode(1);
		p1.right = q12;
//		TreeNode q22 = new TreeNode(1);
//		p2.right = q22;
//		TreeNode q21 = new TreeNode(4);
//		p2.left = q21;

		System.out.print(sumNumbers(root));
	}

}
