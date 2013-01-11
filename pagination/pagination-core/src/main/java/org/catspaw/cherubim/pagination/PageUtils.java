package org.catspaw.cherubim.pagination;

import java.util.List;

public final class PageUtils {

	private PageUtils() {
	}

	/**
	 * 获取任一页第一条数据的位置,startIndex从0开始
	 */
	public static int getOffsetOfPage(int pageNo, int pageSize) {
		int startIndex = (pageNo - 1) * pageSize;
		if (startIndex < 0) {
			startIndex = 0;
		}
		return startIndex;
	}

	/**
	 * 获取当前页的起始记录数
	 * @param pageSize
	 * @return
	 */
	public static int getOffsetOfPage(int pageSize) {
		PaginationParameter pp = PaginationParameterLocal.get();
		int pageNo = pp.getPageNo();
		return getOffsetOfPage(pageNo, pageSize);
	}

	public static <T> PageData<T> createPage(int start, int totalSize,
			int pageSize, List<T> data) {
		return new DefaultPageData<T>(start, totalSize, pageSize, data);
	}

	public static int countPage(int totalSize, int pageSize) {
		int page = totalSize / pageSize;
		int mod = totalSize % pageSize;
		if (mod > 0) {
			return ++page;
		}
		return Math.max(page, 1);
	}
}
