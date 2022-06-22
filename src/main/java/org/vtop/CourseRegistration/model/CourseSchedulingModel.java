package org.vtop.CourseRegistration.model;

import java.io.Serializable;
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
@Table(name="COURSE_SCHEDULING", schema="ACADEMICS")
public class CourseSchedulingModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private CourseSchedulingPKModel courseSchedulingPKId;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID",insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
	
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE",insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID",insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
				
	@Column(name="NUMBER_OF_BATCHES")
	private int numberOfBatches;
		
	@Column(name="LOCK_STATUS")
	private Integer status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public CourseSchedulingPKModel getCourseSchedulingPKId() {
		return courseSchedulingPKId;
	}

	public void setCourseSchedulingPKId(CourseSchedulingPKModel courseSchedulingPKId) {
		this.courseSchedulingPKId = courseSchedulingPKId;
	}

	public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public int getNumberOfBatches() {
		return numberOfBatches;
	}

	public void setNumberOfBatches(int numberOfBatches) {
		this.numberOfBatches = numberOfBatches;
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
		result = prime * result + ((courseSchedulingPKId == null) ? 0 : courseSchedulingPKId.hashCode());
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
		CourseSchedulingModel other = (CourseSchedulingModel) obj;
		if (courseSchedulingPKId == null) {
			if (other.courseSchedulingPKId != null)
				return false;
		} else if (!courseSchedulingPKId.equals(other.courseSchedulingPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseSchedulingModel [courseSchedulingPKId=" + courseSchedulingPKId + ", courseCatalogModel="
				+ courseCatalogModel + ", courseTypeComponentModel=" + courseTypeComponentModel
				+ ", semesterDetailsModel=" + semesterDetailsModel + ", numberOfBatches=" + numberOfBatches
				+ ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}
	
}
