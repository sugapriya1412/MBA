package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="STUDENTS_LOGIN_DETAILS" , schema="admissions")
public class StudentsLoginDetailsModel {

	@Id
	@Column(name="REG_NO")
	private String regNo;
	
	@Column(name="APPLICATION_NO")
	private Long applicationNo;
	
	@ManyToOne
	@JoinColumn(name="APPLICATION_NO", insertable = false, updatable = false)
	StudentBaseModel studentBaseModel;	
	
	/*@Column(name="PASSWORD")
	private String password;*/
	
	@Column(name="COST_CENTRE")
	private Integer costCentre;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	/*@Column(name="PWD_LASTUPDATE")
	@Temporal(TemporalType.TIMESTAMP)	
	private Date pwdLastUpdate;*/
	
	@Column(name="EDU_STATUS")	
	private String eduStatus;
	
	@Column(name="LOCK_STATUS")	
	private Integer lockStatus;
	
	@Column(name="STUDY_START_DATE")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date studyStartDate;
	
	@Column(name="STUDY_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date studyEndDate;
	
	@Column(name="STUDY_SYSTEM")
	private String studySystem;
	
	/*@Column(name="PARENT_LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date parentLogTimestamp;
	
	@Column(name="PARENT_LOG_IPADDRESS")
	private String parentLogIpAddress;*/
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddess;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	public String getRegNo() {
		return regNo;
	}
	
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public Long getApplicationNo() {
		return applicationNo;
	}
	
	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}
	
	/*public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}*/
	
	public Integer getCostCentre() {
		return costCentre;
	}
	
	public void setCostCentre(Integer costCentre) {
		this.costCentre = costCentre;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/*public Date getPwdLastUpdate() {
		return pwdLastUpdate;
	}
	
	public void setPwdLastUpdate(Date pwdLastUpdate) {
		this.pwdLastUpdate = pwdLastUpdate;
	}*/
	
	public String getEduStatus() {
		return eduStatus;
	}
	
	public void setEduStatus(String eduStatus) {
		this.eduStatus = eduStatus;
	}
	
	public Integer getLockStatus() {
		return lockStatus;
	}
	
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	public Date getStudyStartDate() {
		return studyStartDate;
	}
	
	public void setStudyStartDate(Date studyStartDate) {
		this.studyStartDate = studyStartDate;
	}
	
	public Date getStudyEndDate() {
		return studyEndDate;
	}
	
	public void setStudyEndDate(Date studyEndDate) {
		this.studyEndDate = studyEndDate;
	}
	public String getStudySystem() {
		return studySystem;
	}
	public void setStudySystem(String studySystem) {
		this.studySystem = studySystem;
	}
	/*public Date getParentLogTimestamp() {
		return parentLogTimestamp;
	}
	public void setParentLogTimestamp(Date parentLogTimestamp) {
		this.parentLogTimestamp = parentLogTimestamp;
	}
	public String getParentLogIpAddress() {
		return parentLogIpAddress;
	}
	public void setParentLogIpAddress(String parentLogIpAddress) {
		this.parentLogIpAddress = parentLogIpAddress;
	}*/
	public String getLogIpAddess() {
		return logIpAddess;
	}
	public void setLogIpAddess(String logIpAddess) {
		this.logIpAddess = logIpAddess;
	}
	public Date getLogTimestamp() {
		return logTimestamp;
	}
	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public StudentBaseModel getStudentBaseModel() {
		return studentBaseModel;
	}

	public void setStudentBaseModel(StudentBaseModel studentBaseModel) {
		this.studentBaseModel = studentBaseModel;
	}
	
	
	
}
