package org.vtop.CourseRegistration.model;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ADDITIONAL_LEARNING_CRS_CTLG", schema="academics")
public class AdditionalLearningCourseCatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AdditionalLearningCourseCatalogPkModel alccPkId;
	
	@ManyToOne
	@JoinColumn(name="ADDTNL_LEARNING_DETAILS_CODE", insertable = false, updatable = false)
	private AdditionalLearningDetailsModel additionalLearningDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public AdditionalLearningCourseCatalogPkModel getAlccPkId() {
		return alccPkId;
	}

	public void setAlccPkId(AdditionalLearningCourseCatalogPkModel alccPkId) {
		this.alccPkId = alccPkId;
	}

	public AdditionalLearningDetailsModel getAdditionalLearningDetailsModel() {
		return additionalLearningDetailsModel;
	}

	public void setAdditionalLearningDetailsModel(AdditionalLearningDetailsModel additionalLearningDetailsModel) {
		this.additionalLearningDetailsModel = additionalLearningDetailsModel;
	}

	public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alccPkId == null) ? 0 : alccPkId.hashCode());
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
		AdditionalLearningCourseCatalogModel other = (AdditionalLearningCourseCatalogModel) obj;
		if (alccPkId == null) {
			if (other.alccPkId != null)
				return false;
		} else if (!alccPkId.equals(other.alccPkId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdditionalLearningCourseCatalogModel [alccPkId=" + alccPkId + ", additionalLearningDetailsModel="
				+ additionalLearningDetailsModel + ", courseCatalogModel=" + courseCatalogModel + ", status=" + status
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
}
