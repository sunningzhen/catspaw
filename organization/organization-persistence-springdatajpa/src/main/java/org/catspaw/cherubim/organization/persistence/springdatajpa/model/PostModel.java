package org.catspaw.cherubim.organization.persistence.springdatajpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.catspaw.cherubim.organization.Post;

@Entity(name = "Post")
@Table(name = "org_post")
public class PostModel implements Post, Serializable {

	@Id
	private String			id;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private DepartmentModel	department;
	@Basic
	private String			name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartmentId() {
		return getDepartment().getId();
	}

	public DepartmentModel getDepartment() {
		return department;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}
}
