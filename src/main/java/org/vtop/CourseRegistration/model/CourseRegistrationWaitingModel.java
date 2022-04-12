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
@Table(name="COURSE_REGISTRATION_WAITING", schema="ACADEMICS")
public class CourseRegistrationWaitingModel implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private CourseRegistrationWaitingPKModel courseRegistrationWaitingPKId;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID", insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
		
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
		
	@Column(name="COURSE_ALLOCATION_CLASS_ID")
	private String classId;
			
	@ManyToOne
	@JoinColumn(name="COURSE_ALLOCATION_CLASS_ID", insertable = false, updatable = false)
	private CourseAllocationModel courseAllocationModel;
	
	@Column(name="COURSE_OPTION_MASTER_CODE")
	private String courseOptionCode;
	
	@ManyToOne
	@JoinColumn(name="COURSE_OPTION_MASTER_CODE", insertable = false, updatable = false)
	private CourseOptionModel courseOptionModel;
	
	@Column(name="REGISTRATION_STATUS_NUMBER")
	private int statusNumber;
	
	@ManyToOne
	@JoinColumn(name="REGISTRATION_STATUS_NUMBER", insertable = false, updatable = false)
	private RegistrationStatusMasterModel registrationStatusMasterModel;
	
	@Column(name="REGISTRATION_COMPONENT_TYPE")
	private int componentType;
	
	@Column(name="GRADE_CATEGORY")
	private String gradeCategory;
	
	@Column(name="WAITING_STATUS")
	private int waitingStatus;
			
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public CourseRegistrationWaitingPKModel getCourseRegistrationWaitingPKId() {
		return courseRegistrationWaitingPKId;
	}

	public void setCourseRegistrationWaitingPKId(CourseRegistrationWaitingPKModel courseRegistrationWaitingPKId) {
		this.courseRegistrationWaitingPKId = courseRegistrationWaitingPKId;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public CourseAllocationModel getCourseAllocationModel() {
		return courseAllocationModel;
	}

	public void setCourseAllocationModel(CourseAllocationModel courseAllocationModel) {
		this.courseAllocationModel = courseAllocationModel;
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

	public String getCourseOptionCode() {
		return courseOptionCode;
	}

	public void setCourseOptionCode(String courseOptionCode) {
		this.courseOptionCode = courseOptionCode;
	}

	public CourseOptionModel getCourseOptionModel() {
		return courseOptionModel;
	}

	public void setCourseOptionModel(CourseOptionModel courseOptionModel) {
		this.courseOptionModel = courseOptionModel;
	}

	public int getStatusNumber() {
		return statusNumber;
	}

	public void setStatusNumber(int statusNumber) {
		this.statusNumber = statusNumber;
	}

	public RegistrationStatusMasterModel getRegistrationStatusMasterModel() {
		return registrationStatusMasterModel;
	}

	public void setRegistrationStatusMasterModel(RegistrationStatusMasterModel registrationStatusMasterModel) {
		this.registrationStatusMasterModel = registrationStatusMasterModel;
	}

	public int getComponentType() {
		return componentType;
	}

	public void setComponentType(int componentType) {
		this.componentType = componentType;
	}
	
	public String getGradeCategory() {
		return gradeCategory;
	}

	public void setGradeCategory(String gradeCategory) {
		this.gradeCategory = gradeCategory;
	}

	public int getWaitingStatus() {
		return waitingStatus;
	}

	public void setWaitingStatus(int waitingStatus) {
		this.waitingStatus = waitingStatus;
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
		result = prime * result
				+ ((courseRegistrationWaitingPKId == null) ? 0 : courseRegistrationWaitingPKId.hashCode());
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
		CourseRegistrationWaitingModel other = (CourseRegistrationWaitingModel) obj;
		if (courseRegistrationWaitingPKId == null) {
			if (other.courseRegistrationWaitingPKId != null)
				return false;
		} else if (!courseRegistrationWaitingPKId.equals(other.courseRegistrationWaitingPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseRegistrationWaitingModel [courseRegistrationWaitingPKId=" + courseRegistrationWaitingPKId
				+ ", semesterDetailsModel=" + semesterDetailsModel + ", courseCatalogModel=" + courseCatalogModel
				+ ", courseTypeComponentModel=" + courseTypeComponentModel + ", classId=" + classId
				+ ", courseAllocationModel=" + courseAllocationModel + ", courseOptionCode=" + courseOptionCode
				+ ", courseOptionModel=" + courseOptionModel + ", statusNumber=" + statusNumber
				+ ", registrationStatusMasterModel=" + registrationStatusMasterModel + ", componentType="
				+ componentType + ", gradeCategory=" + gradeCategory + ", waitingStatus=" + waitingStatus
				+ ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
}
