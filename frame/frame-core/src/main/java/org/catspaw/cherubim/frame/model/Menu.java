package org.catspaw.cherubim.frame.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "frame_menu")
public class Menu implements Serializable {

	@Id
	private String	id;
	@ManyToOne
	private Menu	parent;
	@Basic
	private String	code;
	@Basic
	private String	name;
	@Basic
	private String	url;
	@Basic
	private String	frame;
	@ManyToOne
	private Module	module;

	public String getId() {
		return id;
	}

	public Menu getParent() {
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

	public Module getModule() {
		return module;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParent(Menu parent) {
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

	public void setModule(Module module) {
		this.module = module;
	}
}
