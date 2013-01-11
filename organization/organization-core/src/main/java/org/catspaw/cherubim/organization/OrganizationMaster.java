package org.catspaw.cherubim.organization;

import java.util.List;

public interface OrganizationMaster {

	Branch getTopBranch();

	List<? extends Branch> getChildBranchs(String parentId);

	Department getTopDepartment(String branchId);

	List<? extends Department> getChildDepartments(String branchId, String parentDepartmentId);

	List<? extends Post> getPosts(String departmentId);
	
	List<? extends Staff> getStaffs(String departmentId);
	
	Branch getBranchByStaffId(String staffId);
	
	Department getDepartmentByStaffId(String staffId);
	
	List<? extends Post> getPostsByStaffId(String staffId);
}
