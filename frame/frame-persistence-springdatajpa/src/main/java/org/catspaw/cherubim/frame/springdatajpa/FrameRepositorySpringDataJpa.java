package org.catspaw.cherubim.frame.springdatajpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.catspaw.cherubim.frame.FrameRepository;
import org.catspaw.cherubim.frame.model.Menu;
import org.catspaw.cherubim.frame.model.Module;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Named
public class FrameRepositorySpringDataJpa implements FrameRepository {

	@PersistenceContext
	private EntityManager						entityManager;
	private SimpleJpaRepository<Module, String>	moduleRepository;
	private SimpleJpaRepository<Menu, String>	menuRepository;

	@PostConstruct
	public void init() {
		JpaEntityInformation<Module, String> moduleEntityInfo = new JpaMetamodelEntityInformation<Module, String>(
				Module.class, entityManager.getMetamodel());
		moduleRepository = new SimpleJpaRepository<Module, String>(moduleEntityInfo, entityManager);
		JpaEntityInformation<Menu, String> menuEntityInfo = new JpaMetamodelEntityInformation<Menu, String>(Menu.class,
				entityManager.getMetamodel());
		menuRepository = new SimpleJpaRepository<Menu, String>(menuEntityInfo, entityManager);
	}

	public List<Module> findAllModules() {
		return moduleRepository.findAll();
	}

	public List<Menu> findTopMenus(final String moduleCode) {
		return menuRepository.findAll(new Specification<Menu>() {

			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate1 = cb.equal(root.get("module").get("code"), moduleCode);
				Predicate predicate2 = cb.isNull(root.get("parent"));
				return cb.and(predicate1, predicate2);
			}
		});
	}

	public List<Menu> findChildMenus(final String parentMenuId) {
		return menuRepository.findAll(new Specification<Menu>() {

			@Override
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("parent").get("id"), parentMenuId);
			}
		});
	}
}
