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
@Table(name = "COST_CENTRE", schema="vtopmaster")
public class CostCentre {
	
	@Id
	@Column(name = "CENTRE_ID")
	private Integer centreId;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CAMPUS_CODE")
	private String campusCode;
	
	@ManyToOne
	@JoinColumn(name = "CAMPUS_CODE", insertable = false, updatable = false)
	private Campus campus;
	
	@Column(name = "CENTRE_TYPE")
	private String centreType;
	
	@Column(name = "LOCK_STATUS")
	private Integer lockStatus;
	
	@Column(name = "LOG_USERID")
	private String logUserid;
	
	@Column(name = "LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name = "LOG_IPADDRESS")
	private String logIpaddress;

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

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

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}
	
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getCentreType() {
		return centreType;
	}

	public void setCentreType(String centreType) {
		this.centreType = centreType;
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
		return "CostCentre [centreId=" + centreId + ", code=" + code + ", description=" + description + ", campusCode="
				+ campusCode + ", campus=" + campus + ", centreType=" + centreType + ", lockStatus=" + lockStatus
				+ ", logUserid=" + logUserid + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
	
}
