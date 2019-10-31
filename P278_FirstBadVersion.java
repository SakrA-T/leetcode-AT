package leetCode;

/**
 * @className P278_FirstBadVersion.java
 * @author AT
 * @version Create Time：2019年7月8日 下午2:02:27
 * @question leetcode.p278:你可以通过调用( bool isBadVersion(version))
 *           接口来判断版本号version是否在单元测试中出错。实现一个函数来查找第一个错误的版本。应该尽量减少对调用 API 的次数。
 */
public class P278_FirstBadVersion extends VersionControl {
	// 保证left为false(即非错误版本)，right为true（即错误版本）
	public static int binarySearch(int left, int right, int boundary) {
		if (left == right) {
			return right;
		}
		double sum = (double) left + (double) right;
		int mid = (int) (sum / 2);
		int rst = 0;
		if (isBadVersion(mid, boundary)) {
			rst = binarySearch(left, mid, boundary);
		} else {
			rst = binarySearch(mid + 1, right, boundary);
		}
		return rst;
	}

	public static int firstBadVersion(int n, int boundary) {
		int rst = 0;
		if (isBadVersion(1, boundary)) {
			return 1;
		}
		rst = binarySearch(1, n, boundary);
		return rst;
	}

	// 优秀代码示例1
	public static int firstBadVersion1(int n, int boundary) {
		int l = 1;
		for (; l < n;) {
			// 可以避免使用(n+l)/2时出现的(n+l)溢出Integer.MAX_VALUE
			int m = l + (n - l) / 2;
			// 若m为错误版本，则[m+1,right],都是错误，则循环判断[left,m]；
			// 否则，表示[left,m]都是正确，循环判断[m+1,right]
			if (isBadVersion(m, boundary)) {
				n = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		int boundary = 1;
		int rst = firstBadVersion(n, boundary);
		System.out.println(rst);
	}

}
