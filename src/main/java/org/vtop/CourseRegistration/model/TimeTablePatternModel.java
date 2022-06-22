package org.vtop.CourseRegistration.model;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;


@Entity
@Table(name="TIME_TABLE_PATTERN_MASTER", schema="academics")
public class TimeTablePatternModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Valid
	@Column(name="PATTERN_ID")
	private Integer patternId;
	
	@Column(name="CAMPUS_CODE")
	private String campusCode;
	
	@MapsId("campusCode")
	@ManyToOne
	@JoinColumn(name="CAMPUS_CODE", insertable = false, updatable = false)
	private Campus campusModel;
	
	@NotEmpty(message="This Filed is Reguired")
	@Column(name="DESCRIPTION")
	private String description;
	
	@NotNull(message="This Filed is Reguired")
	@Column(name="NUMBER_OF_DAYS")
	private Integer noOfDays;

	@Column(name="LOCK_STATUS")
	private Integer status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public Integer getPatternId() {
		return patternId;
	}

	public void setPatternId(Integer patternId) {
		this.patternId = patternId;
	}

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public Campus getCampusModel() {
		return campusModel;
	}

	public void setCampusModel(Campus campusModel) {
		this.campusModel = campusModel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
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
		return "TimeTablePatternModel [patternId=" + patternId + ", campusCode=" + campusCode + ", campusModel="
				+ campusModel + ", description=" + description + ", noOfDays=" + noOfDays + ", status=" + status
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
	
}

