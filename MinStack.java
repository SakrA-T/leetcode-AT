package leetCode;

import java.util.List;
import java.util.Stack;

/**
 * @className MinStack.java
 * @author AT
 * @version Create Time：2019年9月30日 下午7:08:40
 * @question: leetcode.p155:最小栈
 * @describe: 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * 
 */
//	push(x) -- 将元素 x 推入栈中。
//	pop() -- 删除栈顶的元素。
//	top() -- 获取栈顶元素。
//	getMin() -- 检索栈中的最小元素

// Your MinStack object will be instantiated and called as such:
// MinStack obj = new MinStack();
// obj.push(x);
// obj.pop();
// int param_3 = obj.top();
// int param_4 = obj.getMin();
class MinStack {
	/** initialize your data structure here. */
	private List<Integer> stack;

//	public MinStack() {
//		stack = new ArrayList<Integer>();
//	}

	public void push(int x) {
		stack.add(x);
	}

	public void pop() {
		stack.remove(stack.size() - 1);
	}

	public int top() {
		return stack.get(stack.size() - 1);
	}

	// 非常数时间
	public int getMin() {
		int min = stack.get(0);
		for (Integer i : stack) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

	// 优秀代码实例1
	Stack<Integer> stack1;
	Stack<Integer> minStack;

	public MinStack() {
		stack1 = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}

	public void push1(int x) {
		// 每添加一个元素到原栈中，同步添加一个元素到最小栈中，正好解决 pop 时的同步删除（注意边界）
		if (stack.isEmpty()) {
			minStack.push(x);
		} else {
			// 当要添加的元素A小于或等于当前最小栈中的最小元素（栈顶）B，则将当前元素添加到原栈的同时，将其添加到最小栈中
			// 当 A 大于 B 时，将当前最小栈中最小元素 B 直接添加到最小栈中，表示，如果A出现在原栈顶，则 minStack
			// 应该返回B值，没毛病！！！超厉害！！！
			if (x <= minStack.peek())
				minStack.push(x);
			else
				minStack.push(minStack.peek());
		}
		stack1.push(x);
	}

	public void pop1() {
		if (stack.isEmpty())
			return;
		stack1.pop();
		minStack.pop();
	}

	public int top1() {
		return stack1.peek();
	}

	public int getMin1() {
		return minStack.peek();
	}
}
