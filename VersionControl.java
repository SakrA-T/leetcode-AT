package leetCode;

/**
 * @className VersionControl.java
 * @author AT
 * @version Create Time：2019年7月8日 下午2:04:45
 * @question for P278_FirstBadVersion
 */
public class VersionControl {
	static boolean isBadVersion(int version, int boundary) {
		if (version >= boundary) {
			return true;
		}
		return false;
	}

}
