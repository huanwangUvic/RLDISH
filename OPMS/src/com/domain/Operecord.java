package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Operecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operecord", catalog = "epb")
public class Operecord implements java.io.Serializable {

	// Fields

	private Integer opeid;
	private String opetime;
	private String opecontent;
	private Integer opeman;
	private Integer opetype;

	// Constructors

	/** default constructor */
	public Operecord() {
	}

	/** full constructor */
	public Operecord(String opetime, String opecontent, Integer opeman,
			Integer opetype) {
		this.opetime = opetime;
		this.opecontent = opecontent;
		this.opeman = opeman;
		this.opetype = opetype;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "opeid", unique = true, nullable = false)
	public Integer getOpeid() {
		return this.opeid;
	}

	public void setOpeid(Integer opeid) {
		this.opeid = opeid;
	}

	@Column(name = "opetime", length = 50)
	public String getOpetime() {
		return this.opetime;
	}

	public void setOpetime(String opetime) {
		this.opetime = opetime;
	}

	@Column(name = "opecontent", length = 50)
	public String getOpecontent() {
		return this.opecontent;
	}

	public void setOpecontent(String opecontent) {
		this.opecontent = opecontent;
	}

	@Column(name = "opeman")
	public Integer getOpeman() {
		return this.opeman;
	}

	public void setOpeman(Integer opeman) {
		this.opeman = opeman;
	}

	@Column(name = "opetype")
	public Integer getOpetype() {
		return this.opetype;
	}

	public void setOpetype(Integer opetype) {
		this.opetype = opetype;
	}

}