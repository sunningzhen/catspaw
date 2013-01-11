package org.catspaw.cherubim.security.ss3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import org.catspaw.cherubim.security.DefaultPrincipal;
import org.catspaw.cherubim.security.Principal;
import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.User;
import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;

public class RbacOrganizationUserDetailsService extends RbacUserDetailsService {

	private RbacRepository	rbacRepository;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(rbacRepository, "RbacRepository must not be null");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = rbacRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user: " + username);
		}
		List<? extends Permission> permissions = rbacRepository.findPermissionsByUsername(username);
		Set<String> authorities = new HashSet<String>();
		for (Permission permission : permissions) {
			authorities.add(permission.getPermit());
		}
		Principal principal = new DefaultPrincipal(user, new HashSet<String>(authorities));
		return new RbacUserDetails(principal);
	}

	public RbacRepository getRbacRepository() {
		return rbacRepository;
	}

	public void setRbacRepository(RbacRepository rbacRepository) {
		this.rbacRepository = rbacRepository;
	}
}
