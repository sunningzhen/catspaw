package org.catspaw.cherubim.security.shiro.rbac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.catspaw.cherubim.security.DefaultPrincipal;
import org.catspaw.cherubim.security.Principal;
import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.User;
import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;

public class RbacRealm extends AuthorizingRealm {

	private RbacRepository	repository;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		// Null username is invalid
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
		User user = repository.findUserByUsername(username);
		List<? extends Permission> permissions = repository.findPermissionsByUsername(username);
		Set<String> authorities = new HashSet<String>();
		for (Permission permission : permissions) {
			authorities.add(permission.getPermit());
		}
		Principal principal = new DefaultPrincipal(user, new HashSet<String>(authorities));
		String password = user.getPassword();
		return new SimpleAuthenticationInfo(principal, password, getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		Principal principal = (Principal) getAvailablePrincipal(principals);
		String username = principal.getUsername();
		Set<String> roleCodes = new HashSet<String>(repository.findRoleCodesByUsername(username));
		List<? extends Permission> permissions = repository.findPermissionsByUsername(username);
		List<String> resourceRightCodes = new ArrayList<String>(permissions.size());
		for (Permission permission : permissions) {
			resourceRightCodes.add(permission.getResourceSymbol());
		}
		Set<String> perms = PermissionUtils.toStringPermissions(resourceRightCodes, repository);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleCodes);
		info.setStringPermissions(perms);
		return info;
	}

	public RbacRepository getRbacRepository() {
		return repository;
	}

	public void setRbacRepository(RbacRepository repository) {
		this.repository = repository;
	}
}
