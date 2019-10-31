package leetCode;

import java.util.List;

/**
 * @fileName: Node.java
 * @author: AT
 * @version: 2019年7月23日 下午8:44:44
 * @describe 应用于：P559/P/P/P/P/P/P
 */

public class Node {
	public int val;
	public List<Node> children;

	public Node() {
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
}
