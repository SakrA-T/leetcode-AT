package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @className P100_IsSameTree.java
 * @author AT
 * @version Create Time：2019年7月29日 下午9:53:50
 * @question: leetcode.p100:相同的树
 * @describe: 给定两个二叉树，编写一个函数来检验它们是否相同。
 */

public class P100_IsSameTree {
	// 思路：同步遍历两棵树
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		Queue<TreeNode> pQueue = new LinkedList<>();
		Queue<TreeNode> qQueue = new LinkedList<>();
		TreeNode p_dummy = new TreeNode(0);
		p_dummy.left = p;
		TreeNode q_dummy = new TreeNode(0);
		q_dummy.left = q;
		// 添加虚拟节点，考虑两树都为空或有一个为空的情况
		pQueue.offer(p_dummy);
		qQueue.offer(q_dummy);
		while (!pQueue.isEmpty() || !qQueue.isEmpty()) {
			int size = pQueue.size();
			for (int i = 0; i < size; i++) {
				TreeNode p_cur = pQueue.poll();
				TreeNode q_cur = qQueue.poll();
				if (p_cur.val != q_cur.val) {
					return false;
				}
				if (p_cur.left != null && q_cur.left != null) {
					pQueue.offer(p_cur.left);
					qQueue.offer(q_cur.left);
				} else if (p_cur.left != null || q_cur.left != null) {
					return false;
				}
				if (p_cur.right != null && q_cur.right != null) {
					pQueue.offer(p_cur.right);
					qQueue.offer(q_cur.right);
				} else if (p_cur.right != null || q_cur.right != null) {
					return false;
				}
			}
		}
		if (!pQueue.isEmpty() || !qQueue.isEmpty()) {
			return false;
		}
		return true;
	}

	// 优秀代码示例1
	// ？？？ 我在写什么？递归不就很好吗！！！，时间复杂度相同O(n)，空间复杂度最坏都为O(n)，维护递归栈需要空间！
	public static boolean isSameTree1(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一棵树：第一层
		TreeNode p = new TreeNode(1);

		// 第二层
		TreeNode p1 = new TreeNode(2);
		TreeNode p2 = new TreeNode(2);
		p.left = p1;
		p.right = p2;

		// 第三层
		TreeNode p11 = new TreeNode(4);
		p1.left = p11;
		TreeNode p22 = new TreeNode(4);
		p2.right = p22;
		TreeNode p12 = new TreeNode(3);
		p1.right = p12;
		TreeNode p21 = new TreeNode(3);
		p2.left = p21;

		// 第二棵树：第一层
		TreeNode q = new TreeNode(1);

		// 第二层
		TreeNode q1 = new TreeNode(2);
		TreeNode q2 = new TreeNode(2);
		q.left = q1;
		q.right = q2;

		// 第三层
		TreeNode q11 = new TreeNode(4);
		q1.left = q11;
		TreeNode q22 = new TreeNode(4);
		q2.right = q22;
		TreeNode q12 = new TreeNode(3);
		q1.right = q12;
//		TreeNode q21 = new TreeNode(3);
//		q2.left = q21;

		System.out.println(isSameTree(p, q));
	}

}
