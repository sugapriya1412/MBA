package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COURSE_EQUIVALANCES", schema="ACADEMICS")
public class CourseEquivalancesModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private CourseEquivalancesPKModel courseEquivalancesPkId;
	
	/*@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;*/
		
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	
	@Column(name="COURSE_CODE")
	private String courseCode;
	
	@Column(name="EQUIVALENT_COURSE_CODE")
	private String equivalentCourseCode;

	public CourseEquivalancesPKModel getCourseEquivalancesPkId() {
		return courseEquivalancesPkId;
	}

	public void setCourseEquivalancesPkId(CourseEquivalancesPKModel courseEquivalancesPkId) {
		this.courseEquivalancesPkId = courseEquivalancesPkId;
	}

	/*public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
	}*/
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getEquivalentCourseCode() {
		return equivalentCourseCode;
	}

	public void setEquivalentCourseCode(String equivalentCourseCode) {
		this.equivalentCourseCode = equivalentCourseCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseEquivalancesPkId == null) ? 0 : courseEquivalancesPkId.hashCode());
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
		CourseEquivalancesModel other = (CourseEquivalancesModel) obj;
		if (courseEquivalancesPkId == null) {
			if (other.courseEquivalancesPkId != null)
				return false;
		} else if (!courseEquivalancesPkId.equals(other.courseEquivalancesPkId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseEquivalancesModel [courseEquivalancesPkId=" + courseEquivalancesPkId + ", remarks=" + remarks
				+ ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + ", courseCode=" + courseCode + ", equivalentCourseCode="
				+ equivalentCourseCode + "]";
	}

	/*@Override
	public String toString() {
		return "CourseEquivalancesModel [courseEquivalancesPkId=" + courseEquivalancesPkId + ", courseCatalogModel="
				+ courseCatalogModel + ", remarks=" + remarks + ", status=" + status + ", logUserId=" + logUserId
				+ ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + ", courseCode=" + courseCode
				+ ", equivalentCourseCode=" + equivalentCourseCode + "]";
	}*/
	
}
