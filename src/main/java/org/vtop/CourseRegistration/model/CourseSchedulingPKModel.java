package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseSchedulingPKModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="SEMSTR_DETAILS_SEMESTER_SUB_ID")
	private String semesterSubId;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
			
	@Column(name="CRSTYPCMPNTMASTER_COURSE_TYPE")
	private String courseType;
	
	@Column(name="TIME_TABLE_SLOT_ID")
	private Integer slotId;

	public String getSemesterSubId() {
		return semesterSubId;
	}

	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
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

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseType == null) ? 0 : courseType.hashCode());
		result = prime * result + ((semesterSubId == null) ? 0 : semesterSubId.hashCode());
		result = prime * result + ((slotId == null) ? 0 : slotId.hashCode());
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
		CourseSchedulingPKModel other = (CourseSchedulingPKModel) obj;
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
		if (semesterSubId == null) {
			if (other.semesterSubId != null)
				return false;
		} else if (!semesterSubId.equals(other.semesterSubId))
			return false;
		if (slotId == null) {
			if (other.slotId != null)
				return false;
		} else if (!slotId.equals(other.slotId))
			return false;
		return true;
	}
	
}
