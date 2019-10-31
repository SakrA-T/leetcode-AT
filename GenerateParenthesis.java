package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @className GenerateParenthesis.java
 * @author AT
 * @version Create Time：2019年7月4日 上午9:23:24
 */
public class GenerateParenthesis {
	public static List<String> generateParenthesis(int n) {
		List<String> rstList = new ArrayList<>();
		int i = 0, j = 0, k = 0, m = 0, p = 0;
		Object[][] pareArr = new Object[n][];
		pareArr[0] = new String[1];
		pareArr[0][0] = "";
		rstList.add("()");
		String str = "";
		for (i = 2; i <= n; i++) {
			pareArr[i - 1] = rstList.toArray();
			System.out.println("eachPare[" + (i - 1) + "] = " + Arrays.toString(pareArr[i - 1]));
			rstList.clear();
			for (k = 0; k < i; k++) {
				for (j = 0; j < pareArr[k].length; j++) {
					m = i - k - 1;
					System.out.println("i = " + i + ",j = " + j + ", k = " + k + ", m = " + m);
					for (p = 0; p < pareArr[m].length; p++) {
						str = "(" + pareArr[k][j] + ")" + pareArr[m][p];
						if (!rstList.contains(str)) {
							rstList.add(str);
							System.out.println("str = " + str);
						}
					}
				}
			}
		}
		return rstList;
	}

	// 优秀代码示例1——回溯法
	// ***思路：只有在我们知道序列仍然保持有效时才添加 '(' or ')'
	// 如果我们还剩一个位置，我们可以开始放一个左括号,如果它不超过左括号的数量，我们可以放一个右括号。
	public static List<String> generateParenthesis1(int n) {
		List<String> res = new ArrayList<>();
		backtrack(res, "", 0, 0, n);
		return res;
	}

	private static void backtrack(List<String> res, String cur, int open, int close, int n) {
		if (cur.length() == n * 2) {
			// 放置完成
			res.add(cur);
//			System.out.println(cur);
			return;
		}
		if (open < n) {
			backtrack(res, cur + "(", open + 1, close, n);
		}
		if (close < open) {
			backtrack(res, cur + ")", open, close + 1, n);
		}
	}

	// 优秀代码示例2——闭合数法
	// ***思路:为了枚举某些内容，我们通常希望将其表示为更容易计算的不相交子集的总和。
	// 考虑有效括号序列 S 的 闭包数：至少存在 index >= 0，使得S[0], S[1], ..., S[2*index+1]是有效的。
	// 显然，每个括号序列都有一个唯一的闭包号。 我们可以尝试单独列举它们。
	// ***算法:对于每个闭合数 c，我们知道起始和结束括号必定位于索引 0 和 2*c + 1。
	// 然后两者间的 2*c 个元素一定是有效序列，其余元素一定是有效序列。
	public static List<String> generateParenthesis2(int n) {
		List<String> ans = new ArrayList<>();
		if (n == 0) {
			ans.add("");
		} else {
			for (int c = 0; c < n; ++c)
				// 我方法的简化清晰版
				for (String left : generateParenthesis(c))
					for (String right : generateParenthesis(n - 1 - c))
						ans.add("(" + left + ")" + right);
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		List<String> rstList = generateParenthesis(n);
		ListIterator<String> iter = rstList.listIterator();
		int count = 0;
		System.out.println("[");
		while (iter.hasNext()) {
			count++;
			String item = iter.next();
			if (item == rstList.get(rstList.size() - 1)) {
				System.out.println("\" " + item + " \" ");
			} else {
				System.out.println("\" " + item + " \" , ");
			}
		}
		System.out.println("]");
		System.out.println("count = " + count);
	}

}
