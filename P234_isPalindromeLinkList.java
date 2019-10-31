package leetCode;

import java.util.Stack;

/**
 * @className P234_isPalindromeLinkList.java
 * @author AT
 * @version Create Time：2019年7月18日 上午11:37:56
 * @question leetcode.p234:回文链表
 * @describe 判断一个链表是否为回文链表。
 */
public class P234_isPalindromeLinkList {
	// 思路1：利用栈，但是空间复杂度不是O(1)
	// 思路2：将前半部分链表进行翻转
	public static boolean isPalindrome(ListNode head) {
		ListNode p = head;
		int len = 0;
		Stack<Integer> valStack = new Stack<>();
		while (p != null) {
			len++;
			p = p.next;
		}
		System.out.println(len);
		p = head;
		for (int i = 0; i < len / 2; i++) {
			valStack.push(p.val);
			p = p.next;
		}
		if (len % 2 == 1) {
			p = p.next;
		}
		while (p != null) {
			if (valStack.pop() != p.val) {
				return false;
			}
			p = p.next;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
//		ListNode p2 = new ListNode(1);
//		head.next = p2;
//		ListNode p3 = new ListNode(2);
//		p2.next = p3;
//
//		ListNode p4 = new ListNode(4);
//		ListNode p5 = new ListNode(6);
//		p3.next = p4;
//		p4.next = p5;
//
//		ListNode p6 = new ListNode(4);
//		ListNode p7 = new ListNode(2);
//		p5.next = p6;
//		p6.next = p7;
//
//		ListNode p8 = new ListNode(1);
//		ListNode p9 = new ListNode(1);
//		p7.next = p8;
//		p8.next = p9;
//		ListNode p10 = new ListNode(1);
//		p9.next = p10;
		System.out.println(isPalindrome(head));
	}

}
