package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task", catalog = "epb")
public class Task implements java.io.Serializable {

	// Fields

	private Integer taskid;
	private Integer proid;
	private String taskname;
	private String planTime;
	private String startTime;
	private String endTime;
	private Integer taskCondition;
	private String subFile;
	private Integer fileCon;
	private String taskexplain;
	private Integer filePath;

	// Constructors

	/** default constructor */
	public Task() {
	}

	/** full constructor */
	public Task(Integer proid, String taskname, String planTime,
			String startTime, String endTime, Integer taskCondition,
			String subFile, Integer fileCon, String taskexplain, Integer filePath) {
		this.proid = proid;
		this.taskname = taskname;
		this.planTime = planTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.taskCondition = taskCondition;
		this.subFile = subFile;
		this.fileCon = fileCon;
		this.taskexplain = taskexplain;
		this.filePath = filePath;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "taskid", unique = true, nullable = false)
	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	@Column(name = "proid")
	public Integer getProid() {
		return this.proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	@Column(name = "taskname", length = 50)
	public String getTaskname() {
		return this.taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	@Column(name = "plan_time", length = 50)
	public String getPlanTime() {
		return this.planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	@Column(name = "start_time", length = 50)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 50)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "task_condition")
	public Integer getTaskCondition() {
		return this.taskCondition;
	}

	public void setTaskCondition(Integer taskCondition) {
		this.taskCondition = taskCondition;
	}

	@Column(name = "sub_file", length = 100)
	public String getSubFile() {
		return this.subFile;
	}

	public void setSubFile(String subFile) {
		this.subFile = subFile;
	}

	@Column(name = "file_con")
	public Integer getFileCon() {
		return this.fileCon;
	}

	public void setFileCon(Integer fileCon) {
		this.fileCon = fileCon;
	}

	@Column(name = "taskexplain", length = 200)
	public String getTaskexplain() {
		return this.taskexplain;
	}

	public void setTaskexplain(String taskexplain) {
		this.taskexplain = taskexplain;
	}

	@Column(name = "file_path", length = 100)
	public Integer getFilePath() {
		return this.filePath;
	}

	public void setFilePath(Integer filePath) {
		this.filePath = filePath;
	}

}