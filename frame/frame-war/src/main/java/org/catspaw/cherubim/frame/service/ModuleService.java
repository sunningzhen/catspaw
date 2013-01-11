package org.catspaw.cherubim.frame.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.catspaw.cherubim.frame.model.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Named
public class ModuleService {

	@PersistenceContext
	private EntityManager						entityManager;
	private SimpleJpaRepository<Module, String>	repository;

	@PostConstruct
	public void init() {
		repository = new SimpleJpaRepository<Module, String>(Module.class, entityManager);
	}

	public Page<Module> findModules(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
