package org.catspaw.cherubim.persistence.dao;

import java.util.Collection;
import java.util.List;

public class GeneralDaoAdapter<E, P> implements GeneralDao<E, P> {

	public long countByProperties(String[] names, Object[] values) {
		throw new UnsupportedOperationException();
	}

	public long countByProperty(String name, Object value) {
		throw new UnsupportedOperationException();
	}

	public long countInProperties(String name, Object[] value) {
		throw new UnsupportedOperationException();
	}

	public void deleteByProperties(String[] names, Object[] values) {
		throw new UnsupportedOperationException();
	}

	public void deleteByProperty(String name, Object value) {
		throw new UnsupportedOperationException();
	}

	public void deleteInProperties(String name, Object[] value) {
		throw new UnsupportedOperationException();
	}

	public List<E> findByExample(E entity) {
		throw new UnsupportedOperationException();
	}

	public List<E> findByProperties(String[] names, Object[] values) {
		throw new UnsupportedOperationException();
	}

	public List<E> findByProperty(String name, Object value) {
		throw new UnsupportedOperationException();
	}

	public List<E> findInProperties(String name, Object[] value) {
		throw new UnsupportedOperationException();
	}

	public E findByUnique(String name, Object value) {
		throw new UnsupportedOperationException();
	}

	public E create() {
		throw new UnsupportedOperationException();
	}

	public void delete(E entity) {
		throw new UnsupportedOperationException();
	}

	public List<E> findAll() {
		throw new UnsupportedOperationException();
	}

	public E get(P id) {
		throw new UnsupportedOperationException();
	}

	public void save(E entity) {
		throw new UnsupportedOperationException();
	}

	public P saveAndObtainId(E entity) {
		throw new UnsupportedOperationException();
	}

	public long total() {
		throw new UnsupportedOperationException();
	}

	public void update(E entity) {
		throw new UnsupportedOperationException();
	}

	public void deleteAll(Collection<E> entities) {
		throw new UnsupportedOperationException();
	}

	public void deleteAll(E[] entities) {
		throw new UnsupportedOperationException();
	}

	public void saveAll(Collection<E> entities) {
		throw new UnsupportedOperationException();
	}

	public void saveAll(E[] entities) {
		throw new UnsupportedOperationException();
	}

	public void updateAll(Collection<E> entities) {
		throw new UnsupportedOperationException();
	}

	public void updateAll(E[] entities) {
		throw new UnsupportedOperationException();
	}
}
