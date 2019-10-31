package leetCode;

/**
 * @className P82_DeleteDuplicates.java
 * @author AT
 * @version Create Time：2019年7月17日 下午4:09:23
 * @question leetcode.p82:删除排序链表中的重复元素 II
 * @describe 删除所有含有重复数字的节点（一个都不保留），只保留原始链表中没有重复的数字。
 */
public class P82_DeleteDuplicates2 {

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = head, q = dummy;
		ListNode del = new ListNode(0);
		boolean isDuplicate = false;
		while (p != null) {
			if (p.next != null && p.next.val == p.val) {
				isDuplicate = true;
				// 利用del进行垃圾处理
				del = p.next;
				p.next = p.next.next;
				del = null;
			} else {
				p = p.next;
				if (isDuplicate) {
					// 此时删除当前p的前一个元素（因为之前p后移了），q不需要动
					del = q.next;
					q.next = p;
					del.next = null;
					isDuplicate = false;
				} else {
					// 此时p和q 都要前进1
					q = q.next;
				}
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(2);
		head.next = p2;
		ListNode p3 = new ListNode(3);
		p2.next = p3;
		ListNode p4 = new ListNode(3);
		ListNode p5 = new ListNode(4);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(4);
		ListNode p7 = new ListNode(5);
		p5.next = p6;
		p6.next = p7;
//		ListNode p8 = new ListNode(7);
//		ListNode p9 = new ListNode(7);
//		p7.next = p8;
//		p8.next = p9;
//		ListNode p10 = new ListNode(10);
//		p9.next = p10;
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
