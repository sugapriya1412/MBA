package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
	
@Embeddable
public class CompulsoryCourseConditionDetailModelPK implements Serializable
{
	private static final long serialVersionUID = 1L;
		
	@Column(name="SEMESTER_SUB_ID")
	private String semesterSubId;
	
	@Column(name="PROGRAM_GROUP_ID")
	private Integer programGroupId;
	
	@Column(name="STUDENT_BATCH")
	private Integer studentBatch;
				
	@Column(name="COURSE_ID")
	private String courseId;
		

	public String getSemesterSubId() {
		return semesterSubId;
	}

	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}

	public Integer getProgramGroupId() {
		return programGroupId;
	}

	public void setProgramGroupId(Integer programGroupId) {
		this.programGroupId = programGroupId;
	}

	public Integer getStudentBatch() {
		return studentBatch;
	}

	public void setStudentBatch(Integer studentBatch) {
		this.studentBatch = studentBatch;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((programGroupId == null) ? 0 : programGroupId.hashCode());
		result = prime * result + ((semesterSubId == null) ? 0 : semesterSubId.hashCode());
		result = prime * result + ((studentBatch == null) ? 0 : studentBatch.hashCode());
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
		CompulsoryCourseConditionDetailModelPK other = (CompulsoryCourseConditionDetailModelPK) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (programGroupId == null) {
			if (other.programGroupId != null)
				return false;
		} else if (!programGroupId.equals(other.programGroupId))
			return false;
		if (semesterSubId == null) {
			if (other.semesterSubId != null)
				return false;
		} else if (!semesterSubId.equals(other.semesterSubId))
			return false;
		if (studentBatch == null) {
			if (other.studentBatch != null)
				return false;
		} else if (!studentBatch.equals(other.studentBatch))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompulsoryCourseConditionDetailModelPK [semesterSubId=" + semesterSubId + ", programGroupId="
				+ programGroupId + ", studentBatch=" + studentBatch + ", courseId=" + courseId + "]";
	}
}
