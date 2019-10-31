package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @className SimplifyPath.java
 * @author AT
 * @version Create Time：2019年7月4日 下午3:07:29
 */
public class SimplifyPath {
	public static String simplifyPath(String path) {
		String rstPath = "";
		String[] pathArr = path.split("/");
//		System.out.println("pathArr[1] =" + pathArr[1]);
		System.out.println("pathArr = " + Arrays.toString(pathArr));
		Stack<String> pathStack = new Stack<>();
//		for (int i = 1; i < pathArr.length; i++) {
//			if (pathArr[i].equals("..")) {
//				if (!pathStack.empty()) {
//					pathStack.pop();
//				}
//			} else if (pathArr[i].equals("") || pathArr[i].equals(".")) {
//				continue;
//			} else {
//				pathStack.push(pathArr[i]);
//			}
//		}
		for (String pStr : pathArr) {
			if (pStr.equals("..")) {
				if (!pathStack.empty()) {
					pathStack.pop();
				}
			} else if (pStr.equals("") || pStr.equals(".")) {
				continue;
			} else {
				pathStack.push(pStr);
			}
		}
		while (!pathStack.empty()) {
			// 栈顶应该位于字符串末尾
			rstPath = "/" + pathStack.pop() + rstPath;
		}
		return (rstPath.length() == 0) ? "/" : rstPath;
	}

	// 优秀代码示例1（没仔细看）
	public String simplifyPath1(String path) {
		int i = path.length() - 1, count = 0;
		List<String> symbols = new ArrayList<>();
		while (i > 0) {
			int j = path.lastIndexOf('/', i);
			if (i == j)
				i--;
			else {
				String x = path.substring(j + 1, i + 1);
				i = j - 1;
				if (".".equals(x))
					;
				else if ("..".equals(x))
					count++;
				else if (count > 0)
					count--;
				else
					symbols.add(0, x);
			}
		}

		return symbols.isEmpty() ? "/" : "/" + String.join("/", symbols);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path1 = "/a//b////c/d//././/..";
		String path2 = "/..//../";
		String path3 = "/a/../../b/../c//.//";
		String spPath1 = simplifyPath(path1);
		System.out.println("spPath1 = " + spPath1);
		String spPath2 = simplifyPath(path2);
		System.out.println("spPath2 = " + spPath2);
		String spPath3 = simplifyPath(path3);
		System.out.println("spPath3 = " + spPath3);
	}

}
