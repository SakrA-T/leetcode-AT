package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @className P437_PathSum3.java
 * @author AT
 * @version Create Time：2019年7月30日 下午9:35:52
 * @question: leetcode.p437:路径总和 III
 * @describe: 找出二叉树中路径和等于给定数值的路径总数。路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */

public class P437_PathSum3 {
	// 遍历树，对于每个节点，维护一个列表，列表值为从根节点到该节点的路径上，从根开始，逐个减去上层节点，所得到的路径上节点值的和。
	private static int dfs(TreeNode r, int sum, List<Integer> sumList) {
		int cur_count = 0;
		if (r != null) {
			int path_sum = 0;
			cur_count = (r.val == sum) ? 1 : 0;
			for (int i = 0; i < sumList.size(); i++) {
				// 在每条路径加上当前节点值
				path_sum = sumList.get(i) + r.val;
				if (path_sum == sum) {
					cur_count++;
				}
				sumList.set(i, path_sum);
			}
			// 当前单个节点作为路径
			sumList.add(r.val);
			// 这边要传递一个新创建的列表，不能在后续递归中修改当前列表，所以说是每个节点维护一个包含当前节点的路径的和的列表
			cur_count += dfs(r.left, sum, new ArrayList<>(sumList));
			cur_count += dfs(r.right, sum, new ArrayList<>(sumList));
		}
		return cur_count;
	}

	public static int pathSum(TreeNode root, int sum) {
		List<Integer> pathSumList = new ArrayList<Integer>();
		return dfs(root, sum, pathSumList);
	}

	// 优秀代码示例1
	// 双递归
	public static int pathSum1(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		// 当前根下的满足要求的路径条数，等于包含根节点或包含根节点左/右孩子的路径的条数
		return paths(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}

	private static int paths(TreeNode root, int sum) {

		if (root == null) {
			return 0;
		}

		int res = 0;
		if (root.val == sum) {
			res += 1;
		}

		res += paths(root.left, sum - root.val);
		res += paths(root.right, sum - root.val);

		return res;
	}

	// 优秀代码示例2
	// DFS加回溯，每次访问到一个节点，把该节点加入到当前的pathSum中
	// 然后判断是否存在一个之前的前n项和，其值等于pathSum与sum之差
	// 如果有，就说明现在的前n项和，所找到的之前的前n项和，之差等于sum，那么也就是说，这两个点之间的路径和，就是sum
	public static int pathSum2(TreeNode root, int sum) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);
		return backTrack(root, map, sum, 0);
	}

	private static int backTrack(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum) {
		int res = 0;
		if (root == null)
			return 0;

		pathSum += root.val;
		// 查找是否有=pathSum-sum的前n项和
		res += map.getOrDefault(pathSum - sum, 0);
		// 对于相同的和进行累计
		map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
		// 递归左右子树
		res += backTrack(root.left, map, sum, pathSum) + backTrack(root.right, map, sum, pathSum);
		// 回溯，把当前添加的路径和 去掉
		map.put(pathSum, map.get(pathSum) - 1);
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一层
		TreeNode root = new TreeNode(10);

		// 第二层
		TreeNode p1 = new TreeNode(5);
		TreeNode p2 = new TreeNode(-3);
		root.left = p1;
		root.right = p2;

		// 第三层
		TreeNode q11 = new TreeNode(3);
		p1.left = q11;
		TreeNode q12 = new TreeNode(2);
		p1.right = q12;
//		TreeNode q21 = new TreeNode(11);
//		p2.left = q21;
		TreeNode q22 = new TreeNode(11);
		p2.right = q22;

		// 第四层
		TreeNode q111 = new TreeNode(3);
		q11.left = q111;

		TreeNode q112 = new TreeNode(-2);
		q11.right = q112;
		TreeNode q122 = new TreeNode(1);
		q12.right = q122;

		int sum = 8;
		System.out.println(pathSum(root, sum));
	}

}
