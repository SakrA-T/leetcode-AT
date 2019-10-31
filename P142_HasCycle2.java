package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName: P142_HasCycle2.java
 * @author: AT
 * @version: 2019年7月22日 下午8:00:09
 * @question: leetcode.p142:环形链表 II
 * @describe: 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */

public class P142_HasCycle2 {
	// 思路1：只需要在P141的基础上修改返回值即可
	public static ListNode detectCycle(ListNode head) {
		List<ListNode> visitedList = new ArrayList<>();
		ListNode p = head;
		while (p != null && !visitedList.contains(p)) {
			visitedList.add(p);
			p = p.next;
		}
		return p;
	}

	// 优秀代码示例——快慢指针
	// 思路：简单计算会发现快慢指针相遇时的节点有一定规律：从该节点与从头节点同时遍历，两个遍历节点会在环入口节点相遇。
	// 可以简单画图并计算一下辅助理解：
	// 2(F+a) = F+a+b+a
	// 2F+2a = F+2a+b
	// F = b
	public static ListNode detectCycle1(ListNode head) {
		if (head == null || head.next == null)
			return null;
		ListNode slow = head;
		ListNode fast = head;

		// 以flag保证循环块一定会被执行一次，因为初始化时slow==fast
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				break;
		}
		if (slow != fast) {
			return null;
		}
		System.out.println(fast.val);
		slow = head;
		// slow和fast同时遍历的话，他们会在环入口节点相遇。
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(4);
		head.next = p2;
		ListNode p3 = new ListNode(-2);
		p2.next = p3;
		ListNode p4 = new ListNode(5);
		ListNode p5 = new ListNode(4);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(6);
		p5.next = p6;
		p6.next = p2;
		ListNode rst = detectCycle1(head);

		int count = 0;
		ListNode p = head;
		for (; p != null; p = p.next) {
			if (p == rst) {
				System.out.println("tail connects to node index " + count);
				break;
			}
			count++;
		}
		if (p == null) {
			System.out.print("no cycle");
		}
	}

}
