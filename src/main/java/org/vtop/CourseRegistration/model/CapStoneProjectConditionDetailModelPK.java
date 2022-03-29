package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
	
public class CapStoneProjectConditionDetailModelPK implements Serializable{

	private static final long serialVersionUID = 1L;
			
	@Column(name="PROGRAM_GROUP_ID")
	private Integer programGroupId;
			
	@Column(name="STUDENT_BATCH")
	private Integer studentBatch;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((programGroupId == null) ? 0 : programGroupId.hashCode());
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
		CapStoneProjectConditionDetailModelPK other = (CapStoneProjectConditionDetailModelPK) obj;
		if (programGroupId == null) {
			if (other.programGroupId != null)
				return false;
		} else if (!programGroupId.equals(other.programGroupId))
			return false;
		if (studentBatch == null) {
			if (other.studentBatch != null)
				return false;
		} else if (!studentBatch.equals(other.studentBatch))
			return false;
		return true;
	}
	
}
