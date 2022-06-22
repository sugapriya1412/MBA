package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PROGRAMME_GROUP", schema="vtopmaster")
public class ProgrammeGroupModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROGRAMME_GROUP_ID")
	private Integer groupId;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="PROGRAMME_MODE")
	private String programmeMode;
	
	@Column(name="PROGRAMME_NAME")
	private String programmeName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PROGRAMME_DURATION")
	private int programmeDuration;
	
	@Column(name="YEAR_STARTED")
	private Integer yearStarted;
	
	@Column(name="ELAPSED_TIME")
	private int elapsedTime;
	
	@Column(name="PROGRAMME_LEVEL")
	private String programmeLevel;
	
	@Column(name="PROGRAMME_TYPE")
	private String programmeType;
	
	@Column(name="COUNCIL_COUNCIL_NUMBER")
	private long councilNumber;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProgrammeMode() {
		return programmeMode;
	}

	public void setProgrammeMode(String programmeMode) {
		this.programmeMode = programmeMode;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProgrammeDuration() {
		return programmeDuration;
	}

	public void setProgrammeDuration(int programmeDuration) {
		this.programmeDuration = programmeDuration;
	}

	public Integer getYearStarted() {
		return yearStarted;
	}

	public void setYearStarted(Integer yearStarted) {
		this.yearStarted = yearStarted;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getProgrammeLevel() {
		return programmeLevel;
	}

	public void setProgrammeLevel(String programmeLevel) {
		this.programmeLevel = programmeLevel;
	}

	public String getProgrammeType() {
		return programmeType;
	}

	public void setProgrammeType(String programmeType) {
		this.programmeType = programmeType;
	}

	public long getCouncilNumber() {
		return councilNumber;
	}

	public void setCouncilNumber(long councilNumber) {
		this.councilNumber = councilNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}
	
	public String getLogTimestamp() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	    if(this.logTimestamp==null) {
	    	return "";
	    } else {
	      return dateFormat.format(logTimestamp); 
	    }
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
		return "ProgrammeGroupModel [groupId=" + groupId + ", code=" + code + ", programmeMode=" + programmeMode
				+ ", programmeName=" + programmeName + ", description=" + description + ", programmeDuration="
				+ programmeDuration + ", yearStarted=" + yearStarted + ", elapsedTime=" + elapsedTime
				+ ", programmeLevel=" + programmeLevel + ", programmeType=" + programmeType + ", councilNumber="
				+ councilNumber + ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}

}
