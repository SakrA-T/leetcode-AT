package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @className P49_GroupAnagrams.java
 * @author AT
 * @version Create Time：2019年10月2日 下午5:04:18
 * @question: leetcode.p49: 字母异位词分组
 * @describe: 给定一个字符串数组，将字母异位词组合在一起（放到一个list中）。字母异位词指字母相同，但排列不同的字符串
 */

public class P49_GroupAnagrams {
	// 方案1：单次遍历，在对每个单词进行排序后对比，是否已经存在相应队列。
	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> rst = new ArrayList<List<String>>();
		List<String> group = new ArrayList<>();
		for (String str : strs) {
			char[] strArr = str.toCharArray();
			Arrays.sort(strArr);
			String sortStr = new String(strArr);

			if (group.indexOf(sortStr) == -1) {
				List<String> itemList = new ArrayList<>();
				group.add(sortStr);
				itemList.add(str);
				rst.add(itemList);
			} else {
				rst.get(group.indexOf(sortStr)).add(str);
			}
		}
		return rst;
	}

	// 优秀代码示例1
	// 思路和我的相同，实现方式更佳，利用映射表进行直接对应
	public List<List<String>> groupAnagrams1(String[] strs) {
		// 找到相同的字符串，主要是排序不一样
		HashMap<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			String key = sort(str);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(str);
		}
		return new ArrayList<>(map.values());
	}

	String sort(String str) {
		char[] c = str.toCharArray();
		Arrays.sort(c);
		return String.valueOf(c);
	}

	public static void main(String[] args) {
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> rst = groupAnagrams(strs);
		for (List<String> item : rst) {
			System.out.print("[");
			for (String str : item) {
				System.out.printf("%6s", str);
			}
			System.out.println(" ]");
		}
	}

}
