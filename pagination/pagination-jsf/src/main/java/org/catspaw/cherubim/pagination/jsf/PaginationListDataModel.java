package org.catspaw.cherubim.pagination.jsf;

import javax.faces.model.DataModel;

import org.catspaw.cherubim.pagination.PageData;

public abstract class PaginationListDataModel<T> extends DataModel {

	/**
	 * 页大小
	 */
	private final int	pageSize;
	/**
	 * 当前数据所在的行数
	 */
	private int			rowIndex	= -1;
	/**
	 * 一页的数据集
	 */
	private PageData<T>	pageData;
	/**
	 * 总页数
	 */
	private int			pageCount	= -1;
	/**
	 * 当前页数
	 */
	private int			currentPage	= 1;
	private boolean		visitable;

	public PaginationListDataModel(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setWrappedData(Object o) {
		if (o instanceof PageData) {
			this.pageData = (PageData<T>) o;
		} else {
			throw new IllegalArgumentException(
					"WrappedData must be a instance of PageData");
		}
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int index) {
		rowIndex = index;
	}

	public int getRowCount() {
		if (!isVisitable()) {
			return 0;
		}
		return getPageData().getTotalCount();
	}

	private PageData<T> getPageData() {
		if (pageData != null) {
			return pageData;
		}
		int rowIndex = getRowIndex();
		int startRow = rowIndex;
		if (rowIndex == -1) {
			// even when no row is selected, we still need a page
			// object so that we know the amount of data available.
			startRow = 0;
		}
		// invoke method on enclosing class
		pageData = fetchPage(startRow, pageSize);
		return pageData;
	}

	public Object getRowData() {
		if (!isVisitable()) {
			return null;
		}
		if (rowIndex < 0) {
			throw new IllegalArgumentException(
					" Invalid rowIndex for PagedListDataModel; not within page ");
		}
		// ensure page exists; if rowIndex is beyond dataset size, then
		// we should still get back a PageData object with the dataset size
		// in it
		if (pageData == null) {
			pageData = fetchPage(rowIndex, pageSize);
		}
		int totalCount = pageData.getTotalCount();
		int startRow = pageData.getOffset();
		int nRows = pageData.getData().size();
		int endRow = startRow + nRows;
		if (rowIndex >= totalCount) {
			throw new IllegalArgumentException(" Invalid rowIndex ");
		}
		if (rowIndex < startRow) {
			pageData = fetchPage(rowIndex, pageSize);
			startRow = pageData.getOffset();
		} else if (rowIndex >= endRow) {
			pageData = fetchPage(rowIndex, pageSize);
			startRow = pageData.getOffset();
		}
		return pageData.getData().get(rowIndex - startRow);
	}

	public Object getWrappedData() {
		if (!isVisitable()) {
			return null;
		}
		return pageData.getData();
	}

	public boolean isRowAvailable() {
		if (!isVisitable()) {
			return false;
		}
		PageData<T> page = getPageData();
		if (page == null) {
			return false;
		}
		int rowIndex = getRowIndex();
		if (rowIndex < 0) {
			return false;
		} else if (rowIndex >= page.getTotalCount()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取一页数据
	 * @param startRow 起始行数
	 * @param pageSize 页大小
	 * @return 数据集
	 */
	public abstract PageData<T> fetchPage(int startRow, int pageSize);

	/**
	 * 刷新本页
	 */
	public void refresh() {
		if (this.pageData != null) {
			this.pageData = null;
			getPageData();
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageCount() {
		if (!isVisitable()) {
			return 0;
		}
		if (pageCount < 0) {
			int totalCount = getPageData().getTotalCount();
			pageCount = totalCount / pageSize;
			if (totalCount % pageSize != 0) {
				pageCount++;
			}
		}
		return pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean isVisitable() {
		return visitable;
	}

	public void setVisitable(boolean visitable) {
		this.visitable = visitable;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
