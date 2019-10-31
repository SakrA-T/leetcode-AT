package leetCode;

import java.util.Scanner;

/**
 * @className LinkedAddTwoNumbers.java
 * @author SakrA
 * @version 创建时间：2019年3月20日 下午3:04:16
 */
public class LinkedAddTwoNumbers {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode addList = new ListNode(0);
		ListNode p = addList;
		int carryBit = 0;
		int sum = 0;
		while (l1 != null && l2 != null) {
			sum = l1.val + l2.val + carryBit;
			carryBit = sum / 10;
			l1 = l1.next;
			l2 = l2.next;
			p.val = sum % 10;
			if (!(l1 == null && l2 == null) || (l1 == null && l2 == null && carryBit != 0))
				p.next = new ListNode(0);
			p = p.next;
			if (l1 == null && l2 == null && carryBit != 0) {
				p.val = carryBit;
				p.next = null;
			}
		}
		while (l2 != null) {
			sum = l2.val + carryBit;
			carryBit = sum / 10;
			p.val = sum % 10;
			l2 = l2.next;
			if (l2 != null || (l2 == null && carryBit != 0))
				p.next = new ListNode(0);
			p = p.next;
		}
		while (l1 != null) {
			sum = l1.val + carryBit;
			carryBit = sum / 10;
			p.val = sum % 10;
			l1 = l1.next;
			if (l1 != null || (l1 == null && carryBit != 0))
				p.next = new ListNode(0);
			p = p.next;
		}
		if (carryBit != 0) {
			p.val = carryBit;
			p.next = null;
		}
		return addList;
	}

	// 优秀代码示例
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null ? p.val : 0);
			int y = (q != null ? q.val : 0);
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String numStr = sc.nextLine();
		String[] num1 = numStr.split(" ");
		ListNode l1 = new ListNode(0);
		ListNode item1 = l1;
		for (int i = 0; i < num1.length; i++) {
			item1.val = Integer.parseInt(num1[i]);
			if (i != num1.length - 1)
				item1.next = new ListNode(0);
			item1 = item1.next;
		}
		String numStr2 = sc.nextLine();
		String[] num2 = numStr2.split(" ");
		ListNode l2 = new ListNode(0);
		ListNode item2 = l2;
		for (int j = 0; j < num2.length; j++) {
			item2.val = Integer.parseInt(num2[j]);
			if (j != num2.length - 1)
				item2.next = new ListNode(0);
			item2 = item2.next;
		}
		ListNode rst = addTwoNumbers(l1, l2);
		while (rst.next != null) {
			System.out.print(rst.val + "->");
			rst = rst.next;
		}
		System.out.println(rst.val);
		sc.close();
	}

}
