package org.catspaw.cherubim.security.rbac.persistence.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.Resource;
import org.catspaw.cherubim.security.rbac.User;
import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;
import org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model.PermissionModel;
import org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model.ResourceModel;
import org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model.RoleModel;
import org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model.UserModel;

@Named
public class RbacRepositorySpringDataJpa implements RbacRepository {

	@PersistenceContext
	private EntityManager									entityManager;
	private SimpleJpaRepository<ResourceModel, String>		resourceRepository;
	private SimpleJpaRepository<PermissionModel, String>	permissionRepository;
	private SimpleJpaRepository<RoleModel, String>			roleRepository;
	private SimpleJpaRepository<UserModel, String>			userRepository;

	@PostConstruct
	public void init() {
		JpaEntityInformation<ResourceModel, String> resourceEntityInfo = new JpaMetamodelEntityInformation<ResourceModel, String>(
				ResourceModel.class, entityManager.getMetamodel());
		resourceRepository = new SimpleJpaRepository<ResourceModel, String>(resourceEntityInfo, entityManager);
		JpaEntityInformation<PermissionModel, String> permissionEntityInfo = new JpaMetamodelEntityInformation<PermissionModel, String>(
				PermissionModel.class, entityManager.getMetamodel());
		permissionRepository = new SimpleJpaRepository<PermissionModel, String>(permissionEntityInfo, entityManager);
		JpaEntityInformation<RoleModel, String> roleEntityInfo = new JpaMetamodelEntityInformation<RoleModel, String>(
				RoleModel.class, entityManager.getMetamodel());
		roleRepository = new SimpleJpaRepository<RoleModel, String>(roleEntityInfo, entityManager);
		JpaEntityInformation<UserModel, String> userEntityInfo = new JpaMetamodelEntityInformation<UserModel, String>(
				UserModel.class, entityManager.getMetamodel());
		userRepository = new SimpleJpaRepository<UserModel, String>(userEntityInfo, entityManager);
	}

	@Override
	public List<? extends Resource> findResourcesByType(final String type) {
		return resourceRepository.findAll(new Specification<ResourceModel>() {

			@Override
			public Predicate toPredicate(Root<ResourceModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("type"), type);
			}
		});
	}

	public List<String> findOperationSymbolsByResourceSymbol(final String resourceSymbol) {
		List<PermissionModel> permissions = permissionRepository.findAll(new Specification<PermissionModel>() {

			@Override
			public Predicate toPredicate(Root<PermissionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("resourceSymbol"), resourceSymbol);
			}
		});
		List<String> operationSymbols = new ArrayList<String>(permissions.size());
		for (PermissionModel permission : permissions) {
			operationSymbols.add(permission.getOperationSymbol());
		}
		return operationSymbols;
	}

	public String findResourceSymbolByResourceString(final String resourceString) {
		ResourceModel resource = resourceRepository.findOne(new Specification<ResourceModel>() {

			public Predicate toPredicate(Root<ResourceModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("resourceString"), resourceString);
			}
		});
		if (resource != null) {
			return resource.getSymbol();
		}
		return null;
	}

	public List<String> findResourceSymbolsByRoleCode(final String roleCode) {
		String ql = "select r.permissions from Role r where r.code=?1";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, roleCode);
		List<PermissionModel> permissions = query.getResultList();
		List<String> resourceSymbols = new ArrayList<String>(permissions.size());
		for (PermissionModel permission : permissions) {
			resourceSymbols.add(permission.getResourceSymbol());
		}
		return resourceSymbols;
	}

	@Override
	public List<? extends Permission> findPermissionsByUsername(String username) {
		String ql = "select r.permissions from User u join u.roles r where u.username=?1";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username);
		return query.getResultList();
	}

	@Override
	public List<? extends Permission> findPermissionsByResourceSymbolAndOperationSymbol(final String resourceSymbol,
			final String operationSymbol) {
		return permissionRepository.findAll(new Specification<PermissionModel>() {

			@Override
			public Predicate toPredicate(Root<PermissionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("resourceSymbol"), resourceSymbol), cb.equal(root
						.get("operationSymbol"), operationSymbol));
			}
		});
	}

	public List<String> findRoleCodesByResourceSymbol(final String resourceSymbol) {
		List<RoleModel> roles = roleRepository.findAll(new Specification<RoleModel>() {

			@Override
			public Predicate toPredicate(Root<RoleModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<RoleModel, PermissionModel> join = root.join("permissions");
				return cb.equal(join.get("resourceSymbol"), resourceSymbol);
			}
		});
		List<String> roleCodes = new ArrayList<String>(roles.size());
		for (RoleModel role : roles) {
			roleCodes.add(role.getCode());
		}
		return roleCodes;
	}

	public List<String> findRoleCodesByUsername(final String username) {
		String ql = "select u.roles from User u where u.username=?1";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username);
		List<RoleModel> roles = query.getResultList();
		List<String> authorities = new ArrayList<String>(roles.size());
		for (RoleModel role : roles) {
			authorities.add(role.getCode());
		}
		List<String> roleCodes = new ArrayList<String>(roles.size());
		for (RoleModel role : roles) {
			roleCodes.add(role.getCode());
		}
		return roleCodes;
	}

	public User findUserByUsername(final String username) {
		return userRepository.findOne(new Specification<UserModel>() {

			public Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("username"), username);
			}
		});
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
