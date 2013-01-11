package org.catspaw.cherubim.pagination.web;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;

import org.catspaw.cherubim.pagination.PaginationParameter;
import org.catspaw.cherubim.pagination.PaginationParameter.SearchMode;

public class PaginationParameterExtractor {

	public static PaginationParameter extractFromRequest(ServletRequest request) {
		String json = request.getParameter(PaginationParameter.PAGINATION_PARAMETER_KEY);
		JSONObject o = JSONObject.fromObject(json);
		int pageNo = o.getInt("pageNo");
		String searchModeStr = o.getString("searchMode");
		return new PaginationParameter(pageNo, SearchMode.valueOf(searchModeStr));
	}
}
