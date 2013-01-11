package org.catspaw.cherubim.organization.persistence.springdatajpa;

import static org.junit.Assert.*;
import java.util.List;
import org.catspaw.cherubim.organization.Branch;
import org.catspaw.cherubim.organization.Department;
import org.catspaw.cherubim.organization.Post;
import org.catspaw.cherubim.organization.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("classpath:applicationContext.xml")
public class OrganizationMasterSpringDataJpaTest {

	@SpringBeanByType
	private OrganizationMasterSpringDataJpa	master;

	@Test
	public void testGetTopBranch() {
		Branch branch = master.getTopBranch();
		assertNotNull(branch);
		System.out.println(branch.getName());
	}

	@Test
	public void testGetChildBranchs() {
		Branch branch = master.getTopBranch();
		List<? extends Branch> list = master.getChildBranchs(branch.getId());
		assertTrue(list.size() > 0);
	}

	@Test
	public void testGetTopDepartment() {
		Branch branch = master.getTopBranch();
		Department department = master.getTopDepartment(branch.getId());
		assertNotNull(department);
		System.out.println(department.getName());
	}

	@Test
	public void testGetChildDepartments() {
		Branch branch = master.getTopBranch();
		Department department = master.getTopDepartment(branch.getId());
		List<? extends Department> list = master.getChildDepartments(branch.getId(), department.getId());
		assertTrue(list.size() > 0);
	}

	@Test
	public void testGetPosts() {
		Branch branch = master.getTopBranch();
		Department department = master.getTopDepartment(branch.getId());
		List<? extends Post> list = master.getPosts(department.getId());
		assertTrue(list.size() > 0);
	}

	@Test
	public void testGetStaffs() throws Exception {
		Branch branch = master.getTopBranch();
		Department department = master.getTopDepartment(branch.getId());
		List<? extends Staff> list = master.getStaffs(department.getId());
		assertTrue(list.size() > 0);
	}

	@Test
	public void testGetBranchByStaffId() throws Exception {
		Branch branch = master.getBranchByStaffId("bde2874a-e024-4506-b0a1-6a2915a1556e");
		assertEquals("GRP", branch.getName());
	}

	@Test
	public void testGetDepartmentByStaffId() throws Exception {
		Department department = master.getDepartmentByStaffId("bde2874a-e024-4506-b0a1-6a2915a1556e");
		assertEquals("HQ", department.getName());
	}
	
	@Test
	public void testGetPostsByStaffId() throws Exception {
		List<? extends Post> list = master.getPostsByStaffId("bde2874a-e024-4506-b0a1-6a2915a1556e");
		assertTrue(list.size() > 0);
	}
}
