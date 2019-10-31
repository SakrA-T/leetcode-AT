package leetCode;

/**
 * @className RemoveNthFromEnd.java
 * @author AT
 * @version Create Time：2019年7月1日 下午8:39:00
 */
public class RemoveNthFromEnd {
	// definition for singly-linked list.

	// 思路：双指针，q-p=n-1，当q移动至链表末尾（倒数第1个节点）时，p为倒数第n个节点
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode rst = head;
		ListNode q = head, p = head, p_pre = head;
		int i = 0;
		for (i = 1; i < n; i++) {
			q = q.next;
		}
		// 若此时q为链表末尾元素，则表示要删除的元素为头节点
		if (q.next == null) {
			rst = head.next;
			head = null;
			return rst;
		}
		// q为第n个节点，p为第1个节点，循环结束q为尾节点，q.next为null
		while (q.next != null) {
			q = q.next;
			p_pre = p;
			p = p.next;
		}
		p_pre.next = p.next;
		p = null;
		return rst;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(2);
		ListNode p3 = new ListNode(3);
		ListNode p4 = new ListNode(4);
		ListNode p5 = new ListNode(5);
		head.next = p2;
		p2.next = p3;
		p3.next = p4;
		p4.next = p5;
//		p5.next = null;
		int n = 2;
		ListNode rst = removeNthFromEnd(head, n);
		ListNode p = null;
		for (p = rst; p.next != null; p = p.next) {
			System.out.print(p.val + " -> ");
		}
		System.out.println(p.val);
	}

}
