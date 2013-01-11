package org.catspaw.cherubim.security;

import java.util.Collection;

public interface SecurityMaster extends PrincipalMaster {

	boolean isPermitted(String resourceSymbol, String operationSymbol);

	boolean isPermitted(String permit);

	boolean isPermittedAll(Collection<String> permits);
}
