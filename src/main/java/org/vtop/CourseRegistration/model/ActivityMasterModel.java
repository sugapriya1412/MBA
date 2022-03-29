package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="ACTIVITY_MASTER", schema="ACADEMICS")
public class ActivityMasterModel {
	
	@Valid
	@Id
	@Column(name="ACTIVITY_ID")
	private Integer activityId;
	
	
	@NotEmpty(message="Enter the Activity Name")
	@Column(name="SHORT_DESCRIPTION")
	private String shortDescription;
	
	
	
	@NotEmpty(message="Enter the Activty Description")
	@Column(name="DESCRIPTION")
	private String description;
	
	@NotNull(message="Select the Cost Center")
	@Column(name="COST_CENTRE_CENTRE_ID")
	private Integer costCentreCentreId;
	
	
	@MapsId("costCentreCentreId")
	@ManyToOne
	@JoinColumn(name="COST_CENTRE_CENTRE_ID", insertable = false, updatable = false)
	private CostCentre costCentre;
	
			
	@Column(name="LOCK_STATUS")
	private Integer lockStatus;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddress;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCostCentreCentreId() {
		return costCentreCentreId;
	}

	public void setCostCentreCentreId(Integer costCentreCentreId) {
		this.costCentreCentreId = costCentreCentreId;
	}

	public CostCentre getCostCentre() {
		return costCentre;
	}

	public void setCostCentre(CostCentre costCentre) {
		this.costCentre = costCentre;
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
		return "ActivityMasterModel [activityId=" + activityId + ", shortDescription=" + shortDescription
				+ ", description=" + description + ", costCentreCentreId=" + costCentreCentreId + ", costCentre="
				+ costCentre + ", lockStatus=" + lockStatus + ", logUserId=" + logUserId + ", logTimeStamp="
				+ logTimeStamp + ", logIpAddress=" + logIpAddress + "]";
	}

	
}
