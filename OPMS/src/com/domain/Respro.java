package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Respro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "respro", catalog = "epb")
public class Respro implements java.io.Serializable {

	// Fields

	private Integer proid;
	private String proname;
	private Integer protype;
	private Integer prodep;
	private String apptime;
	private Integer planmoney;
	private String prostate;
	private String proexplain;
	private String probatch;
	private String pronum;
	private Integer prokapp;
	private Integer resman;
	private Integer taskingid;
	private Integer planCon;
	private Integer nowMoney;
	private Integer proCon;

	// Constructors

	/** default constructor */
	public Respro() {
	}

	/** full constructor */
	public Respro(String proname, Integer protype, Integer prodep,
			String apptime, Integer planmoney, String prostate,
			String proexplain, String probatch, String pronum, Integer prokapp,
			Integer resman, Integer taskingid, Integer planCon,
			Integer nowMoney, Integer proCon) {
		this.proname = proname;
		this.protype = protype;
		this.prodep = prodep;
		this.apptime = apptime;
		this.planmoney = planmoney;
		this.prostate = prostate;
		this.proexplain = proexplain;
		this.probatch = probatch;
		this.pronum = pronum;
		this.prokapp = prokapp;
		this.resman = resman;
		this.taskingid = taskingid;
		this.planCon = planCon;
		this.nowMoney = nowMoney;
		this.proCon = proCon;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "proid", unique = true, nullable = false)
	public Integer getProid() {
		return this.proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	@Column(name = "proname", length = 50)
	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	@Column(name = "protype")
	public Integer getProtype() {
		return this.protype;
	}

	public void setProtype(Integer protype) {
		this.protype = protype;
	}

	@Column(name = "prodep")
	public Integer getProdep() {
		return this.prodep;
	}

	public void setProdep(Integer prodep) {
		this.prodep = prodep;
	}

	@Column(name = "apptime", length = 50)
	public String getApptime() {
		return this.apptime;
	}

	public void setApptime(String apptime) {
		this.apptime = apptime;
	}

	@Column(name = "planmoney")
	public Integer getPlanmoney() {
		return this.planmoney;
	}

	public void setPlanmoney(Integer planmoney) {
		this.planmoney = planmoney;
	}

	@Column(name = "prostate", length = 50)
	public String getProstate() {
		return this.prostate;
	}

	public void setProstate(String prostate) {
		this.prostate = prostate;
	}

	@Column(name = "proexplain", length = 65535)
	public String getProexplain() {
		return this.proexplain;
	}

	public void setProexplain(String proexplain) {
		this.proexplain = proexplain;
	}

	@Column(name = "probatch", length = 50)
	public String getProbatch() {
		return this.probatch;
	}

	public void setProbatch(String probatch) {
		this.probatch = probatch;
	}

	@Column(name = "pronum", length = 50)
	public String getPronum() {
		return this.pronum;
	}

	public void setPronum(String pronum) {
		this.pronum = pronum;
	}

	@Column(name = "prokapp")
	public Integer getProkapp() {
		return this.prokapp;
	}

	public void setProkapp(Integer prokapp) {
		this.prokapp = prokapp;
	}

	@Column(name = "resman")
	public Integer getResman() {
		return this.resman;
	}

	public void setResman(Integer resman) {
		this.resman = resman;
	}

	@Column(name = "taskingid")
	public Integer getTaskingid() {
		return this.taskingid;
	}

	public void setTaskingid(Integer taskingid) {
		this.taskingid = taskingid;
	}

	@Column(name = "plan_con")
	public Integer getPlanCon() {
		return this.planCon;
	}

	public void setPlanCon(Integer planCon) {
		this.planCon = planCon;
	}

	@Column(name = "now_money")
	public Integer getNowMoney() {
		return this.nowMoney;
	}

	public void setNowMoney(Integer nowMoney) {
		this.nowMoney = nowMoney;
	}

	@Column(name = "pro_con")
	public Integer getProCon() {
		return this.proCon;
	}

	public void setProCon(Integer proCon) {
		this.proCon = proCon;
	}

}