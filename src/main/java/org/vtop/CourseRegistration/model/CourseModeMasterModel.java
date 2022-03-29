package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="COURSE_MODE_MASTER", schema="ACADEMICS")
public class CourseModeMasterModel {
	
	@NotEmpty(message="Enter Course Mode")
	@Id
	@Column(name="CODE")
	private String code;
	
	@NotEmpty(message="Enter Course Mode Description")
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LOCK_STATUS")
	private Integer lockStatus;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddress;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public Date getLogTimeStamp() {
		return logTimeStamp;
	}

	public void setLogTimeStamp(Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	@Override
	public String toString() {
		return "CourseModeMasterModel [code=" + code + ", description=" + description + ", lockStatus=" + lockStatus
				+ ", logUserId=" + logUserId + ", logTimeStamp=" + logTimeStamp + ", logIpAddress=" + logIpAddress
				+ "]";
	}
	
	
}
