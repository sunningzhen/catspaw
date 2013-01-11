package org.catspaw.cherubim.security;

import java.util.Collection;
import org.catspaw.cherubim.organization.Branch;
import org.catspaw.cherubim.organization.Department;
import org.catspaw.cherubim.organization.Post;
import org.catspaw.cherubim.security.rbac.User;

public class OrganizationPrincipal extends DefaultPrincipal {

	private static final long	serialVersionUID	= 6136798626372867854L;
	private Branch				branch;
	private Department			department;
	private Collection<Post>	posts;

	public OrganizationPrincipal(String username, String password, Collection<String> authorities, Branch branch,
			Department department, Collection<Post> posts) {
		super(username, password, authorities);
		this.branch = branch;
		this.department = department;
		this.posts = posts;
	}

	public OrganizationPrincipal(User user, Collection<String> authorities, Branch branch, Department department,
			Collection<Post> posts) {
		super(user, authorities);
		this.branch = branch;
		this.department = department;
		this.posts = posts;
	}

	public Branch getBranch() {
		return branch;
	}

	public Department getDepartment() {
		return department;
	}

	public Collection<Post> getPosts() {
		return posts;
	}
}
