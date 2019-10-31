package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className P966_Spellchecker.java
 * @author AT
 * @version Create Time：2019年7月19日 下午5:19:22
 * @question leetcode.p966:元音拼写检查器
 * @describe 在给定单词列表 wordlist 的情况下，实现一个拼写检查器(3种对应方式)，将查询单词转换为正确的单词。
 */
public class P966_Spellchecker {
//	static char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
	static List<Character> vowels = new ArrayList<>();

	// 超出时间限制
	public static boolean vowelChecker(String word, String query) {
		String q = query.toLowerCase();
		String w = word.toLowerCase();
		int w_len = word.length(), q_len = q.length();
		char wc = ' ', qc = ' ';
		if (w_len == q_len) {
//			System.out.println("w = " + w + ", before_q = " + q);
			for (int i = 0; i < w_len; i++) {
				wc = w.charAt(i);
				qc = q.charAt(i);
				if (wc != qc) {
					if (!vowels.contains(wc) || !vowels.contains(qc)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public static String[] spellchecker(String[] wordlist, String[] queries) {
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		String[] answer = new String[queries.length];
		int count = 0;
		// 实现优先级
		for (String q : queries) {
			answer[count] = "";
			for (String w : wordlist) {
				// 为什么这边写 w==q 会正常执行，不会出现不相等应该使用w.equal(q)的问题
				if (w.equals(q)) {
					answer[count] = w;
					break;
				}
			}
			if (answer[count] != "") {
				count++;
				continue;
			}
			for (String w : wordlist) {
				if (w.toLowerCase().equals(q.toLowerCase())) {
					answer[count] = w;
					break;
				}
			}
			if (answer[count] != "") {
				count++;
				continue;
			}
			for (String w : wordlist) {
				if (vowelChecker(w, q)) {
					answer[count] = w;
					break;
				}
			}
			count++;
		}
		return answer;
	}

	// 优秀代码示例1
	// 使用哈希表Map进行提前转换和对应的话更好一些。
	public static String[] spellchecker1(String[] wordlist, String[] queries) {
		Map<String, String> wordMap = new HashMap<>();
		// 大小写和元音错误匹配都放到 wordPatternMap中
		Map<String, String> wordPatternMap = new HashMap<>();
		for (String word : wordlist) {
			wordMap.put(word, word);
			wordPatternMap.putIfAbsent(word.toLowerCase(), word);
			wordPatternMap.putIfAbsent(wordPattern(word), word);
		}
		for (int i = 0; i < queries.length; i++) {
			if (wordMap.containsKey(queries[i])) {
				queries[i] = wordMap.get(queries[i]);
				continue;
			}
			String wordPattern = queries[i].toLowerCase();
			if (wordPatternMap.containsKey(wordPattern)) {
				queries[i] = wordPatternMap.get(wordPattern);
				continue;
			}
			wordPattern = wordPattern(queries[i]);
			if (wordPatternMap.containsKey(wordPattern)) {
				queries[i] = wordPatternMap.get(wordPattern);
				continue;
			}
			queries[i] = "";
		}
		return queries;
	}

	private static String wordPattern(String word) {
		char[] letters = word.toLowerCase().toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == 'a' || letters[i] == 'e' || letters[i] == 'i' || letters[i] == 'o' || letters[i] == 'u') {
				letters[i] = '*';
			}
		}
		return String.valueOf(letters);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] wordlist = { "zuo", "zeo", "noi", "nae" };
		String[] queries = { "Zeo", "nAe" };
		String[] answer = spellchecker(wordlist, queries);
		for (String str : answer) {
			System.out.println("\"" + str + "\" ");
		}
		System.out.println();
	}

}
