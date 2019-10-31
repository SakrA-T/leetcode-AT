package leetCode;

/**
 * @className P328_OddEvenList.java
 * @author AT
 * @version Create Time：2019年7月5日 下午2:53:57
 * @question leetcode.p328: 给定一个单链表，把所有的奇数（编号）节点和偶数节点分别排在一起，并保持相对顺序（编号以1起）。
 */
public class P328_OddEvenList {
	public static ListNode oddEvenList(ListNode head) {
		ListNode rstList = head;
		if (head == null || head.next == null) {
			return rstList;
		}
		ListNode evenHead = head.next;
		int count = 1;
		ListNode p = evenHead, p1 = head, p2 = evenHead;
		ListNode oddTail = null;
		while (p != null) {
			oddTail = p1;
			if (count % 2 == 1) {
				p1.next = p.next;
				p1 = p1.next;
			} else {
				p2.next = p.next;
				p2 = p2.next;
			}
			p = p.next;
			count++;
		}
		oddTail.next = evenHead;
		return rstList;
	}

	// 优秀代码示例1
	public ListNode oddEvenList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode odd = new ListNode(0);
		ListNode a = head;
		ListNode b = odd;

		while (a.next != null && a.next.next != null) {
			b.next = a.next;
			a.next = a.next.next;
			b = b.next;
			a = a.next;
		}
		// 尾结点是偶数节点
		if (a.next != null) {
			b.next = a.next;
		} else
			// 尾结点是奇数节点
			b.next = null;
		a.next = odd.next;
		return head;
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
		ListNode rst = oddEvenList(head);
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
