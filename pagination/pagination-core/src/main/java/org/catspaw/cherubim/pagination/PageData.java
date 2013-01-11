package org.catspaw.cherubim.pagination;

import java.io.Serializable;
import java.util.List;

public interface PageData<T> extends Serializable, Iterable<T> {

	int	DEFAULT_PAGE_SIZE	= 20;

	List<T> getData();

	int getOffset();

	int getPageSize();

	boolean hasNextPage();

	boolean hasPreviousPage();

	int getFirstPageNo();

	int getPreviousPageNo();

	int getNextPageNo();

	int getLastPageNo();

	int getOffsetOfFirstPage();

	int getOffsetOfPreviousPage();

	int getOffsetOfNextPage();

	int getOffsetOfLastPage();

	int getOffsetOfAnyPage(int pageNumber);

	int getTotalCount();

	int getCurrentPageNo();

	int getTotalPageCount();
}
