package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @className P137_SingleNumber2.java
 * @author AT
 * @version Create Time：2019年7月11日 上午11:47:24
 * @question leetcode.p137:只出现一次的数字II
 * @describe 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * @require 线性时间复杂度，不使用额外空间。
 */
public class P137_SingleNumber2 {
	// 思路1：使用HashMap，线性时间复杂度，但有额外空间
	public static int singleNumber(int[] nums) {
		Map<Integer, Integer> numMap = new HashMap<>();
		for (int num : nums) {
			if (numMap.containsKey(num)) {
				numMap.replace(num, numMap.get(num) + 1);
				if (numMap.get(num) == 3) {
					numMap.remove(num);
				}
			} else {
				numMap.put(num, 1);
			}
		}
		return (int) numMap.keySet().toArray()[0];
	}

	// 思路2：通过这些不重复的元素和的三倍减去原来nums的和，得到的结果就是单个元素的两倍。
	// 可以利用set储存不重复元素，（HashSet可以用来存储互不相同的任何元素），集合本身就不允许有重复元素。

	// 以下两种模拟三进制法还是不是很懂

	// 优秀代码示例1
	public static int singleNumber1(int[] nums) {
		int a1 = 0, a2 = 0; // 分别记录出现一次的位，和出现两次的位
		for (int x : nums) {
			a1 = (x ^ a1) & (~a2); // 保留a1中为1或0，x中为0的位；添加x中为1，a1中为0的位；删去a1、x中都为1的位（出现两次了），置0。若a1中为1且a2中也为1，则说明出现3次，删去；所有在a2中为0的为，在a1中保留原值，所有a1中的0位，保留原值
			a2 = (x ^ a2) & (~a1); // 没有出现在a2中，记录，已经出现在a2中，删除；出现在a1中，删除。
		}
		return a1;
	}

	// 优秀代码示例2——（模拟三进制法），神仙思路！！！
	// 思路：可以通过某种运算$，使a$a$a=0，0$a=a，即创建“三进制下不考虑进位的加法”，这样将整个arr遍历加和，留下来的就是那个只出现一次的数字（其余各位都出现了3x次，一定为0）。
	public static int singleNumber2(int[] nums) {
		int ones = 0, twos = 0, threes = 0;
		for (int num : nums) {
			twos |= ones & num; // ones & num 提取ones和num中同为1的位（表示出现了两次1），与twos做 或操作 保留出现2次的位
			ones ^= num; // one异或num，one中为1，num中为0的位，表示出现一次，保留；one中为0,num中为1的位，表示出现1次，添加到one中；其余位从ones中清零或保持0，（因为同时为1已经加到twos里了，这里不做记录）
			threes = ones & twos;// 在ones和twos都为1的位，表示一共出现3次，置threes对应位为1
			ones &= ~threes; // 出现3次的位，表示不是所求，从ones和twos中删除
			twos &= ~threes; // 做到a$a$a=0
		}
		return ones; // 最终ones保留结果为只出现一次的那个元素。
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 0, 99, 1, 99, 99, 1, 1 };
		int single = singleNumber(nums);
		System.out.println(single);
	}

}
