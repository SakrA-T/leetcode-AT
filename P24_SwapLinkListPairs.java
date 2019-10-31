package leetCode;

/**
 * @className P24_SwapLinkListPairs.java
 * @author AT
 * @version Create Time：2019年7月5日 下午4:26:24
 * @question leetcode.p24:给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。(不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。)
 */
public class P24_SwapLinkListPairs {
	// 思路：双指针
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode rstList = null;
		ListNode p = head, q = head.next;
		ListNode newHead = null;
		while (q.next != null && q.next.next != null) {
			p.next = q.next;
			q.next = p;
			if (newHead != null) {
				newHead.next.next = q;
			}
			newHead = q;
			if (rstList == null) {
				rstList = newHead;
			}
			p = p.next;
			q = p.next;
		}
		if (q.next != null) {
			p.next = q.next;
			q.next = p;
			if (newHead != null) {
				newHead.next.next = q;
			}
			newHead = q;
		} else {
			q.next = p;
			if (newHead != null) {
				newHead.next.next = q;
			}
			newHead = q;
			p.next = null;
		}
		if (rstList == null) {
			rstList = newHead;
		}
		return rstList;
	}

	// 优秀代码示例1
	public static ListNode swapPairs1(ListNode head) {
		// 使用虚拟头结点，更便于操作
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = dummy;
		while (current.next != null && current.next.next != null) {
			swap(current);
			current = current.next.next;
			// current当前是要交换的两个节点的第一个节点的前驱节点
		}
		return dummy.next;
	}

	private static void swap(ListNode pre) {
		ListNode dummy = pre.next;
		pre.next = dummy.next;
		dummy.next = dummy.next.next;
		pre.next.next = dummy;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(2);
		ListNode p3 = new ListNode(3);
		head.next = p2;
		p2.next = p3;
		ListNode p4 = new ListNode(4);
		ListNode p5 = new ListNode(5);
		p3.next = p4;
		p4.next = p5;
//		ListNode p6 = new ListNode(6);
//		ListNode p7 = new ListNode(7);
//		p5.next = p6;
//		ListNode p8 = new ListNode(8);
//		ListNode p9 = new ListNode(9);
//		p6.next = p7;
//		p7.next = p8;
//		p8.next = p9;
//		ListNode p10 = new ListNode(10);
//		p9.next = p10;
		ListNode rst = swapPairs(head);
		ListNode p = null;
		if (rst == null) {
			System.out.println(rst);
		} else {
			for (p = rst; p.next != null; p = p.next) {
				System.out.print(p.val + " -> ");
			}
			System.out.println(p.val);
		}
	}

}
