package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseEquivalancesPKModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
	
	@Column(name="EQUIVALENT_COURSE_ID")
	private String equivalentCourseId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getEquivalentCourseId() {
		return equivalentCourseId;
	}

	public void setEquivalentCourseId(String equivalentCourseId) {
		this.equivalentCourseId = equivalentCourseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((equivalentCourseId == null) ? 0 : equivalentCourseId.hashCode());
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
		CourseEquivalancesPKModel other = (CourseEquivalancesPKModel) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (equivalentCourseId == null) {
			if (other.equivalentCourseId != null)
				return false;
		} else if (!equivalentCourseId.equals(other.equivalentCourseId))
			return false;
		return true;
	}
	
}
