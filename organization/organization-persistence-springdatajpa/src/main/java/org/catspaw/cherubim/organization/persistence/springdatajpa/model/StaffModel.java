package org.catspaw.cherubim.organization.persistence.springdatajpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.catspaw.cherubim.organization.Staff;

@Entity(name = "Staff")
@Table(name = "org_staff")
public class StaffModel implements Staff, Serializable {

	@Id
	private String			id;
	@Basic
	private String			no;
	@Basic
	private String			name;
	@Basic
	private String			sex;
	@Basic
	private Date			birthday;
	@Basic
	@Column(name = "identity_no")
	private String			identityNo;
	@Basic
	private Date			entryDate;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private DepartmentModel	department;
	@ManyToMany(targetEntity = PostModel.class)
	@JoinTable(name = "org_staff_post", joinColumns = { @JoinColumn(name = "staff_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "post_id", referencedColumnName = "id") })
	private Set<PostModel>	posts;

	public String getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public String getSex() {
		return sex;
	}
	
	public DepartmentModel getDepartment() {
		return department;
	}
	
	public Set<PostModel> getPosts() {
		return posts;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setDepartment(DepartmentModel department) {
		this.department = department;
	}
	
	public void setPosts(Set<PostModel> posts) {
		this.posts = posts;
	}
}
