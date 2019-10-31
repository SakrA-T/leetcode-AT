package leetCode;

import java.util.Stack;

/**
 * @className P227_SimpleCalculate.java
 * @author AT
 * @version Create Time：2019年7月6日 下午3:08:32
 * @question leetcode.p227:实现一个基本的计算器来计算一个简单的字符串表达式的值。
 */
public class P227_SimpleCalculate {
	static Stack<String> numStack = new Stack<>();
	static Stack<Character> symStack = new Stack<>();

	public static void mtpdiv() {
		String crtNum = "";
		int num1 = 0, num2 = 0;
		char symbol;
		num1 = Integer.parseInt(numStack.pop());
		num2 = Integer.parseInt(numStack.pop());
		symbol = symStack.pop();
		switch (symbol) {
		case '*':
			crtNum = Integer.toString(num2 * num1);
			break;
		case '/':
			crtNum = Integer.toString(num2 / num1);
			break;
		}
		numStack.push(crtNum);
	}

	public static int addsub() {
		int num1 = Integer.parseInt(numStack.pop());
		if (symStack.isEmpty()) {
			return num1;
		}
		char sym = symStack.pop();
		int num2 = addsub(), rst = 0;
		switch (sym) {
		case '+':
			rst = num2 + num1;
			break;
		case '-':
			rst = num2 - num1;
			break;
		}
		return rst;
	}

	public static int calculate(String s) {
		// 去掉两端空格
		s = s.trim();
		if (s.length() == 0) {
			return 0;
		}
		StringBuilder str = new StringBuilder(s);
		String crtNum = "";
		boolean isCompute = false, isPreNum = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case ' ':
				break;
			case '+':
			case '-':
				if (isCompute) {
					mtpdiv();
					isCompute = false;
				}
				symStack.push(c);
				isPreNum = false;
				break;
			case '*':
			case '/':
				if (isCompute) {
					mtpdiv();
				}
				symStack.push(c);
				isCompute = true;
				isPreNum = false;
				break;
			default:
				if (isPreNum) {
					crtNum = numStack.pop();
					crtNum = crtNum + c;
					numStack.push(crtNum);
				} else if (!isPreNum) {
					numStack.push(c + "");
					isPreNum = true;
				}

				break;
			}
			if (i == str.length() - 1 && isCompute) {
				mtpdiv();
			}
		}
		// 以上处理完之后符号栈中只剩下加减号
		return addsub();
	}

	// 优秀代码示例1（很优秀）
	// 思路：将运算视作多个乘除运算的相加（减视作-1*后续数值），如单个加减法，1-2视作1*1+1*(-2)。
	// 仅当再次遍历到符号或最后一个数值时，才计算前一个符号，且依据当前符号是否为加减或最后来判断是否统计当前计算结果
	public static int calculate1(String s) {
		int res = 0, curRes = 0, num = 0, n = s.length();
		char op = '+'; // 初始化为+的话curRes在遍历完第一个数之后会等于0+第一个数
		for (int i = 0; i < n; ++i) {
			char c = s.charAt(i);
			// 若连续遍历到数字，会自动转换为对应数值，相比我的方法来说，更好一些
			if (c >= '0' && c <= '9') {
				num = num * 10 + (c - '0');
			}
			// op 是当前遍历到的符号or最后一个字符c 的前一个符号
			// 若 c 是最后一个字符，则num为最后一个数值，op为最后一个符号，curRes为op的左值
			if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
				// num 为前一个符号的右值（当前符号的前一个数值），curRes为前一个符号的左值
				// 若curRes为0，op为+或-相当于是在确定前一个数的正负值，并由curRes保存
				switch (op) {
				case '+':
					curRes += num;
					break;
				case '-':
					curRes -= num;
					break;
				case '*':
					curRes *= num;
					break;
				case '/':
					curRes /= num;
					break;
				}
				// 若当前字符为+或-或最后一个字符，则将当前计算结果加到最终结果res上，并将当前计算结果置0，重新开始
				if (c == '+' || c == '-' || i == n - 1) {
					res += curRes;
					curRes = 0;
				}
				op = c;
				// 符号间断了连续遍历数字的计数，重新计数
				num = 0;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = " 1* 13- 1*5/2";
		int result = calculate1(s);
		System.out.println(s + " = " + result);
	}

}
