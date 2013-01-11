package org.catspaw.cherubim.pagination;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 分页页面对象. 包含数据及分页信息，Page类实现了用于显示分页信息的基本方法
 */
public final class DefaultPageData<T> implements PageData<T> {

	private static final long	serialVersionUID	= -1900102918731464577L;
	private int					hash;											//hashCode
	private int					pageSize;										// 每页的记录数，实际记录数小于或等于它
	private int					offset;											//当前页第一条数据的位置,从0开始
	private int					totalCount;									//总记录数
	private int					totalPageCount;								//总页数
	private int					currentPageNo;									//当前页号
	private List<T>				data;											//当前页中存放的记录

	/**
	 * 构造方法，只构造空页
	 */
	@SuppressWarnings("unchecked")
	public DefaultPageData() {
		this(0, 0, 1, Collections.EMPTY_LIST);
		initHash();
	}

	/**
	 * 默认构造方法
	 * @param offset 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize 本页容量
	 * @param data 本页包含的数据
	 */
	public DefaultPageData(int offset, int totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.offset = offset;
		this.currentPageNo = (offset / pageSize) + 1;
		this.totalCount = totalSize;
		totalPageCount = PageUtils.countPage(totalSize, pageSize);
		this.data = Collections.unmodifiableList(data);//不可修改的List
		initHash();
	}

	public DefaultPageData(List<T> data, int totalSize, int offset, int pageSize) {
		this.pageSize = pageSize;
		this.offset = offset;
		this.currentPageNo = (offset / pageSize) + 1;
		this.totalCount = totalSize;
		totalPageCount = PageUtils.countPage(totalSize, pageSize);
		this.data = Collections.unmodifiableList(data);//不可修改的List
		initHash();
	}

	public DefaultPageData(List<T> data) {
		this.data = Collections.unmodifiableList(data);//不可修改的List
		initHash();
	}

	public DefaultPageData(PageData<T> page) {
		this(page.getOffset(), page.getTotalCount(), page.getPageSize(), page
				.getData());
		initHash();
	}

	private void initHash() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + pageSize;
		result = prime * result + offset;
		result = prime * result + totalCount;
		this.hash = result;
	}

	/**
	 * 当前页中的记录
	 */
	public List<T> getData() {
		return this.data;
	}

	public int getOffset() {
		return offset;
	}

	/**
	 * 取本页数据容量（本页能包含的记录数）
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 是否有下一页
	 */
	public boolean hasNextPage() {
		return (this.getCurrentPageNo() < this.getTotalPageCount() - 1);
	}

	/**
	 * 是否有上一页
	 */
	public boolean hasPreviousPage() {
		return (this.getCurrentPageNo() > 1);
	}

	public int getFirstPageNo() {
		return 1;
	}

	public int getPreviousPageNo() {
		return Math.max(this.currentPageNo - 1, 1);
	}

	public int getNextPageNo() {
		return Math.min(this.currentPageNo + 1, totalPageCount);
	}

	public int getLastPageNo() {
		return totalPageCount;
	}

	public int getOffsetOfFirstPage() {
		return 1;
	}

	/**
	 * 获取上一页第一条数据在数据库中的位置
	 */
	public int getOffsetOfPreviousPage() {
		return Math.max(this.offset - this.pageSize, 1);
	}

	/**
	 * 获取下一页第一条数据在数据库中的位置
	 */
	public int getOffsetOfNextPage() {
		return this.offset + this.pageSize;
	}

	public int getOffsetOfLastPage() {
		int oddment = totalCount % pageSize;
		if (oddment != 0) {
			return totalCount - oddment + 1;
		}
		return Math.max(totalCount - pageSize + 1, 1);
	}

	/**
	 * 获取任一页第一条数据在数据库中的位置，每页条数使用默认值
	 */
	public int getOffsetOfAnyPage(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		} else if (pageNumber > totalPageCount) {
			pageNumber = totalPageCount;
		}
		return PageUtils.getOffsetOfPage(pageNumber, pageSize);
	}

	/**
	 * 取数据库中包含的总记录数
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取当前页码,页码从1开始
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * 取总页数
	 */
	public int getTotalPageCount() {
		return totalPageCount;
	}

	public Iterator<T> iterator() {
		return data.iterator();
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DefaultPageData<?> other = (DefaultPageData<?>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (offset != other.offset)
			return false;
		if (totalCount != other.totalCount)
			return false;
		return true;
	}
}
