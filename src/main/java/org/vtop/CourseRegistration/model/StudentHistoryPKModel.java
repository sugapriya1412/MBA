package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentHistoryPKModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="STDNTSLGNDTLS_REGISTER_NUMBER")
	private String registerNumber;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
	
	@Column(name="CRSTYPCMPNTMASTER_COURSE_TYPE")
	private String courseType;

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
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseType == null) ? 0 : courseType.hashCode());
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
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
		StudentHistoryPKModel other = (StudentHistoryPKModel) obj;
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
		return true;
	}
	
}
