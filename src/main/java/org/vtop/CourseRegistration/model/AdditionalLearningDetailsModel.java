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
@Table(name="ADDITIONAL_LEARNING_DETAILS", schema="academics")
public class AdditionalLearningDetailsModel {
	
	@Id
	@Column(name="CODE")
	private String code;
		
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LEARNING_TYPE")
	private String learningType;
	
	@Column(name="PRGRM_GROUP_PROGRAMME_GROUP_ID")
	private Integer groupId;
	
	@ManyToOne
	@JoinColumn(name="PRGRM_GROUP_PROGRAMME_GROUP_ID", referencedColumnName="PROGRAMME_GROUP_ID", 
				insertable = false, updatable = false)
	private ProgrammeGroupModel programmeGroupModel;
	
	@Column(name="PROGRAM_SPECIALIZATION")
	private String programSpecialization;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLearningType() {
		return learningType;
	}

	public void setLearningType(String learningType) {
		this.learningType = learningType;
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
		return "AdditionalLearningDetailsModel [code=" + code + ", description=" + description + ", learningType="
				+ learningType + ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}
	
}
