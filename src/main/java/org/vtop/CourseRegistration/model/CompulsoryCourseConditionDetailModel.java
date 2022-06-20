package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="COMPU_COURSE_COND_DETAIL", schema="ACADEMICS")
public class CompulsoryCourseConditionDetailModel implements Serializable
{
	private static final long serialVersionUID = 1L;
				
	@EmbeddedId
	private CompulsoryCourseConditionDetailModelPK cccdmPkId;
	
	@Column(name="OFFER_TYPE")
	private Integer offerType;
				
	@Column(name="PROGRAM_SPECIALIZATION")
	private String programSpecialization;
	
	@Column(name="PREREQUISITE_TYPE")
	private Integer prerequisiteType;
			
	@Column(name="PREREQUISITE_PARAM")
	private String prerequisiteParam;
	
	@Column(name="PRIORITY_NO")
	private Integer priorityNo;
		
	@Column(name="STATUS")
	private Integer status;
			
	@Column(name="LOG_USERID")
	private String logUserId;
			
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
			
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	
	public CompulsoryCourseConditionDetailModelPK getCccdmPkId() {
		return cccdmPkId;
	}

	public void setCccdmPkId(CompulsoryCourseConditionDetailModelPK cccdmPkId) {
		this.cccdmPkId = cccdmPkId;
	}

	public Integer getOfferType() {
		return offerType;
	}

	public void setOfferType(Integer offerType) {
		this.offerType = offerType;
	}

	public String getProgramSpecialization() {
		return programSpecialization;
	}

	public void setProgramSpecialization(String programSpecialization) {
		this.programSpecialization = programSpecialization;
	}

	public Integer getPrerequisiteType() {
		return prerequisiteType;
	}

	public void setPrerequisiteType(Integer prerequisiteType) {
		this.prerequisiteType = prerequisiteType;
	}

	public String getPrerequisiteParam() {
		return prerequisiteParam;
	}

	public void setPrerequisiteParam(String prerequisiteParam) {
		this.prerequisiteParam = prerequisiteParam;
	}

	public Integer getPriorityNo() {
		return priorityNo;
	}

	public void setPriorityNo(Integer priorityNo) {
		this.priorityNo = priorityNo;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cccdmPkId == null) ? 0 : cccdmPkId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompulsoryCourseConditionDetailModel other = (CompulsoryCourseConditionDetailModel) obj;
		if (cccdmPkId == null) {
			if (other.cccdmPkId != null)
				return false;
		} else if (!cccdmPkId.equals(other.cccdmPkId))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "CompulsoryCourseConditionDetailModel [cccdmPkId=" + cccdmPkId + ", offerType=" + offerType
				+ ", programSpecialization=" + programSpecialization + ", prerequisiteType=" + prerequisiteType
				+ ", prerequisiteParam=" + prerequisiteParam + ", priorityNo=" + priorityNo + ", status=" + status
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
}
