package org.catspaw.cherubim.security;

public class PermitUtils {

	public static final String	PERMIT_PRIFIX		= "PERM_";
	public static final String	WILDCARD_OPERATION	= "*";

	public static String buildPermit(String resourceSymbol, String operationSymbol) {
		if (operationSymbol == null) {
			operationSymbol = WILDCARD_OPERATION;
		}
		return PERMIT_PRIFIX + operationSymbol + ":" + resourceSymbol;
	}

	public static String extractResourceSymbol(String authority) {
		return authority.substring(PERMIT_PRIFIX.length()).split(":")[0];
	}

	public static String extractOperationSymbol(String authority) {
		return authority.substring(PERMIT_PRIFIX.length()).split(":")[1];
	}

	public static boolean isPermitted(String authority, String permit) {
		if (permit.equals(authority)) {
			return true;
		}
		String authRes = PermitUtils.extractResourceSymbol(authority);
		String permitRes = PermitUtils.extractResourceSymbol(permit);
		if (!permitRes.equals(authRes)) {
			return false;
		}
		String authOpr = PermitUtils.extractOperationSymbol(authority);
		if (WILDCARD_OPERATION.equals(authOpr)) {
			return true;
		}
		String permitOpr = PermitUtils.extractOperationSymbol(permit);
		if (permitOpr.equals(authOpr)) {
			return true;
		}
		return false;
	}
}
