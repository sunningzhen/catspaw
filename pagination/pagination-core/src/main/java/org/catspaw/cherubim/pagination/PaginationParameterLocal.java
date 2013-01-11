package org.catspaw.cherubim.pagination;

public class PaginationParameterLocal {

	private static final ThreadLocal<PaginationParameter>	local	= new ThreadLocal<PaginationParameter>();

	public static void bind(PaginationParameter pp) {
		local.set(pp);
	}

	public static PaginationParameter get() {
		return local.get();
	}

	public static void unbind() {
		local.remove();
	}
}
