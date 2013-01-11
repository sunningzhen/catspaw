package org.catspaw.cherubim.pagination;

import java.util.Properties;

public class PaginationResource {

	public static final String	PAGINATION_RESOURCE_KEY	= PaginationResource.class
																.getName();
	private String				scriptTemplate;
	private String				multiPageBannerTemplate;
	private String				firstPageBannerTemplate;
	private String				lastPageBannerTemplate;
	private String				onePageBannerTemplate;

	public static PaginationResource parseFromProperties(Properties properties) {
		PaginationResource resource = new PaginationResource();
		resource.setScriptTemplate(properties
				.getProperty("pagination.script_template"));
		resource.setMultiPageBannerTemplate(properties
				.getProperty("pagination.banner.multi"));
		resource.setFirstPageBannerTemplate(properties
				.getProperty("pagination.banner.first"));
		resource.setLastPageBannerTemplate(properties
				.getProperty("pagination.banner.last"));
		resource.setOnePageBannerTemplate(properties
				.getProperty("pagination.banner.one"));
		return resource;
	}

	public String getScriptTemplate() {
		return scriptTemplate;
	}

	public String getMultiPageBannerTemplate() {
		return multiPageBannerTemplate;
	}

	public String getFirstPageBannerTemplate() {
		return firstPageBannerTemplate;
	}

	public String getLastPageBannerTemplate() {
		return lastPageBannerTemplate;
	}

	public String getOnePageBannerTemplate() {
		return onePageBannerTemplate;
	}

	public void setScriptTemplate(String scriptTemplate) {
		this.scriptTemplate = scriptTemplate;
	}

	public void setMultiPageBannerTemplate(String multiPageBannerTemplate) {
		this.multiPageBannerTemplate = multiPageBannerTemplate;
	}

	public void setFirstPageBannerTemplate(String firstPageBannerTemplate) {
		this.firstPageBannerTemplate = firstPageBannerTemplate;
	}

	public void setLastPageBannerTemplate(String lastPageBannerTemplate) {
		this.lastPageBannerTemplate = lastPageBannerTemplate;
	}

	public void setOnePageBannerTemplate(String onePageBannerTemplate) {
		this.onePageBannerTemplate = onePageBannerTemplate;
	}
}
