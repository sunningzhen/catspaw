package org.catspaw.cherubim.organization.persistence.springdatajpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.catspaw.cherubim.organization.Branch;

@Entity(name = "Branch")
@Table(name = "org_branch")
public class BranchModel implements Branch, Serializable {

	@Id
	private String		id;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private BranchModel	parent;
	@Basic
	private String		name;
	@Basic
	private String		code;
	@Basic
	private String		frame;
	@Basic
	private int			level;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getParentId() {
		return getParent().getId();
	}

	public int getLevel() {
		return level;
	}

	public BranchModel getParent() {
		return parent;
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

	public void setParent(BranchModel parent) {
		this.parent = parent;
	}
}
