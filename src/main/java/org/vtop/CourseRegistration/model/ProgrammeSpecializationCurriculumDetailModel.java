package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRG_SPLZTN_CURRICULUM_DETAILS", schema="ACADEMICS")
public class ProgrammeSpecializationCurriculumDetailModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProgrammeSpecializationCurriculumDetailPKModel psccdPkId;
	
	@ManyToOne
	@JoinColumn(name="PRGSPLZN_PRG_SPECIALIZATION_ID", insertable = false, updatable = false)
	private ProgrammeSpecializationModel programmeSpecializationModel;
	
	@Column(name="CATALOG_TYPE")
	private String catalogType;
	
	@Column(name="COURSE_CATEGORY")
	private String courseCategory;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public ProgrammeSpecializationCurriculumDetailPKModel getPsccdPkId() {
		return psccdPkId;
	}

	public void setPsccdPkId(ProgrammeSpecializationCurriculumDetailPKModel psccdPkId) {
		this.psccdPkId = psccdPkId;
	}

	public ProgrammeSpecializationModel getProgrammeSpecializationModel() {
		return programmeSpecializationModel;
	}

	public void setProgrammeSpecializationModel(ProgrammeSpecializationModel programmeSpecializationModel) {
		this.programmeSpecializationModel = programmeSpecializationModel;
	}

	public String getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((psccdPkId == null) ? 0 : psccdPkId.hashCode());
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
		ProgrammeSpecializationCurriculumDetailModel other = (ProgrammeSpecializationCurriculumDetailModel) obj;
		if (psccdPkId == null) {
			if (other.psccdPkId != null)
				return false;
		} else if (!psccdPkId.equals(other.psccdPkId))
			return false;
		return true;
	}
	
}
