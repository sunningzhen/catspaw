package org.catspaw.cherubim.pagination.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.catspaw.cherubim.pagination.PageData;
import org.catspaw.cherubim.pagination.PaginationResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jsp分页标签 global.properties里page.banner.full是页面显示的模板
 * @author 孙宁振
 */
public class PaginationTag extends BodyTagSupport {

	private static final Logger	logger	= LoggerFactory
												.getLogger(PaginationTag.class);
	private String				action;
	private String				method	= "post";
	private PageData<Object>	page;

	@Override
	public int doEndTag() throws JspException {
		PaginationResource resource = (PaginationResource) pageContext
				.getServletContext()
				.getAttribute(PaginationResource.PAGINATION_RESOURCE_KEY);
		if (page != null) {
			StringBuilder scriptBuilder = new StringBuilder(resource
					.getScriptTemplate());
			replaceString(scriptBuilder, "${action}", action);
			replaceString(scriptBuilder, "${method}", method);
			String script = scriptBuilder.toString();
			logger.debug("script: " + script);
			String firstPage = script.replace("${pageNo}", page
					.getFirstPageNo()
					+ "");
			logger.debug("firstPage: " + firstPage);
			String previousPage = script.replace("${pageNo}", page
					.getPreviousPageNo()
					+ "");
			logger.debug("previousPage: " + previousPage);
			String nextPage = script.replace("${pageNo}", page.getNextPageNo()
					+ "");
			logger.debug("nextPage: " + nextPage);
			String lastPage = script.replace("${pageNo}", page.getLastPageNo()
					+ "");
			logger.debug("lastPage: " + lastPage);
			StringBuilder bannerBuilder = new StringBuilder(
					getBannerTemplate(page, resource));
			replaceString(bannerBuilder, "${firstPage}", firstPage);
			replaceString(bannerBuilder, "${previousPage}", previousPage);
			replaceString(bannerBuilder, "${nextPage}", nextPage);
			replaceString(bannerBuilder, "${lastPage}", lastPage);
			replaceString(bannerBuilder, "${currPage}", page.getCurrentPageNo()
					+ "");
			replaceString(bannerBuilder, "${totalPage}", page
					.getTotalPageCount()
					+ "");
			replaceString(bannerBuilder, "${totalCount}", page.getTotalCount()
					+ "");
			String banner = bannerBuilder.toString();
			logger.debug("banner: " + banner);
			try {
				pageContext.getOut().write(banner);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		return EVAL_PAGE;
	}

	private void replaceString(StringBuilder builder, String oldStr,
			String newStr) {
		int begin = builder.indexOf(oldStr);
		if (begin > 0) {
			int end = begin + oldStr.length();
			builder.replace(begin, end, newStr);
		}
	}

	private String getBannerTemplate(PageData<Object> page,
			PaginationResource resource) {
		if (page.getFirstPageNo() == page.getLastPageNo()) {
			return resource.getOnePageBannerTemplate();
		}
		if (page.getCurrentPageNo() == page.getFirstPageNo()) {
			return resource.getFirstPageBannerTemplate();
		}
		if (page.getCurrentPageNo() == page.getLastPageNo()) {
			return resource.getLastPageBannerTemplate();
		}
		return resource.getMultiPageBannerTemplate();
	}

	public PageData<Object> getPage() {
		return page;
	}

	public String getAction() {
		return action;
	}

	public String getMethod() {
		return method;
	}

	public void setPage(PageData<Object> page) {
		this.page = page;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
