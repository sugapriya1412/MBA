package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SemesterClassGroupDetailPk implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(name="SEMESTER_SUB_ID")
	private String semesterSubId;
	
	@Column(name="CLASS_GROUP_ID")
	private String classGroupId;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classGroupId == null) ? 0 : classGroupId.hashCode());
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
		SemesterClassGroupDetailPk other = (SemesterClassGroupDetailPk) obj;
		if (classGroupId == null) {
			if (other.classGroupId != null)
				return false;
		} else if (!classGroupId.equals(other.classGroupId))
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
		return "SemesterClassGroupDetailPk [semesterSubId=" + semesterSubId + ", classGroupId=" + classGroupId + "]";
	}
}
