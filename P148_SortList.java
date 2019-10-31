package leetCode;

/**
 * @fileName: P148_SortList.java
 * @author: AT
 * @version: 2019年7月25日 下午12:43:51
 * @question: leetcode.p148:排序链表
 * @describe: 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 */

public class P148_SortList {
	// 思路：将链表二分，断开，遍历排序链接
	public static ListNode binarySort(ListNode h, int n) {
		// n 为h链表的总节点个数
		// 添加虚拟头节点
		ListNode combine = new ListNode(0);
		if (h.next == null) {
			return h;
		}
		// 4 -> -2 -> 1 ->| 3 -> -3 -> 5 ->| 9 -> 8 -> -1 ->| 0 -> 6 -> 5
		ListNode left = h, r_pre = h, right = h;
		int l_len = (n + 1) / 2, r_len = n - (n + 1) / 2;
		for (int i = 1; i < l_len; i++) {
			r_pre = r_pre.next;
		}
		right = r_pre.next;
		// 左右断开
		r_pre.next = null;
		// 分别排序
		left = binarySort(left, l_len);
		right = binarySort(right, r_len);

		// 归并排序
		combine.next = left;
		ListNode p = combine;
		while (left != null && right != null) {
			if (left.val > right.val) {
				r_pre = right;
				right = right.next;
				// q接到p的后面，left的前面
				p.next = r_pre;
				r_pre.next = left;
				p = r_pre;
			} else {
				left = left.next;
				p = p.next;
			}
		}
		// 若left不为空，其实left剩余部分已经在最终合并之后的链表中了，所以不用再考虑；
		// 若right不为空，将right剩余部分接到p的后面
		if (right != null) {
			p.next = right;
		}

		return combine.next;
	}

	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p = head;
		int count = 0;
		while (p != null) {
			count++;
			p = p.next;
		}
		System.out.println("count = " + count);

		return binarySort(head, count);
//		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(4);
		ListNode p2 = new ListNode(-2);
		head.next = p2;
		ListNode p3 = new ListNode(1);
		p2.next = p3;
		ListNode p4 = new ListNode(3);
		ListNode p5 = new ListNode(-3);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(5);
		ListNode p7 = new ListNode(9);
		p5.next = p6;
		ListNode p8 = new ListNode(8);
		ListNode p9 = new ListNode(-1);
		p6.next = p7;
		p7.next = p8;
		p8.next = p9;
		ListNode p10 = new ListNode(0);
		p9.next = p10;
		ListNode p11 = new ListNode(6);
		p10.next = p11;
		ListNode p12 = new ListNode(5);
		p11.next = p12;
//		ListNode p13 = new ListNode(0);
//		p12.next = p13;

		ListNode rst = sortList(head);
		ListNode p = null;
		int n = 1;
		if (rst == null) {
			System.out.println(rst);
		} else {
			for (p = rst; p.next != null; p = p.next) {
				System.out.print(p.val + " -> ");
				n++;
			}
			System.out.println(p.val);
		}
		System.out.println("n = " + n);
	}

}
