package leetCode;

import java.util.Scanner;
import java.util.Stack;

/**
 * @className P150_EvalRPN.java
 * @author AT
 * @version Create Time：2019年10月27日 下午4:14:11
 * @question: leetcode.p150:逆波兰表达式求值
 * @describe: 根据逆波兰表示法，求表达式的值。
 * @describe: 逆波兰表示法:后缀表达式（将运算符写在操作数之后）
 */

public class P150_EvalRPN {
	// 思路：结合后缀表达式计算规律，利用栈。
	// 简介：新建一个表达式,如果当前字符为变量或者为数字，则压栈，如果是运算符，则将栈顶两个元素弹出作相应运算，结果再入栈，最后当表达式扫描完后，栈里的就是结果。
	public static int evalRPN(String[] tokens) {
		Stack<String> calcStack = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			int leftNum = 0, rightNum = 0;
			String crt = tokens[i];
			switch (tokens[i]) {
			case "+":
				rightNum = Integer.parseInt(calcStack.pop());
				leftNum = Integer.parseInt(calcStack.pop());
				crt = (leftNum + rightNum) + "";
				break;
			case "-":
				rightNum = Integer.parseInt(calcStack.pop());
				leftNum = Integer.parseInt(calcStack.pop());
				crt = (leftNum - rightNum) + "";
				break;
			case "*":
				rightNum = Integer.parseInt(calcStack.pop());
				leftNum = Integer.parseInt(calcStack.pop());
				crt = (leftNum * rightNum) + "";
				break;
			case "/":
				rightNum = Integer.parseInt(calcStack.pop());
				leftNum = Integer.parseInt(calcStack.pop());
				crt = (leftNum / rightNum) + "";
				break;
			}
			calcStack.push(crt);
		}
		return Integer.parseInt(calcStack.pop());
	}

	// 优秀代码示例1
	// 用数组代替栈操作，是更方便一些，但是每次更新到k的位置，[0...k]是有效数据，后面的有数据，但是是废弃的数据，需要注意
	public static int evalRPN1(String[] tokens) {
		int[] stack = new int[tokens.length];
		int k = -1;
		for (String s : tokens) {
			switch (s) {
			case "+":
				stack[k - 1] += stack[k]; // 直接运算并赋值，更方便
				k--;
				break;
			case "-":
				stack[k - 1] -= stack[k];
				k--;
				break;
			case "*":
				stack[k - 1] *= stack[k];
				k--;
				break;
			case "/":
				stack[k - 1] /= stack[k];
				k--;
				break;
			default:
				stack[++k] = Integer.parseInt(s);
			}
		}
		return stack[0];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String str = scanner.nextLine();
			if (str.equals("0")) {
				break;
			}
			String[] tokens = str.split(" ");
			int rst = evalRPN(tokens);
			System.out.println(rst);
		}
		scanner.close();
	}

}
