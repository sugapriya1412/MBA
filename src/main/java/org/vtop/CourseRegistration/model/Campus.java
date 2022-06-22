package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="CAMPUS", schema="vtopmaster")
public class Campus {
	
	@Valid
	@NotEmpty
	@Id
	@Column(name="CODE")
	private String code;
	
	@NotEmpty
	@Column(name="CAMPUS_NAME")
	private String campusName;
	
	@NotEmpty
	@Column(name="ADDRESS")
	private String address;
	
	
	@Column(name="YEAR_STARTED")
	private Integer yearStarted;
	
	@NotNull
	@Column(name="PHONE_NUMBER")
	private Long phoneNumber;
	
	@NotEmpty
	@Column(name="EMAIL")
	private String email;
	
	@NotEmpty
	@Column(name="WEBSITE")
	private String website;
	
	@Column(name="LOCK_STATUS")
	private Integer lockStatus;
	
	@Column(name="LOG_USERID")
	private String logUserid;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;	

	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getYearStarted() {
		return yearStarted;
	}

	public void setYearStarted(Integer yearStarted) {
		this.yearStarted = yearStarted;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLogUserid() {
		return logUserid;
	}

	public void setLogUserid(String logUserid) {
		this.logUserid = logUserid;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogIpaddress() {
		return logIpaddress;
	}

	public void setLogIpaddress(String logIpaddress) {
		this.logIpaddress = logIpaddress;
	}
	
	@Override
	public String toString() {
		return "CampusModel [code=" + code + ", campusName=" + campusName + ", address=" + address + ", yearStarted="
				+ yearStarted + ", phoneNumber=" + phoneNumber + ", email=" + email + ", website=" + website
				+ ", lockStatus=" + lockStatus + ", logUserid=" + logUserid + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}
}
