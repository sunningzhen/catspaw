package org.catspaw.cherubim.security.ss3.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * 登出时清除UserCache里的数据
 * @author 孙宁振
 */
public class UserCacheLogoutHandler implements LogoutHandler {

	private UserCache	userCache;

	public UserCacheLogoutHandler(UserCache userCache) {
		this.userCache = userCache;
	}

	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		if (authentication != null) {
			Object pri = authentication.getPrincipal();
			if (pri instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) pri;
				userCache.removeUserFromCache(userDetails.getUsername());
			}
		}
	}
}
