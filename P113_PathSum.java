package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @className P113_PathSum.java
 * @author AT
 * @version Create Time：2019年7月30日 下午4:52:56
 * @question: leetcode.p113:路径总和II
 * @describe: 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 */

public class P113_PathSum {
	// DFS遍历+回溯，计算每一条路径的节点值总和，将总和等于目标值的路径添加到最终列表中

	private static List<List<Integer>> rstList = new ArrayList<List<Integer>>();

	private static void pathSearch(TreeNode r, int sum, List<Integer> list, int cur_sum) {
		if (r.left == null && r.right == null && cur_sum == sum) {
			rstList.add(new ArrayList<>(list));
			return;
		}
		int index = list.size();
		if (r.left != null) {
			list.add(r.left.val);
			// 不能修改cur_sum的值，因为之后还需要用原值
			pathSearch(r.left, sum, list, cur_sum + r.left.val);
			// 回溯，要去掉当前添加的元素，在原来的基础上进行再执行
			list.remove(index);
		}
		if (r.right != null) {
			list.add(r.right.val);
			pathSearch(r.right, sum, list, cur_sum + r.right.val);
			list.remove(index);
		}
	}

	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		if (root == null) {
			return rstList;
		}
		List<Integer> list = new ArrayList<>();
		list.add(root.val);
		pathSearch(root, sum, list, root.val);

		return rstList;
	}

	// 优秀代码示例1
	public static List<List<Integer>> pathSum1(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> item = new ArrayList<>();
		helper(root, sum, res, item);
		return res;
	}

	private static void helper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> item) {
		if (root == null)
			return;
		item.add(root.val);
		if (root.left == null && root.right == null && sum == root.val) {
			res.add(new ArrayList<Integer>(item));
		} else {
			helper(root.left, sum - root.val, res, item);
			helper(root.right, sum - root.val, res, item);
		}
		// 回溯，去掉当前添加的元素，再返回调用函数继续执行
		item.remove(item.size() - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(5);

		// 第二层
		TreeNode p1 = new TreeNode(4);
		TreeNode p2 = new TreeNode(8);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(11);
		p1.left = q11;
//		TreeNode q12 = new TreeNode(13);
//		p1.right = q12;
		TreeNode q21 = new TreeNode(13);
		p2.left = q21;
		TreeNode q22 = new TreeNode(4);
		p2.right = q22;

		// 第四层
		TreeNode q111 = new TreeNode(7);
		q11.left = q111;
//				TreeNode q22 = new TreeNode(4);
//				p2.right = q22;
		TreeNode q112 = new TreeNode(2);
		q11.right = q112;
		TreeNode q221 = new TreeNode(5);
		q22.left = q221;
		TreeNode q222 = new TreeNode(1);
		q22.right = q222;

		int sum = 22;
		List<List<Integer>> rstList = pathSum(root, sum);
		for (List<Integer> list : rstList) {
			System.out.print("[ ");
			for (Integer li : list) {
				System.out.print(li + " ");
			}
			System.out.println("]");
		}
	}

}
