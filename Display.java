package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @className Display.java
 * @author AT
 * @version Create Time：2019年10月26日 下午3:32:54
 * @param <E>
 * @question: leetcode.p
 * @describe:
 */

public class Display<E> {
	public void doubleListDisplay(List<List<E>> dList) {
		for (List<E> list : dList) {
			System.out.print("[");
			for (E item : list) {
				System.out.print(item + ",");
			}
			System.out.println("]");
		}
	}

	private String[] levelTraversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<String> treeList = new ArrayList<String>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode crt = queue.poll();
				if (crt != null) {
					treeList.add(crt.val + "");
					if (crt.left != null) {
						queue.offer(crt.left);
					} else {
						if (crt.right != null)
							queue.offer(null);
					}
					if (crt.right != null) {
						queue.offer(crt.right);
					} else {
						if (crt.left != null)
							queue.offer(null);
					}
				} else {
					treeList.add("null");
				}

			}
		}
		return (String[]) treeList.toArray(new String[treeList.size()]);
	}

	public void treeDisplay(TreeNode root) {
		String[] rstArr = levelTraversal(root);
		System.out.println(Arrays.toString(rstArr));
	}
}
