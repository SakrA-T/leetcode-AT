package leetCode;

/**
 * @className ReverseLinkList.java
 * @author AT
 * @version Create Time：2019年7月5日 下午7:07:50
 * @question leetcode.p206 : 反转一个单链表。
 */
public class ReverseLinkList {
	// 思路：迭代
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dmHead = new ListNode(0);
		dmHead.next = head;
		ListNode p = head.next, ntHead = head, ctHead = head;
		while (p != null) {
			ntHead = p;
			ctHead = dmHead.next;
			dmHead.next = ntHead;
			p = ntHead.next;
			ntHead.next = ctHead;
			if (ctHead == head) {
				ctHead.next = null;
			}
//			ctHead = ntHead;
		}

		return dmHead.next;
	}

	// 优秀代码示例1——递归解法（没大懂）
	// 递归关键在于反向工作。假设列表的其余部分已经被反转，现在我该如何反转它前面的部分？
	// 假设列表为：n1->n2->n3->...->nk-> nk+1 -> nk+2 ->...-> nm;
	// k+1到m已经被反转，为n1->n2->n3->...-> nk -> nk+1 <- nk+2 <-...<- nm;
	// 现在要反转 nk -> nk+1 为 nk <- nk+1
	// 则要 nk.next.next = nk;
	// 注：nk 的 next 是指向null的，否则会造成循环
	public static ListNode reverseList1(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode a = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return a;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(2);
//		ListNode p3 = new ListNode(3);
		head.next = p2;
//		p2.next = p3;
//		ListNode p4 = new ListNode(4);
//		ListNode p5 = new ListNode(5);
//		p3.next = p4;
//		p4.next = p5;
//		ListNode head = null;
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
		ListNode rst = reverseList(head);
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
