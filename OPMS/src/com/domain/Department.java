package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "department", catalog = "epb")
public class Department implements java.io.Serializable {

	// Fields

	private Integer depnum;
	private String depname;
	private Integer deprole;
	private String deppwd;

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String depname, Integer deprole, String deppwd) {
		this.depname = depname;
		this.deprole = deprole;
		this.deppwd = deppwd;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "depnum", unique = true, nullable = false)
	public Integer getDepnum() {
		return this.depnum;
	}

	public void setDepnum(Integer depnum) {
		this.depnum = depnum;
	}

	@Column(name = "depname", length = 50)
	public String getDepname() {
		return this.depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	@Column(name = "deprole")
	public Integer getDeprole() {
		return this.deprole;
	}

	public void setDeprole(Integer deprole) {
		this.deprole = deprole;
	}

	@Column(name = "deppwd", length = 50)
	public String getDeppwd() {
		return this.deppwd;
	}

	public void setDeppwd(String deppwd) {
		this.deppwd = deppwd;
	}

}