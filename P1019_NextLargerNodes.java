package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @fileName: P1019_NextLargerNodes.java
 * @author: AT
 * @version: 2019年7月22日 上午10:59:43
 * @question: leetcode.p1019:链表中的下一个更大节点
 * @describe: 对于每个节点，求其后第一个 val 比它大的节点的值组成的数组。（若不存在该节点，则保存0）
 */

public class P1019_NextLargerNodes {
	// 思路1：常规解法，双指针，对每个节点，往后查找其下一个更大节点或至链表尾
	// 思路2：构造下标链
	public static int[] nextLargerNodes(ListNode head) {
		// indexList 若是下一个元素比它大则存储下一个元素下标值，否则存0
		// headList 按顺序存储原链表的val值
		List<Integer> indexList = new ArrayList<>();
		List<Integer> headList = new ArrayList<>();
		ListNode p = head;
		int count = 0;
		while (p != null) {
			count++;
			if (p.next != null && p.next.val > p.val) {
				indexList.add(count);
			} else {
				indexList.add(0);
			}
			headList.add(p.val);
			p = p.next;
		}
//		System.out.println("len = " + count);
		int[] rstArray = new int[count];
		int index = 0, j = 0;
		// 从倒数第二个元素开始，反向遍历并填充结果数组
		for (int i = count - 2; i >= 0; i--) {
			index = indexList.get(i);
			if (index != 0) {
				rstArray[i] = headList.get(index);
				j = index;
			} else {
				while (j != 0 && headList.get(j) <= headList.get(i)) {
					// 以下标链式的方式，找到第一个比当前元素大的值。
					j = indexList.get(j);
				}
				// 将比当前元素大的值的下标记录
				indexList.set(i, j);
				rstArray[i] = (j == 0) ? 0 : headList.get(j);
			}
		}
		indexList.clear();
		headList.clear();
		return rstArray;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// [1,7,5,1,9,2,5,1,3,6]
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(7);
		head.next = p2;
		ListNode p3 = new ListNode(5);
		p2.next = p3;
		ListNode p4 = new ListNode(1);
		ListNode p5 = new ListNode(9);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(2);
		p5.next = p6;
		ListNode p7 = new ListNode(5);
		ListNode p8 = new ListNode(1);
		ListNode p9 = new ListNode(3);
		p6.next = p7;
		p7.next = p8;
		p8.next = p9;
		ListNode p10 = new ListNode(6);
		p9.next = p10;
		int[] rst = nextLargerNodes(head);
		System.out.println(Arrays.toString(rst));
	}

}
