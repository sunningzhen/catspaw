package org.catspaw.cherubim.organization.persistence.springdatajpa;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.catspaw.cherubim.organization.Branch;
import org.catspaw.cherubim.organization.Department;
import org.catspaw.cherubim.organization.OrganizationMaster;
import org.catspaw.cherubim.organization.Post;
import org.catspaw.cherubim.organization.Staff;
import org.catspaw.cherubim.organization.persistence.springdatajpa.model.BranchModel;
import org.catspaw.cherubim.organization.persistence.springdatajpa.model.DepartmentModel;
import org.catspaw.cherubim.organization.persistence.springdatajpa.model.PostModel;

@Named
public class OrganizationMasterSpringDataJpa implements OrganizationMaster {

	@PersistenceContext
	private EntityManager									entityManager;
	private SimpleJpaRepository<BranchModel, String>		branchRepository;
	private SimpleJpaRepository<DepartmentModel, String>	departmentRepository;
	private SimpleJpaRepository<PostModel, String>			postRepository;

	@PostConstruct
	public void init() {
		branchRepository = new SimpleJpaRepository<BranchModel, String>(BranchModel.class, entityManager);
		departmentRepository = new SimpleJpaRepository<DepartmentModel, String>(DepartmentModel.class, entityManager);
		postRepository = new SimpleJpaRepository<PostModel, String>(PostModel.class, entityManager);
	}

	public Branch getTopBranch() {
		return branchRepository.findOne(new Specification<BranchModel>() {

			public Predicate toPredicate(Root<BranchModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.isNull(root.get("parent"));
			}
		});
	}

	public List<? extends Branch> getChildBranchs(final String parentId) {
		String ql = "select b from Branch b where b.parent = (select b from Branch b where b.id=?1)";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, parentId);
		return query.getResultList();
	}

	public Department getTopDepartment(String branchId) {
		String ql = "select a from Department a where a.branch = (select b from Branch b where b.id=?1) and a.parent is null";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, branchId);
		return (Department) query.getSingleResult();
	}

	public List<? extends Department> getChildDepartments(String branchId, String parentDepartmentId) {
		String ql = "select a from Department a where a.branch = (select b from Branch b where b.id=?1) and a.parent = (select b from Department b where b.id=?2)";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, branchId);
		query.setParameter(2, parentDepartmentId);
		return query.getResultList();
	}

	public List<? extends Post> getPosts(String departmentId) {
		String ql = "select a from Post a where a.department = (select b from Department b where b.id=?1)";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, departmentId);
		return query.getResultList();
	}

	public List<? extends Staff> getStaffs(String departmentId) {
		String ql = "select a from Staff a where a.department = (select b from Department b where b.id=?1)";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, departmentId);
		return query.getResultList();
	}

	public Branch getBranchByStaffId(String staffId) {
		String ql = "select c.department.branch from Staff c where c.id=?1";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, staffId);
		return (Branch) query.getSingleResult();
	}

	public Department getDepartmentByStaffId(String staffId) {
		String ql = "select c.department from Staff c where c.id=?1";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, staffId);
		return (Department) query.getSingleResult();
	}

	public List<? extends Post> getPostsByStaffId(String staffId) {
		String ql = "select a.posts from Staff a where a.id=?1";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, staffId);
		return query.getResultList();
	}
}
