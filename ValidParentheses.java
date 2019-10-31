package leetCode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @className ValidParentheses.java
 * @author AT
 * @version Create Time：2019年7月3日 上午11:03:32
 */
public class ValidParentheses {
	// 思路：利用栈，遇到左括号则压栈，右括号则出栈栈顶，若出现栈顶与当前右括号不匹配，则返回false
	public static boolean isValid(String s) {
		HashMap<Character, Character> charMap = new HashMap<>();
		charMap.put('}', '{');
		charMap.put(']', '[');
		charMap.put(')', '(');
		Stack<Character> pareStack = new Stack<>();
		int len = s.length();
		Character item, top;
		for (int i = 0; i < len; i++) {
			item = s.charAt(i);
			// item为左括号
			if (charMap.containsValue(item)) {
				pareStack.push(item);
			} else {
				// item为右括号
				// 若存在没有左括号，只有右括号的情况，判断栈为空，无法执行出栈顶，则为不可匹配
				if (pareStack.isEmpty()) {
					return false;
				}
				top = pareStack.pop();
				if (!top.equals(charMap.get(item))) {
					return false;
				}
			}
		}
//		if (pareStack.isEmpty()) {
//			return true;
//		} else {
//			return false;
//		}
		return pareStack.isEmpty();
	}

	// 优秀代码示例1
	// 综合我的做法和示例2的做法，相比我做了优化，显然比我的更容易理解和实现
	public boolean isValid1(String s) {
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');

		Stack<Character> stack = new Stack<Character>();

		for (char c : s.toCharArray()) {
			if (map.containsKey(c))
				// 若遍历到对应key值，则对应value值压栈，方便后续比较
				stack.push(map.get(c));
			// 将两个条件进行完美组合
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}

	// 优秀代码示例2
	// s.toCharArray() 将字符串转换为数组再进行foreach遍历，更方便
	public boolean isValid2(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			// 当遍历到左括号时，将对应右括号压栈；
			// 当遍历到右括号时（为最后一个if条件），若当前栈空则返回false；若不空，则pop栈顶，若栈顶元素不等于当前元素则括号不匹配，返回false
			// ***好聪明的做法！！！！！！！！！！！！
			if (c == '(')
				stack.push(')');
			else if (c == '[')
				stack.push(']');
			else if (c == '{')
				stack.push('}');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pareStr1 = "{()[()]";
		boolean isvalid = isValid(pareStr1);
		System.out.println("\"" + pareStr1 + "\":" + isvalid);
		String pareStr2 = "[({})]";
		isvalid = isValid(pareStr2);
		System.out.println("\"" + pareStr2 + "\":" + isvalid);
		String pareStr3 = "";
		isvalid = isValid(pareStr3);
		System.out.println("\"" + pareStr3 + "\":" + isvalid);
		String pareStr4 = "})";
		isvalid = isValid(pareStr4);
		System.out.println("\"" + pareStr4 + "\":" + isvalid);
	}

}
