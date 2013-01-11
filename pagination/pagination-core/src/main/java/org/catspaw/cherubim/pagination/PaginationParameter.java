package org.catspaw.cherubim.pagination;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class PaginationParameter implements Serializable {

	public static final String	PAGINATION_PARAMETER_KEY	= PaginationParameter.class.getName();
	public static final String	PARAMETER_MAP_KEY			= "parameter_map";
	private SearchMode			searchMode					= SearchMode.RESEARCH;
	private int					pageNo						= 1;

	public PaginationParameter(int pageNo, SearchMode searchMode) {
		this.pageNo = pageNo;
		this.searchMode = searchMode;
	}

	public String toJsonString() {
		JSONObject o = JSONObject.fromObject(this);
		return o.toString();
	}

	public SearchMode getSearchMode() {
		return searchMode;
	}

	public int getPageNo() {
		return pageNo;
	}

	@Override
	public String toString() {
		return toJsonString();
	}

	public static enum SearchMode {
		RESEARCH, CHANGE_PAGE;

		public int intValue() {
			switch (this) {
				case RESEARCH:
					return 1;
				case CHANGE_PAGE:
					return 0;
				default:
					return ordinal();
			}
		}

		public static SearchMode valueOf(int intValue) {
			switch (intValue) {
				case 1:
					return RESEARCH;
				case 0:
					return CHANGE_PAGE;
				default:
					throw new IllegalArgumentException("unsupport int value:" + intValue);
			}
		}
	}
}
