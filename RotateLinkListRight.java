package leetCode;

/**
 * @className RotateLinkListRight.java
 * @author AT
 * @version Create Time：2019年7月4日 下午7:09:30
 * @question leetcode.p61: 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 */
public class RotateLinkListRight {
	// 思路：将右移个数转换为左移个数，将链表链接为循环链表，从head（第0个）起遍历到第len-k个元素，以其为head，并将其前一个元素的next置null
	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		int len = 1;
		ListNode p = head;
		while (p.next != null) {
			p = p.next;
			len++;
		}
		System.out.println("len = " + len);
		// 右移k个，相当于左移len-k个
		k = k % len;
		// 链接为循环链表，head为循环链表的头，p为尾
		p.next = head;
		for (int i = 0; i < len - k; i++) {
			head = head.next;
			p = p.next;
		}
		p.next = null;
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ListNode head = new ListNode(0);
//		ListNode p2 = new ListNode(1);
//		ListNode p3 = new ListNode(2);
//		ListNode p4 = new ListNode(4);
//		ListNode p5 = new ListNode(5);
//		head.next = p2;
//		p2.next = p3;
		ListNode head = null;
//		p3.next = p4;
//		p4.next = p5;
		int k = 4;
		ListNode rstList = rotateRight(head, k);
		if (rstList != null) {

			ListNode p = null;
			for (p = rstList; p.next != null; p = p.next) {
				System.out.print(p.val + " -> ");
			}
			System.out.println(p.val);
		} else {
			System.out.println(rstList);
		}
	}

}
