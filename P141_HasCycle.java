package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P141_HasCycle.java
 * @author: AT
 * @version: 2019年7月22日 下午7:31:33
 * @question: leetcode.p141:环形链表
 * @describe: 给定一个链表，判断链表中是否有环。
 * @describe: 为了表示给定链表中的环，我们使用整数pos来表示链表尾连接到链表中的位置（索引从0开始）。 如果pos是-1，则在该链表中没有环。
 */

public class P141_HasCycle {
	// 思路：创建数组保存对应节点是否被遍历过，boolean类型数组的默认值是false
	public static boolean hasCycle(ListNode head) {
		List<ListNode> visitedList = new ArrayList<>();
		ListNode p = head;
		while (!visitedList.contains(p)) {
			if (p == null) {
				return false;
			}
			visitedList.add(p);
			p = p.next;
		}
		return true;
	}

	// 优秀代码示例——快慢指针
	public boolean hasCycle1(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return false;
			}
			slow = slow.next;
			// 快指针每次加快一个节点，这样的话，不断遍历下去，如果存在环，快慢指针必会重合。
			fast = fast.next.next;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ListNode head = null;
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(3);
		head.next = p2;
		ListNode p3 = new ListNode(-4);
		p2.next = p3;
		ListNode p4 = new ListNode(2);
		ListNode p5 = new ListNode(3);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(-4);
		ListNode p7 = new ListNode(2);
		p5.next = p6;
		ListNode p8 = new ListNode(3);
		ListNode p9 = new ListNode(5);
		p6.next = p7;
		p7.next = p8;
		p8.next = p9;
		ListNode p10 = new ListNode(3);
		p9.next = p10;
		p10.next = p5;

		System.out.println(hasCycle(head));

	}

}
