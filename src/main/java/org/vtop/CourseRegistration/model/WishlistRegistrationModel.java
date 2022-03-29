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
@Table(name="WISHLIST_REGISTRATION", schema="ACADEMICS")
public class WishlistRegistrationModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private WishlistRegistrationPKModel wlRegPKId;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID", insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
		
	@ManyToOne
	@JoinColumn(name="CLSSGRP_MASTER_CLASS_GROUP_ID", insertable = false, updatable = false)
	private ClassGroupModel classGroupModel;
	
	@ManyToOne
	@JoinColumn(name="STDNTSLGNDTLS_REGISTER_NUMBER", referencedColumnName="REG_NO", 
					insertable = false, updatable = false)
	private StudentsLoginDetailsModel studentsLoginDetailsModel;
		
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
		
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="COURSE_OPTION_MASTER_CODE")
	private String courseOptionCode;
	
	@ManyToOne
	@JoinColumn(name="COURSE_OPTION_MASTER_CODE", insertable = false, updatable = false)
	private CourseOptionModel courseOptionModel;
	
	@Column(name="REGISTRATION_COMPONENT_TYPE")
	private int componentType;
		
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public WishlistRegistrationPKModel getWlRegPKId() {
		return wlRegPKId;
	}

	public void setWlRegPKId(WishlistRegistrationPKModel wlRegPKId) {
		this.wlRegPKId = wlRegPKId;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public ClassGroupModel getClassGroupModel() {
		return classGroupModel;
	}

	public void setClassGroupModel(ClassGroupModel classGroupModel) {
		this.classGroupModel = classGroupModel;
	}

	public StudentsLoginDetailsModel getStudentsLoginDetailsModel() {
		return studentsLoginDetailsModel;
	}

	public void setStudentsLoginDetailsModel(StudentsLoginDetailsModel studentsLoginDetailsModel) {
		this.studentsLoginDetailsModel = studentsLoginDetailsModel;
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

	public int getComponentType() {
		return componentType;
	}

	public void setComponentType(int componentType) {
		this.componentType = componentType;
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
		result = prime * result + ((wlRegPKId == null) ? 0 : wlRegPKId.hashCode());
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
		WishlistRegistrationModel other = (WishlistRegistrationModel) obj;
		if (wlRegPKId == null) {
			if (other.wlRegPKId != null)
				return false;
		} else if (!wlRegPKId.equals(other.wlRegPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WishlistRegistrationModel [wlRegPKId=" + wlRegPKId + ", semesterDetailsModel=" + semesterDetailsModel
				+ ", classGroupModel=" + classGroupModel + ", studentsLoginDetailsModel=" + studentsLoginDetailsModel
				+ ", courseCatalogModel=" + courseCatalogModel + ", courseTypeComponentModel="
				+ courseTypeComponentModel + ", courseOptionCode=" + courseOptionCode + ", courseOptionModel="
				+ courseOptionModel + ", componentType=" + componentType + ", logUserId=" + logUserId
				+ ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
	
}
