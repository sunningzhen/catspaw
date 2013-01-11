package org.catspaw.cherubim.frame.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "frame_module")
public class Module implements Serializable {

	@Id
	private String	id;
	@ManyToOne
	private Module	parent;
	@Basic
	private String	code;
	@Basic
	private String	name;
	@Basic
	private String	url;
	@Basic
	private String frame;

	public String getId() {
		return id;
	}

	public Module getParent() {
		return parent;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}
	
	public String getFrame() {
		return frame;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setFrame(String frame) {
		this.frame = frame;
	}
}
