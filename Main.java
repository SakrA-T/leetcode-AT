package leetCode;

/**
 * @className Main.java
 * @author AT
 * @version Create Time：2019年9月30日 下午7:14:11
 * @describe: 用于无主函数类：MinStack.java
 */

public class Main {

	public static void main(String[] args) {
		MinStack obj = new MinStack();
		obj.push(-2);
		obj.push(0);
		obj.push(-3);
		int b = obj.getMin();
		obj.pop();
		int a = obj.top();
		int c = obj.getMin();
		System.out.println("min : " + b);
		System.out.println("top : " + a);
		System.out.println("min : " + c);
	}

}
