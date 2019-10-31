package leetCode;

/**
 * @className P92_ReverseBetween.java
 * @author AT
 * @version Create Time：2019年10月2日 下午8:32:04
 * @question: leetcode.p92: 反转链表Ⅱ
 * @describe: 反转从位置 m 到 n 的链表(从1开始计数)。请使用一趟扫描完成反转。
 */

public class P92_ReverseBetween {
	// 迭代链接反转
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		// pre 是开始翻转的结点的前一个结点
		int i = 0;
		for (i = 1; i < m; i++) {
			pre = pre.next;
		}
		// 开始翻转
		ListNode start = pre.next;
		ListNode q = start.next;
		for (int j = i; j < n; j++) {
			ListNode p = q;
			q = q.next;
			p.next = pre.next;
			pre.next = p;
		}
		start.next = q;
		return dummy.next;
	}

	// 优秀代码示例1
	// 递归回溯交换值解法。有点强！！！
	// Object level variables since we need the changes
	// to persist across recursive calls and Java is pass by value.
	private boolean stop;
	private ListNode left;

	public void recurseAndReverse(ListNode right, int m, int n) {
		if (n == 1) {
			return;
		}
		// 右指针后移，直到n==1
		right = right.next;

		// 继续将左指针向右移动，直到到达开始翻转的节点
		if (m > 1) {
			this.left = this.left.next;
		}
		// 用m和n简化递归。
		this.recurseAndReverse(right, m - 1, n - 1);
		// 如果两个指针互相交叉或相等，停止左右结点数据的交换
		if (this.left == right || right.next == this.left) {
			this.stop = true;
		}
		// 未停止，交换左右指针的值。
		if (!this.stop) {
			int t = this.left.val;
			this.left.val = right.val;
			right.val = t;
			// 左指针后移一步
			// 右指针通过回溯前移一部
			this.left = this.left.next;
		}
	}

	public ListNode reverseBetween1(ListNode head, int m, int n) {
		this.left = head;
		this.stop = false;
		this.recurseAndReverse(head, m, n);
		return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode p2 = new ListNode(2);
		head.next = p2;
		ListNode p3 = new ListNode(3);
		p2.next = p3;
		ListNode p4 = new ListNode(4);
		ListNode p5 = new ListNode(5);
		p3.next = p4;
		p4.next = p5;

		ListNode p6 = new ListNode(6);
		ListNode p7 = new ListNode(7);
		p5.next = p6;
		p6.next = p7;
		int m = 1, n = 7;
		ListNode rst = reverseBetween(head, m, n);
		ListNode p = rst;
		while (p != null) {
			System.out.print(p.val + "->");
			p = p.next;
		}
		System.out.println("NULL");
	}

}
