package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="COURSE_MODE_MASTER", schema="examinations")
public class CourseModeMaster implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String code;

	@Column
	private String description;

	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus;

	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIMESTAMP")
	private Date logTimestamp;

	@Column(name="LOG_USERID")
	private String logUserid;

	public CourseModeMaster() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(BigDecimal lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLogIpaddress() {
		return this.logIpaddress;
	}

	public void setLogIpaddress(String logIpaddress) {
		this.logIpaddress = logIpaddress;
	}

	public Date getLogTimestamp() {
		return this.logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogUserid() {
		return this.logUserid;
	}

	public void setLogUserid(String logUserid) {
		this.logUserid = logUserid;
	}

	@Override
	public String toString() {
		return "CourseModeMaster [code=" + code + ", description=" + description + ", lockStatus=" + lockStatus
				+ ", logIpaddress=" + logIpaddress + ", logTimestamp=" + logTimestamp + ", logUserid=" + logUserid
				+ "]";
	}
}