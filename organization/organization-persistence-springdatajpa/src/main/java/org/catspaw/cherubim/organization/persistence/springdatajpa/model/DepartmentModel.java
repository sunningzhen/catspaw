package org.catspaw.cherubim.organization.persistence.springdatajpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.catspaw.cherubim.organization.Department;

@Entity(name = "Department")
@Table(name = "org_department")
public class DepartmentModel implements Department, Serializable {

	@Id
	private String			id;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private DepartmentModel	parent;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private BranchModel		branch;
	@Basic
	private String			name;
	@Basic
	private String			code;
	@Basic
	private String			frame;
	@Basic
	private int				level;

	public String getId() {
		return id;
	}

	public String getParentId() {
		return getParent().getId();
	}

	public String getBranchId() {
		return getBranch().getId();
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public DepartmentModel getParent() {
		return parent;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public String getCode() {
		return code;
	}

	public String getFrame() {
		return frame;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public void setParent(DepartmentModel parent) {
		this.parent = parent;
	}
}
