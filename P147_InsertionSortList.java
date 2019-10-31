package leetCode;

/**
 * @fileName: P147_InsertionSortList.java
 * @author: AT
 * @version: 2019年7月25日 下午5:36:43
 * @question: leetcode.p147:对链表进行插入排序
 * @describe: 对链表进行插入排序。
 */

public class P147_InsertionSortList {

	public static ListNode insertionSortList(ListNode head) {
		ListNode rst = new ListNode(0);
		if (head == null || head.next == null) {
			return head;
		}
		rst.next = head;
		ListNode p = head.next, pre_q = rst, q = pre_q.next;
		// 已排序与未排序断开
		head.next = null;

		// 寻找p插入的位置，并将其插入
		while (p != null) {
			pre_q = rst;
			q = pre_q.next;
			while (q != null && q.val <= p.val) {
				pre_q = q;
				q = q.next;
			}
			// p 插在 pre_q 与 q 之间
			pre_q.next = p;
			// p先后移，否则会丢失后续未排序链接；此时原p已经链接到pre_q上了，可以获取
			p = p.next;
			pre_q.next.next = q;
		}
		return rst.next;
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

		ListNode rst = insertionSortList(head);
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
