package leetCode;

/**
 * @className P725_SplitListToParts.java
 * @author AT
 * @version Create Time：2019年7月5日 上午10:46:26
 * @question leetcode.p725: 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 *           1)每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 *           2)如原链表只有3各元素，k为5时，不够分，则最后两个链表以空链表代替，满足条件1).
 */
public class P725_SplitListToParts {
	public static ListNode[] splitListToParts(ListNode root, int k) {
		ListNode[] rstListArr = new ListNode[k];
		if (root == null) {
			return rstListArr;
		}
		ListNode p = root, q = null;
		int len = 0, mod = 0, div = 0;
		while (p != null) {
			len++;
			p = p.next;
		}
		System.out.println("len = " + len);
		mod = len % k;
		div = len / k;
		int add = 0, j = 0, i = 0;
		p = root;
		for (j = 0; p != null && j < k; j++) {
			add = j < mod ? 1 : 0;
			// 每个链表有 div+add 个元素，结束循环时，p为该组最后一个元素的后一个元素，或原链表最后一个元素的next即null
			for (i = 0; p != null && i < (div + add); i++) {
				q = p;
				if (rstListArr[j] == null) {
					rstListArr[j] = p;
//					System.out.println("rstListArr[j] = " + rstListArr[j].val + ",div + add = " + (div + add));
				}
				// 将p后移一位,保存其前一个元素为q，方便后续将其next置为null
				p = p.next;
			}
			q.next = null;
		}
		return rstListArr;
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
		ListNode p6 = new ListNode(6);
		ListNode p7 = new ListNode(7);
		p5.next = p6;
		ListNode p8 = new ListNode(8);
		ListNode p9 = new ListNode(9);
		p6.next = p7;
		p7.next = p8;
		p8.next = p9;
		ListNode p10 = new ListNode(10);
		p9.next = p10;
		int k = 3;
		ListNode[] rstListArr = splitListToParts(head, k);
		for (ListNode item : rstListArr) {
			if (item == null) {
				System.out.println(item);
			} else {
				while (item.next != null) {
					System.out.print(item.val + " -> ");
					item = item.next;
				}
				System.out.println(item.val);
			}
		}
	}

}
