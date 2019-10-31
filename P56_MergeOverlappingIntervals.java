package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @fileName: P56_MergeOverlappingIntervals.java
 * @author: AT
 * @version: 2019年7月25日 下午9:00:18
 * @question: leetcode.p56:合并区间
 * @describe: 给出一个区间的集合，请合并所有重叠的区间。
 * @example: 如 [1,3] 和 [2,6] 区间重叠了，应该合并为 [1,6] .
 */

public class P56_MergeOverlappingIntervals {
	// 注意类接口的用法
	// 可以放在类内部，需要加static；可以防止外部作为单独类，不用加private static
	private static class IntervalsComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] arr1, int[] arr2) {
			if (arr1[0] > arr2[0]) {
				return 1;
			} else if (arr1[0] == arr2[0]) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public static int[][] merge(int[][] intervals) {
		if (intervals.length <= 1) {
			return intervals;
		}
		// 将其按每个数组的第一个元素值进行排序
		Arrays.sort(intervals, new IntervalsComparator());
		int i = 0, j = 1;
		while (j < intervals.length) {
			// 合并的情况
			if (intervals[i][1] >= intervals[j][0]) {
				intervals[i][1] = intervals[j][1] > intervals[i][1] ? intervals[j][1] : intervals[i][1];
			} else {
				i++;
				intervals[i][0] = intervals[j][0];
				intervals[i][1] = intervals[j][1];
			}
			j++;
		}
		int[][] mergeArr = Arrays.copyOfRange(intervals, 0, i + 1);
		return mergeArr;
	}

	// 优秀代码示例1
	private static class IntervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval a, Interval b) {
			return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
		}
	}

	public static List<Interval> merge1(List<Interval> intervals) {
		Collections.sort(intervals, new IntervalComparator());

		LinkedList<Interval> merged = new LinkedList<Interval>();
		for (Interval interval : intervals) {
			if (merged.isEmpty() || merged.getLast().end < interval.start) {
				merged.add(interval);
			} else {
				merged.getLast().end = Math.max(merged.getLast().end, interval.end);
			}
		}

		return merged;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 我的方法
		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 15, 18 }, { 6, 10 }, { 12, 17 } };
		int[][] mergeArr = merge(intervals);
		System.out.print("[ ");
		for (int[] item : mergeArr) {
			System.out.print(Arrays.toString(item) + " ");
		}
		System.out.println("]");

		// 示例的方法
		List<Interval> intervals1 = new ArrayList<Interval>();
		intervals1.add(new Interval(1, 3));
		intervals1.add(new Interval(2, 6));
		intervals1.add(new Interval(15, 18));
		intervals1.add(new Interval(6, 10));
		intervals1.add(new Interval(12, 17));
		List<Interval> mergeArr1 = merge1(intervals1);
		System.out.print("[ ");
		for (Interval item : mergeArr1) {
			System.out.print("[" + item.start + ", " + item.end + "] ");
		}
		System.out.println("]");
	}

}
