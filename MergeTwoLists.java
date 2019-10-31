package leetCode;

/**
 * @className MergeTwoLists.java
 * @author AT
 * @version Create Time：2019年7月3日 上午9:37:20
 */
public class MergeTwoLists {
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// 此处做修改，利用虚拟头节点，便不需要判断是否两链表全空了
		// ListNode rstList = null;
		ListNode rstList = new ListNode(-1);

//		ListNode q;
//		if (l1 != null || l2 != null) {
//			rstList = new ListNode(-1);
//			q = rstList;
//		} else {
//			return rstList;
//		}
		ListNode p1 = l1, p2 = l2, q = rstList;
		while (p1 != null && p2 != null) {
			if (p1.val <= p2.val) {
				// 此处做修改，无需如此复杂，直接创建节点对象
//				q.val = p1.val;
				q.next = new ListNode(p1.val);
				p1 = p1.next;
			} else {
				q.next = new ListNode(p2.val);
				p2 = p2.next;
			}
//			q.next = new ListNode(-1);
			q = q.next;
		}
		while (p1 != null) {
			q.next = new ListNode(p1.val);
			q = q.next;
			p1 = p1.next;
		}
		while (p2 != null) {
			q.next = new ListNode(p2.val);
			q = q.next;
			// q 指向当前合并链表的最后一个节点，该节点q.next = null
			p2 = p2.next;
		}
		return rstList.next;
	}

	// 优秀代码示例
	// 问：这种直接连接的方式是否存在安全隐患？
	// 网友解：若原链表在其他地方有所引用，最好创建新的链表进行数据存储，否则，改变原链表指向的方法更优（并未创建新链表，减少了空间复杂度）。
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		// 类似归并排序中的合并过程
		// 此处最佳：使用了一个虚拟头节点，唯一空间消耗就是这个虚拟节点
		ListNode dummyHead = new ListNode(0);
		ListNode cur = dummyHead;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				cur = cur.next;
				l1 = l1.next;
			} else {
				cur.next = l2;
				cur = cur.next;
				l2 = l2.next;
			}
		}
		// 任一为空，直接连接另一条链表
		if (l1 == null) {
			cur.next = l2;
		} else {
			cur.next = l1;
		}
		// 虚拟头不要
		return dummyHead.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head1 = new ListNode(1);
		ListNode p2 = new ListNode(2);
		ListNode p3 = new ListNode(4);
		head1.next = p2;
		p2.next = p3;
		p3.next = null;

		ListNode head2 = new ListNode(1);
		ListNode p4 = new ListNode(3);
		ListNode p5 = new ListNode(4);
		head2.next = p4;
		p4.next = p5;
		p5.next = null;

		ListNode rstList = mergeTwoLists(head1, head2);
		ListNode p = rstList;
		while (p.next != null) {
			System.out.print(p.val + " -> ");
			p = p.next;
		}
		System.out.println(p.val);
	}

}
