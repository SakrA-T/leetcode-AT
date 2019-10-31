package leetCode;

/**
 * @className P83_DeleteDuplicates.java
 * @author AT
 * @version Create Time：2019年7月17日 下午3:55:16
 * @question leetcode.p83:删除排序链表中的重复元素
 * @describe 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 */
public class P83_DeleteDuplicates {

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode p = head;
		ListNode del = new ListNode(0);
		while (p != null && p.next != null) {
			if (p.next.val == p.val) {
				del = p.next;
				p.next = p.next.next;
				del.next = null;
			} else
				p = p.next;
		}
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(1);
		head.next = p2;
		ListNode p3 = new ListNode(2);
		p2.next = p3;
		ListNode p4 = new ListNode(4);
		ListNode p5 = new ListNode(4);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(6);
		ListNode p7 = new ListNode(7);
		p5.next = p6;
		p6.next = p7;
		ListNode p8 = new ListNode(7);
		ListNode p9 = new ListNode(7);
		p7.next = p8;
		p8.next = p9;
		ListNode p10 = new ListNode(10);
		p9.next = p10;
		ListNode rst = deleteDuplicates(head);
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
