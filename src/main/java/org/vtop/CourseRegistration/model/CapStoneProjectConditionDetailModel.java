package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CAP_STONE_PROJ_COND_DET", schema="ACADEMICS")
public class CapStoneProjectConditionDetailModel implements Serializable
{
	private static final long serialVersionUID = 1L;
				
	@EmbeddedId
    private CapStoneProjectConditionDetailModelPK cspcdmPkId;
				
	@Column(name="TOTAL_CREDITS")
	private Integer totalCredits;
				
	@Column(name="PROJECT_PERCENTAGE")
	private Integer projectPercentage;

	@Column(name="STATUS")
	private Integer status;
						
	@Column(name="LOG_USERID")
	private String logUserId;
						
	@Column(name="LOG_TIMESTAMP")
	private Date logTimestamp;
			
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	
	public CapStoneProjectConditionDetailModelPK getCspcdmPkId() {
		return cspcdmPkId;
	}

	public void setCspcdmPkId(CapStoneProjectConditionDetailModelPK cspcdmPkId) {
		this.cspcdmPkId = cspcdmPkId;
	}

	public Integer getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(Integer totalCredits) {
		this.totalCredits = totalCredits;
	}

	public Integer getProjectPercentage() {
		return projectPercentage;
	}

	public void setProjectPercentage(Integer projectPercentage) {
		this.projectPercentage = projectPercentage;
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
		result = prime * result + ((cspcdmPkId == null) ? 0 : cspcdmPkId.hashCode());
		result = prime * result + ((logIpaddress == null) ? 0 : logIpaddress.hashCode());
		result = prime * result + ((logTimestamp == null) ? 0 : logTimestamp.hashCode());
		result = prime * result + ((logUserId == null) ? 0 : logUserId.hashCode());
		result = prime * result + ((projectPercentage == null) ? 0 : projectPercentage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalCredits == null) ? 0 : totalCredits.hashCode());
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
		CapStoneProjectConditionDetailModel other = (CapStoneProjectConditionDetailModel) obj;
		if (cspcdmPkId == null) {
			if (other.cspcdmPkId != null)
				return false;
		} else if (!cspcdmPkId.equals(other.cspcdmPkId))
			return false;
		if (logIpaddress == null) {
			if (other.logIpaddress != null)
				return false;
		} else if (!logIpaddress.equals(other.logIpaddress))
			return false;
		if (logTimestamp == null) {
			if (other.logTimestamp != null)
				return false;
		} else if (!logTimestamp.equals(other.logTimestamp))
			return false;
		if (logUserId == null) {
			if (other.logUserId != null)
				return false;
		} else if (!logUserId.equals(other.logUserId))
			return false;
		if (projectPercentage == null) {
			if (other.projectPercentage != null)
				return false;
		} else if (!projectPercentage.equals(other.projectPercentage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalCredits == null) {
			if (other.totalCredits != null)
				return false;
		} else if (!totalCredits.equals(other.totalCredits))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CapStoneProjectConditionDetailModel [cspcdmPkId=" + cspcdmPkId + ", totalCredits="
					+ totalCredits + ", projectPercentage=" + projectPercentage + ", status=" + status
					+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress="
					+ logIpaddress + "]";
	}
}
