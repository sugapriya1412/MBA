package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WishlistRegistrationPKModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="SEMSTR_DETAILS_SEMESTER_SUB_ID")
	private String semesterSubId;
	
	@Column(name="CLSSGRP_MASTER_CLASS_GROUP_ID")
	private String classGroupId;
	
	@Column(name="STDNTSLGNDTLS_REGISTER_NUMBER")
	private String registerNumber;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
	
	@Column(name="CRSTYPCMPNTMASTER_COURSE_TYPE")
	private String courseType;

	public String getSemesterSubId() {
		return semesterSubId;
	}

	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}

	public String getClassGroupId() {
		return classGroupId;
	}

	public void setClassGroupId(String classGroupId) {
		this.classGroupId = classGroupId;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classGroupId == null) ? 0 : classGroupId.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseType == null) ? 0 : courseType.hashCode());
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
		result = prime * result + ((semesterSubId == null) ? 0 : semesterSubId.hashCode());
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
		WishlistRegistrationPKModel other = (WishlistRegistrationPKModel) obj;
		if (classGroupId == null) {
			if (other.classGroupId != null)
				return false;
		} else if (!classGroupId.equals(other.classGroupId))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (courseType == null) {
			if (other.courseType != null)
				return false;
		} else if (!courseType.equals(other.courseType))
			return false;
		if (registerNumber == null) {
			if (other.registerNumber != null)
				return false;
		} else if (!registerNumber.equals(other.registerNumber))
			return false;
		if (semesterSubId == null) {
			if (other.semesterSubId != null)
				return false;
		} else if (!semesterSubId.equals(other.semesterSubId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WishlistRegistrationPKModel [semesterSubId=" + semesterSubId + ", classGroupId=" + classGroupId
				+ ", registerNumber=" + registerNumber + ", courseId=" + courseId + ", courseType=" + courseType + "]";
	}
	
}
